package MinMaxServer.MultipleClientOneServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MinMaxServer {

    private static final int PORT = 8080;
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

//        PrintWriter out = new PrintWriter(HelloServer.MinMaxRmiServer.client.getOutputStream(), true);
//        BufferedReader input = new BufferedReader(new InputStreamReader(HelloServer.MinMaxRmiServer.client.getInputStream()));
//        ArrayList<Integer> numberList = new ArrayList<>();

        while (true) {
            Socket client = listener.accept();
            ClientHandler clientThread = new ClientHandler(client);
            clientHandlers.add(clientThread);
            service.execute(clientThread);
        }
    }
}
