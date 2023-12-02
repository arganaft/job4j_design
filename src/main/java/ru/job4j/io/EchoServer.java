package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean greetings = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String str = in.readLine();
                    if (str.contains("/?msg=Hello") && !greetings) {
                        out.write("Hello".getBytes());
                    }
                    if (greetings) {
                        out.write("Hello, dear friend.".getBytes());
                        greetings = false;
                    }
                    if (str.contains("/?msg=Exit")) {
                        server.close();
                        out.write("Shutting down the server...".getBytes());
                    }

                    if (!str.contains("/?msg=Hello") && !str.contains("/?msg=Exit")) {
                        out.write("What".getBytes());
                    }
                    for (; str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();

                }
            }
        }
    }
}