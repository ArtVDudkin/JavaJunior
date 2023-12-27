package ru.geekbrains.chat.server;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private final long clientId;

    private static long clientCounter = 1L;
    private static final Map<Long, ClientManager> clients = new HashMap<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        this.clientId = clientCounter++;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.put(clientId, this);
            System.out.println(name + " подключился к чату");
            broadcastMessage("Server: " + name +"_[@" + clientId + "]" + " подключился к чату");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    // для macOS, чтобы вылетевший клиент не спамил null-сообщениями
                    // для Windows такая проблема с null-сообщениями не возникает, т.к дефектный сокет закрывается сам
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                String[] msg = messageFromClient.split(" ");
                if(msg[1].charAt(0) == '@') {
                    try {
                        // в messageFromClient первым словом указано имя пользователя, а затем само сообщение!
                        long sendToId = Long.parseLong(msg[1].substring(1));
                        sendTo(sendToId, messageFromClient);
                    } catch (NumberFormatException | NullPointerException e) {
                        // Если после @ не получилось корректно распарсить значение типа long,
                        // то рассылаем исходное сообщение всем пользователям как есть
                        broadcastMessage(messageFromClient);
                    }

                } else {
                    broadcastMessage(messageFromClient);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Отправить сообщение всем пользователям, кроме себя
     */
    private void broadcastMessage(String message) {
        clients.values().forEach(it -> {
                    try {
                        if (!it.name.equals(name)) {
                            it.bufferedWriter.write(message);
                            it.bufferedWriter.newLine();
                            it.bufferedWriter.flush();
                        }
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
                );
    }

    private void sendTo(long id, String message) {
        System.out.println(id + ": " + message);
        try {
            clients.get(id).bufferedWriter.write(message);
            clients.get(id).bufferedWriter.newLine();
            clients.get(id).bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Закрыть socket, bufferedReader и bufferedWriter на случай, если что-то пошло не так...
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

}
