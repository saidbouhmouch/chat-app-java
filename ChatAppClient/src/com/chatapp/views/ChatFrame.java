/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.chatapp.views;

import com.chatapp.models.UserModel;
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
    UserModel senderUser  = new UserModel();    
    UserModel receiverUser  = new UserModel();

    
  Socket _socket;
  ObjectOutputStream _send;
  ObjectInputStream _received;

  /**
   * Creates new form ChatFrame
   */
  public ChatFrame() {
    initComponents();
    
   
    retrieveSenderData(0);
    retrieveReceiverData(1);
    retrieveSearchIcon();
            
    for (int i = 0; i < 19; i++) {
      addComponent("", (i%2 == 0)? "right" :"");
    }
    
  }
  
  
  public void retrieveSenderData(int userId){
       senderUser.getUserById(userId);
    
     lblSenderName.setText(senderUser.getFirst_name()+" "+senderUser.getLast_name());
     ImageIcon avatar = new ImageIcon(new ImageIcon(senderUser.getPicture()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    lblSenderIcon.setIcon(avatar);
    lblSenderIcon.setSize(60, 60);
    lblSenderIcon.setPreferredSize(new Dimension(60, 60));
    lblSenderIcon.setOpaque(true);
      
  }
  
  
  
  public void retrieveReceiverData(int userId){
      receiverUser.getUserById(userId);
     lblReceiverName.setText(receiverUser.getFirst_name()+" "+receiverUser.getLast_name());
     ImageIcon avatar = new ImageIcon(new ImageIcon(receiverUser.getPicture()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
     lblReceiverIcon.setIcon(avatar);
     lblReceiverIcon.setSize(60, 60);
     lblReceiverIcon.setPreferredSize(new Dimension(60, 60));
     lblReceiverIcon.setOpaque(true);
  }
  
  public void retrieveSearchIcon(){
      
     ImageIcon avatar = new ImageIcon(new ImageIcon("./src/images/search.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
     lblSearchIcon.setText("");
     lblSearchIcon.setIcon(avatar);
     lblSearchIcon.setBackground(Color.decode("#435F7A"));
     lblSearchIcon.setBorder(new EmptyBorder(0, 10, 0, 5));
     lblSearchIcon.setSize(24, 24);
     lblSearchIcon.setPreferredSize(new Dimension(24, 24));
     lblSearchIcon.setOpaque(true);
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
   
    
    if(position.equals("right")){
        int x = pnlContent.getPreferredSize().width - panel.getPreferredSize().width;
        panel.setLocation(x,height);
    }else{
         panel.setLocation(0,height);
    }
    
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
        lblSenderName = new javax.swing.JLabel();
        lblSenderIcon = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        lblSearchIcon = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblReceiverName = new javax.swing.JLabel();
        lblReceiverIcon = new javax.swing.JLabel();
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

        lblSenderName.setForeground(new java.awt.Color(255, 255, 255));
        lblSenderName.setText("jLabel1");

        panelSearch.setBackground(new java.awt.Color(67, 95, 122));

        jTextField1.setBackground(new java.awt.Color(67, 95, 122));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setToolTipText("Search");
        jTextField1.setBorder(null);

        lblSearchIcon.setForeground(new java.awt.Color(67, 95, 122));
        lblSearchIcon.setText("icon");

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLayout.createSequentialGroup()
                .addComponent(lblSearchIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1))
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addComponent(lblSearchIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(50, 70, 90));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_icon.png"))); // NOI18N
        jButton2.setText("ajouter contact");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jButton3.setBackground(new java.awt.Color(50, 70, 90));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setting.png"))); // NOI18N
        jButton3.setText("setting");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblSenderIcon)
                .addGap(18, 18, 18)
                .addComponent(lblSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenderIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        lblReceiverName.setText("Harvey Specter");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblReceiverIcon)
                .addGap(18, 18, 18)
                .addComponent(lblReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReceiverIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblReceiverIcon;
    private javax.swing.JLabel lblReceiverName;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JLabel lblSenderIcon;
    private javax.swing.JLabel lblSenderName;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
