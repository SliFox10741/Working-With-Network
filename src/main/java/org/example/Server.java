package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String ... args) {

        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("Сервер запущен");

            String city = "???";
            String gorod;

            while (true){

                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    System.out.println("Подключён клиент " + client.getPort());
                    writer.println(city);
                    while (true) {
                        gorod = reader.readLine().toLowerCase();
                        System.out.println("(" + client.getPort() + ") " + gorod);

                        if (!city.equals("???")) {
                            if (gorod.charAt(0) == city.charAt(city.length() - 1)) {
                                writer.println("OK");
                                city = gorod;
                                break;
                            } else {
                                writer.println("Not OK");
                            }
                        } else {
                            city = gorod;
                            writer.println("OK");
                            break;
                        }
                    }
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            throw new RuntimeException(e);
        }

    }
}
