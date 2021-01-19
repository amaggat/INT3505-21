package MultipleClientOneServer;

import Model.MinMax;

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
        Socket client = listener.accept();
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));


        ArrayList<Integer> numberList= new ArrayList<>();

        while (true){
            try {
                ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
                try {
                    Object object = objectInput.readObject();
                    numberList =  (ArrayList<Integer>) object;
                    System.out.println(numberList);

                    MinMax results = MinMax.findMinAndMax(numberList);
                    out.println("Min: " + results.getMin() + ", Max: " + results.getMax());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
