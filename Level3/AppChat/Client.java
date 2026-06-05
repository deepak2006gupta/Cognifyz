import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to Server!");

            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            while (true) {

                System.out.print("You: ");
                String message = keyboard.readLine();

                output.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String serverReply = input.readLine();

                System.out.println("Server: " + serverReply);
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}