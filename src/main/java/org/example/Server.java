package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String ... args) {

        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("Сервер запущен");

            while (true){

                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    System.out.println("Подключён клиент " + client.getPort());

                    writer.println("Hi, your port is " + client.getPort());

                    System.out.println("(" + client.getPort() + ") " + reader.readLine());

                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
