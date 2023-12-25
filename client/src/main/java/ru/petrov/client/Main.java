package ru.petrov.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name");
            String name = scanner.nextLine();

            Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
            Client client = new Client(name, socket);

            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort:" + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}