package HelloServer.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import HelloServer.hello.Hello;

public class HelloClient {
    private HelloClient() {};
    public static void main(String[] args) throws Exception{
            Registry registry = LocateRegistry.getRegistry("10.90.86.77");
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
			System.out.println("response: " + stub.addStu("18021174"));
   }
}

