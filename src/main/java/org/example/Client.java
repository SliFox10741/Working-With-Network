package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(ServerConfig.HOST, ServerConfig.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String answer;
            System.out.println(reader.readLine());
            writer.println(scanner.nextLine());
            answer = reader.readLine();
            System.out.println(answer);
            while (answer.equals("Not OK")) {
                writer.println(scanner.nextLine());
                answer = reader.readLine();
                System.out.println(answer);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
