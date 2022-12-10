
package chatappserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.util.Vector;

public class Server {

    // Vector to store active clients
    static Vector<ClientHandler> clientList = new Vector<>();
    final static int serverPort = 4001;
    // counter for clients
    static int i = 0;

    public Server() {
        // server is listening on port 1234
        System.out.println("Waiting client : ");

        // running infinite loop for getting
        // client request
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                Socket socket;
                // Accept the incoming request
                socket = serverSocket.accept();
                // obtain input and output streams
                ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());

                // Create a new handler object for handling this request.
                ClientHandler mtch = new ClientHandler(socket, "client" + i, dis, dos);

                // Create a new Thread with this object.
                Thread t = new Thread(mtch);

                // add this client to active clients list
                clientList.add(mtch);

                // start the thread.
                t.start();

                // increment i for new client.
                // i is used for naming only, and can be replaced
                // by any naming scheme
                i++;

            } catch (Exception e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
                return;
            }

        }
    }
}
