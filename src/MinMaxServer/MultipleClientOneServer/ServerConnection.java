package MinMaxServer.MultipleClientOneServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket socket;
    private BufferedReader input;

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        String serverResponse = null;
        try {
            while (true){
                serverResponse = input.readLine();

                if(serverResponse == null) break;

                System.out.println("Hello " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
