package OldFile.MinMaxServer.OneServerOneClient;

import OldFile.MinMaxServer.Model.MinMax;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MinMaxServer {

    private static final int PORT = 8080;

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
