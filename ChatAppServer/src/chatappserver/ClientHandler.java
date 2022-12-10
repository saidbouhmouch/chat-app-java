package chatappserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.util.StringTokenizer;

class ClientHandler implements Runnable {
    private String name;
    ObjectInputStream _received;
    ObjectOutputStream _reply;
    Socket _socket;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket s, String name, ObjectInputStream received, ObjectOutputStream reply) {
        this._received = received;
        this._reply = reply;
        this.name = name;
        this._socket = s;
        this.isloggedin = true;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {
                // receive the string
                received = (String) _received.readObject();

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this._socket.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();

                // search for the recipient in the connected devices list.
                // ar is the vector storing client of active users
                for (ClientHandler client : Server.clientList) {
                    // if the recipient is found, write on its
                    // output stream
                    if (client.name.equals(recipient) && client.isloggedin == true) {
                        // TODO : function to reply
                        client._reply.writeObject(this.name + " : " + MsgToSend);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("error ");
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
                return;
            }

        }
        try {
            // closing resources
            this._received.close();
            this._reply.close();

        } catch (Exception e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}