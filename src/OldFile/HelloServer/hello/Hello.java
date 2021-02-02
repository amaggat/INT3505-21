package OldFile.HelloServer.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello() throws RemoteException;
	String addStu(String sid) throws RemoteException;
}

