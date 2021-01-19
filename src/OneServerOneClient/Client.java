package OneServerOneClient;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            ArrayList<Integer> numberList = parseStringToInteger(keyboard.readLine());
            try
            {
                ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
                objectOutput.writeObject(numberList);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String serverResponse = input.readLine();
            System.out.println(serverResponse);
        }
    }

    public static ArrayList<Integer> parseStringToInteger (String array)
    {
        String[] list = array.split(" ");
        ArrayList<Integer> numberList = new ArrayList<>();

        for(String elements : list)
        {
            numberList.add(Integer.parseInt(elements));
        }

        return numberList;
    }
}
