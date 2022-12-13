package com.chatapp;

import com.chatapp.views.ChatFrame;
import com.chatapp.views.LoginFrame;
import java.io.IOException;

/**
 *
 * @author mac
 */
public class ChatApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here
        
        //LoginFrame lf = new LoginFrame();
         ChatFrame cf = new ChatFrame();
          cf.open();
           // cf.initConnection();
           // cf.ReciveMessage();
    }
    
}
