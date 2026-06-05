import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server Started...");
            System.out.println("Waiting for Client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client Connected!");

            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            while (true) {

                String clientMessage = input.readLine();

                System.out.println("Client: " + clientMessage);

                System.out.print("You: ");
                String serverReply = keyboard.readLine();

                output.println(serverReply);

                if (serverReply.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}