/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author mac
 */
public class ClientService {

  Socket _socket;
  ObjectOutputStream _send;
  ObjectInputStream _received;

  private static ClientService single_instance = null;

  public ClientService() {}

  public static ClientService getInstance() {
    if (single_instance == null) single_instance = new ClientService();

    return single_instance;
  }
  
  
  public void initConnection() {
    try {
      _socket = new Socket("localhost", 4001);
      _received = new ObjectInputStream(_socket.getInputStream());
      _send = new ObjectOutputStream(_socket.getOutputStream());
    } catch (Exception e) {
     
      e.printStackTrace();
    }
  }

  public void SendMessage(String msg) {
    try {
      _send.writeObject(msg);
    } catch (Exception e) {
      System.out.println("error ");
      e.printStackTrace();
    }
  }

  // TODO : add thead
  public void ReciveMessage() {
    String msg;
    while (true) {
      try {
        msg = (String) _received.readObject();
        // TODO : function retrieve data
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
