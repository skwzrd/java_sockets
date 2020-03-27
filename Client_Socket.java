import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client_Socket {

    public static void main(String[] args) throws Exception {
        System.out.println("Client Activity");

        Socket s = new Socket("localhost", 8080);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String terminate = "q";
        String server_response = "";
        while (!server_response.equals(terminate)) {
            // Get input from console
            System.out.println("Write something to the server. (Enter \"q\" to terminate)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String console_input = br.readLine();

            // Send console input to server
            dos.writeUTF(console_input);
            dos.flush();

            // Read server's response
            server_response = dis.readUTF();
            System.out.println("Client received: " + server_response);
        }
        System.out.println("Shutting down client.");
        dos.close();
        dis.close();
        s.close();
    }
}
