import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader serverInput =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter serverOutput =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            BufferedReader keyboard =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            System.out.println(serverInput.readLine());

            String username = keyboard.readLine();

            serverOutput.println(username);

            Thread receiveThread = new Thread(() -> {

                try {

                    String message;

                    while ((message =
                            serverInput.readLine()) != null) {

                        if (!message.startsWith(username + ":")) {
                            System.out.println(message);
                        }
                    }

                } catch (Exception e) {

                    System.out.println("Disconnected from server.");
                }
            });

            receiveThread.start();

            System.out.println();
            System.out.println("Start chatting...");
            System.out.println("Type 'exit' to leave");
            System.out.println();

            String message;

            while ((message = keyboard.readLine()) != null) {

                if (message.equalsIgnoreCase("exit")) {

                    serverOutput.println(message);

                    break;
                }

                System.out.println("You: " + message);

                serverOutput.println(message);
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}