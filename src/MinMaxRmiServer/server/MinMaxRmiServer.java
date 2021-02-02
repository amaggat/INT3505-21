package MinMaxRmiServer.server;

import MinMaxRmiServer.pair.Pair;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MinMaxRmiServer implements Pair {

    Integer min, max;

    public MinMaxRmiServer() {
    }

    public MinMaxRmiServer(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin(ArrayList<Integer> array){
        min = array.get(0);

        for (Integer i : array)
        {
            if(i < min)
            {
                min = i;
            }
        }
        return min;
    }

    public int getMax(ArrayList<Integer> array){
        max = array.get(0);

        for (Integer i : array)
        {
            if(i > max)
            {
                max = i;
            }
        }
        return max;
    }

    public Pair getMinMax(ArrayList<Integer> array)
    {
        min = getMin(array);
        max = getMax(array);

        return new MinMaxRmiServer(min, max);
    }

    @Override
    public String toString() {
        return "Min: " + min +
                ", Max: " + max;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        MinMaxRmiServer server = new MinMaxRmiServer();
        Pair obj = (Pair) UnicastRemoteObject.exportObject(server, 8080);
        Registry registry = LocateRegistry.createRegistry(8080);
        registry.bind("MinMax", obj);
        System.out.println("MinMaxRmiServer ready!");
    }

}
