import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String username;

    public ClientHandler(Socket socket) {

        this.socket = socket;

        try {
            input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            output = new PrintWriter(
                    socket.getOutputStream(),
                    true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            output.println("Enter your username:");

            username = input.readLine();

            if (username == null || username.trim().isEmpty()) {
                username = "Anonymous";
            }

            broadcast("🔵 " + username + " joined the chat");

            String message;

            while ((message = input.readLine()) != null) {

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                broadcast(username + ": " + message);
            }

        } catch (Exception e) {

            System.out.println(username + " disconnected.");

        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChatServer.clients.remove(this);

            if (username != null) {
                broadcast("🔴 " + username + " left the chat");
            }
        }
    }

    private synchronized void broadcast(String message) {

        for (ClientHandler client : ChatServer.clients) {

            client.output.println(message);
        }

        System.out.println(message);
    }
}