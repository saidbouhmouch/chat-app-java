/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.chatapp.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author mac
 */
public class ChatFrame extends javax.swing.JFrame {

    int height = 0;    
    int nbrPanel = 0;

  Socket _socket;
  ObjectOutputStream _send;
  ObjectInputStream _received;

  /**
   * Creates new form ChatFrame
   */
  public ChatFrame() {
    initComponents();
    for (int i = 0; i < 19; i++) {
      addComponent("", "");
    }

  }

  public void initConnection() {
    try {
      _socket = new Socket("localhost", 4001);
      _received = new ObjectInputStream(_socket.getInputStream());
      _send = new ObjectOutputStream(_socket.getOutputStream());
    } catch (Exception e) {
      Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, e);
      e.printStackTrace();
    }
  }

  public void SendMessage(String msg) {
    try {
      _send.writeObject(msg);
    } catch (Exception e) {
      System.out.println("error ");
      Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, e);
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

  public void refreshScrollPane(){
     pnlContent.setPreferredSize(new Dimension(1021,height+ (nbrPanel * 10)));
     jScrollPane1.setViewportView(pnlContent);
     jScrollPane1.revalidate();
     jScrollPane1.repaint();
  }

  public void handleSend() {
    /*
    String msg = txtMsg.getText();
    if (!msg.equals("")) {
      SendMessage(msg);
    }
    */

    addComponent("", "");
    System.out.println(height);
    jScrollPane1.setPreferredSize(new Dimension(1021, height));
    //jScrollPane1.revalidate();
   jScrollPane1.repaint();
  }

  public void addComponent(String lblText, String position) {
    JTextArea label = createNewLabel(
      "How the hell am I supposed to get a jury to believe you when I am not even sure that I do?! ",
      20
    );
    JLabel avatar = createNewIamge(22, 22);
    JPanel panel = createNewPanel(label, avatar, pnlContent);
    height+= panel.getPreferredSize().height;
    nbrPanel++;
    pnlContent.add(panel);
    pnlContent.revalidate();
    pnlContent.repaint();
    refreshScrollPane();
  }

  public JPanel createNewPanel(JTextArea label, JLabel avatar, JPanel parent) {
    JPanel panel = new JPanel();

    panel.add(avatar);
    panel.add(label);

    panel.revalidate();
    panel.repaint();
    panel.setBorder(new EmptyBorder(5, 5, 5, 5));
    panel.setSize(
      panel.getPreferredSize().width,
      panel.getPreferredSize().height
    );
    panel.setBackground(Color.RED);

    panel.setOpaque(true);

    if (parent.getComponentCount() > 0) {
      Component c = parent.getComponent(parent.getComponentCount() - 1);
      panel.setLocation(
        c.getLocation().x,
        c.getLocation().y + c.getPreferredSize().height + 10
      );
    }

    return panel;
  }

  public JLabel createNewIamge(int height, int width) {
    ImageIcon avatar = new ImageIcon(
      new ImageIcon("./src/images/harveyspecter.png")
        .getImage()
        .getScaledInstance(20, 20, Image.SCALE_DEFAULT)
    );

    JLabel label = new JLabel(avatar);
    label.setSize(width, height);
    label.setPreferredSize(new Dimension(width, height));
    label.setOpaque(true);
    return label;
  }

  public JTextArea createNewLabel(String txt, int positionX) {
    JTextArea label = new JTextArea();
    label.setBorder(new EmptyBorder(10, 10, 10, 10));
    label.setText(txt);
    label.setEditable(false);

    int width = label.getPreferredSize().width;

    if (width > 400) {
      int height = label.getPreferredSize().height;
      label.setSize(400, (width / 400) * height);
      label.setLineWrap(true);
    }

    label.setAlignmentX(50);
    label.setLocation(200, 0);

    label.setBackground(Color.decode("#435f7a"));
    label.setForeground(Color.white);
    label.setOpaque(true);

    return label;
  }

  public void open() {
    this.setTitle("Chat");
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtMsg = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlContent = new javax.swing.JPanel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        txtMsg.setText("jTextField1");

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane1.setToolTipText("");

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:

    handleSend();
  }//GEN-LAST:event_jButton1ActionPerformed

  /**
   * @param args the command line arguments
   */
  //    public static void open() {
  //        /* Set the Nimbus look and feel */
  //        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
  //        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
  //         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
  //         */
  //        try {
  //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
  //                if ("Nimbus".equals(info.getName())) {
  //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
  //                    break;
  //                }
  //            }
  //        } catch (ClassNotFoundException ex) {
  //            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  //        } catch (InstantiationException ex) {
  //            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  //        } catch (IllegalAccessException ex) {
  //            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
  //            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  //        }
  //        //</editor-fold>
  //
  //        /* Create and display the form */
  //        java.awt.EventQueue.invokeLater(new Runnable() {
  //            public void run() {
  //
  //                ChatFrame cf = new ChatFrame();
  //                cf.setTitle("Chat");
  //                cf.setResizable(false);
  //                cf.setLocationRelativeTo(null);
  //                cf.setLayout(null);
  //                cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //                cf.setVisible(true);
  //            }
  //        });
  //    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
