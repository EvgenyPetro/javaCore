package ru.petrov.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String name;
    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;

    public Client(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenForMessage(){
        new Thread(() -> {
            String message;
            while (socket.isConnected()){
                try {
                    message = reader.readLine();
                    System.out.println(message);
                }
                catch (IOException e){
                    closeEverything(socket, reader, writer);
                }
            }
        }).start();
    }


    public void sendMessage(){
        try {
            writer.write(name);
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                writer.write(name + ": " + message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e){
            closeEverything(socket, reader, writer);
        }
    }

    private void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
