package MinMaxRmiServer.client;

import MinMaxRmiServer.pair.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MinMaxRmiClient {

    public MinMaxRmiClient() {
    }

    public static void main(String[] args) throws Exception {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        Registry registry = LocateRegistry.getRegistry("192.168.100.11", 8080);
        Pair stub = (Pair) registry.lookup("MinMax");
        ArrayList<Integer> list = parseStringToInteger(keyboard.readLine());
        Pair result = stub.getMinMax(list);

        System.out.println("getMin() - Min: " + stub.getMin(list));
        System.out.println("getMax() - Max: " + stub.getMax(list));
        System.out.println("getMinMax() " + result);
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
