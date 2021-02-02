package OldFile.HelloServer.server;

import OldFile.HelloServer.hello.Hello;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServer implements Hello {
    public HelloServer(){};

    public String sayHello(){
		System.out.println("Invoked!");
        return "Well done!";
	}
	public String addStu(String sid){
		String regex = "[0-9]";
		if(!sid.matches(regex)){
			return "Please enter your student ID";
		}
		System.out.print(sid);
		return "Done";
			
	}
	public static void main(String[] args) throws Exception {
	     HelloServer obj = new HelloServer();
         Hello stub = (Hello)UnicastRemoteObject.exportObject(obj, 0);
	     Registry registry = LocateRegistry.getRegistry();
         registry.rebind("Hello", stub);
		 System.out.println("OldFile.HelloServer ready!");
	}
}
