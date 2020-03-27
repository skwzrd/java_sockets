import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Socket {

    public static void main(String[] args) throws Exception {
        System.out.println("Server Activity");

        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String terminate = "q";
        String client_request = "";
        while (!client_request.equals(terminate)) {
            // Get request from client
            client_request = dis.readUTF();
            System.out.println("Received: " + client_request);
            // Default response to client
            String response = "OK";

            if (client_request.equals(terminate)) {
                // Tell client to shutdown too
                response = "q";
            }
            System.out.println("Sending: " + response);
            dos.writeUTF(response);
            dos.flush();
        }
        System.out.println("Shutting down server.");

        dos.close();
        dis.close();
        s.close();
        ss.close();
    }
}
