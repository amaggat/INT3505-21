package OneServerOneClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.MinMax;

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

//    public static MinMax findMinAndMax(ArrayList<Integer> numberList)
//    {
//        Integer min = numberList.get(0);
//        Integer max = numberList.get(0);
//
//        for(Integer number : numberList)
//        {
//            if(number < min)
//            {
//                min = number;
//            }
//            if (number > max)
//            {
//                max = number;
//            }
//        }
//
//        return new MinMax(min, max);
//    }
}
