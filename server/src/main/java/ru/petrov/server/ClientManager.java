package ru.petrov.server;

import java.util.ArrayList;

public class ClientManager {


    public final static ArrayList<Client> clients = new ArrayList<>();

    public void sendMessage(String message) {

        if (!message.contains("@")) {
            broadcastSendMessage(message);
        } else {
            privateSendMessage(message);
        }

    }

    private void broadcastSendMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    private void privateSendMessage(String message) {

        String[] split = message.split("@", 2);
        String[] s = split[1].split(" ", 2);
        String from = s[0];
        String mesTo = s[1];

        clients.stream().filter(client -> client.getName().equals(from))
                .forEach(client -> client.sendMessage(String.format("%s%s", split[0], mesTo)));
    }


    public void removeClient(String name, Client client) {
        clients.remove(client);
        System.out.println(name + " покинул чат.");
        sendMessage("Server: " + name + " покинул чат.");
    }

    public void add(Client client) {
        clients.add(client);
    }
}
