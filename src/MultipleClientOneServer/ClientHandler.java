package MultipleClientOneServer;

import Model.MinMax;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter out;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        ArrayList<Integer> numberList = new ArrayList<>();
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
            } finally {
                out.close();

                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}