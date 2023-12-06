import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class ClientBonk {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream din;
    static DataOutputStream dout;

    boolean flag = true;


    public static void main(String[] args){
        try {
            serverSocket = new ServerSocket(1127); // server starts at port 1127
            System.out.println("Server waiting for clients...");
            socket = serverSocket.accept(); // Server will accept the connections

            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());





        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
