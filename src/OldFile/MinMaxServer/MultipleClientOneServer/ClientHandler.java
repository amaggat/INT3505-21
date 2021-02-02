package OldFile.MinMaxServer.MultipleClientOneServer;

import OldFile.MinMaxServer.Model.MinMax;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<ClientHandler> clientHandlers;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        ArrayList<Integer> numberList = new ArrayList<>();

        ObjectInputStream objectInput = null;
        try {
            objectInput = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            while (true){
                try{
                    Object object = objectInput.readObject();
                    numberList =  (ArrayList<Integer>) object;
                    System.out.println(numberList);
                    MinMax results = MinMax.findMinAndMax(numberList);
                    System.out.println("Min: " + results.getMin() + ", Max: " + results.getMax());
                    output.println("Min: " + results.getMin() + ", Max: " + results.getMax());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}