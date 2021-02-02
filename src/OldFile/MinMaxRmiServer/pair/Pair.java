package OldFile.MinMaxRmiServer.pair;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Pair extends Remote, Serializable {

    int getMin(ArrayList<Integer> array) throws RemoteException;

    int getMax(ArrayList<Integer> array) throws RemoteException;

    Pair getMinMax(ArrayList<Integer> list) throws RemoteException;
}
