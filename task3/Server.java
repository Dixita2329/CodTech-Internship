import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static Set<String> usernames = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Ask username
                out.println("Enter your username:");
                username = in.readLine();

                synchronized (usernames) {
                    usernames.add(username);
                }

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                out.println("Welcome " + username + "! You can start chatting.");

                String message;
                while ((message = in.readLine()) != null) {
                    String fullMessage = username + ": " + message;
                    System.out.println(fullMessage);

                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(fullMessage);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Client disconnected");
            } finally {
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                synchronized (usernames) {
                    usernames.remove(username);
                }
            }
        }
    }
}