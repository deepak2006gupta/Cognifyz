import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {

    public static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("=================================");
            System.out.println(" Multi-Threaded Chat Server");
            System.out.println(" Running on Port 5000");
            System.out.println("=================================");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Client connected: "
                        + socket.getInetAddress());

                ClientHandler client = new ClientHandler(socket);

                clients.add(client);

                client.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}