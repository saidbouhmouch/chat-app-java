/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.chatapp.views;

import com.chatapp.service.GenerateComponent;
import com.chatapp.models.UserModel;
import com.chatapp.service.ClientService;
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
import javax.swing.border.LineBorder;

/**
 *
 * @author mac
 */
public class ChatFrame extends javax.swing.JFrame {

  int heightMessages = 0;
  int nbrPanelMessages = 0;

  int nbrPanelConversation = 0;
  int heightPanelConversation = 0;

  UserModel senderUser = new UserModel();
  UserModel receiverUser = new UserModel();
  GenerateComponent generateComponent = new GenerateComponent();
  ClientService clientService;



  /**
   * Creates new form ChatFrame
   */
  public ChatFrame() {
    initComponents();
    
    clientService = ClientService.getInstance();

    retrieveUserData(senderUser, 0, lblSenderName, lblSenderIcon);
    retrieveUserData(receiverUser, 1, lblReceiverName, lblReceiverIcon);

    retrieveSearchIcon();

    for (int i = 1; i < 19; i++) {
      addMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ..", (i % 2 == 0) ? "right" : "");
      
    }
    
    addConversation("Harvey Specter","./src/images/harveyspecter.png","Lorem ipsum dolor sit amet");    
    addConversation("Louis Litt","./src/images/3.png","Lorem ipsum dolor sit amet");    
    addConversation("Daniel Hardman","danielhardman.png","Lorem ipsum dolor sit amet..");
    addConversation("Donna Paulsen","./src/images/donnapaulsen.png","Lorem ipsum dolor sit amet...");
    addConversation("Harold Gunderson","./src/images/haroldgunderson.png","Lorem ipsum dolor sit amet...");
    addConversation("Jessica Pearson","./src/images/jessicapearson.png","Lorem ipsum dolor sit amet...");
    addConversation("Rachel Zane","./src/images/rachelzane.png","Lorem ipsum dolor sit amet...");

  }

  public void retrieveUserData(
    UserModel user,
    int userId,
    JLabel lblName,
    JLabel lblIcon
  ) {
    user.getUserById(userId);

    lblName.setText(user.getFirst_name() + " " + user.getLast_name());
    ImageIcon avatar = new ImageIcon(
      new ImageIcon(user.getPicture())
        .getImage()
        .getScaledInstance(60, 60, Image.SCALE_DEFAULT)
    );

    lblIcon.setSize(60, 60);
    lblIcon.setPreferredSize(new Dimension(60, 60));
    lblIcon.setIcon(avatar);
    lblIcon.setOpaque(true);
  }

  public void retrieveSearchIcon() {
    ImageIcon avatar = new ImageIcon(
      new ImageIcon("./src/images/search.png")
        .getImage()
        .getScaledInstance(24, 24, Image.SCALE_DEFAULT)
    );
    lblSearchIcon.setText("");
    lblSearchIcon.setIcon(avatar);
    lblSearchIcon.setBackground(Color.decode("#435F7A"));
    lblSearchIcon.setBorder(new EmptyBorder(0, 10, 0, 5));
    lblSearchIcon.setSize(24, 24);
    lblSearchIcon.setPreferredSize(new Dimension(24, 24));
    lblSearchIcon.setOpaque(true);
  }

  

  public void refreshScrollPane() {
    pnlContent.setPreferredSize(new Dimension(1000, heightMessages + (nbrPanelMessages * 10)));
    jScrollPane1.setViewportView(pnlContent);
    jScrollPane1.revalidate();
    jScrollPane1.repaint();
  }
  
   public void refreshScrollPaneConversation(){
    pnlConversation.setPreferredSize(new Dimension(300, heightPanelConversation + (nbrPanelConversation * 10)));
    scrollPaneConversation.setViewportView(pnlConversation);
    scrollPaneConversation.revalidate();
    scrollPaneConversation.repaint();
  }

  public void handleSend() {
    /*
    String msg = txtMsg.getText();
    if (!msg.equals("")) {
      SendMessage(msg);
    }
    */

    addMessage("", "");
    jScrollPane1.setPreferredSize(new Dimension(1021, heightMessages));
    //jScrollPane1.revalidate();
    jScrollPane1.repaint();
  }

  public void addMessage(String lblText, String position) {
      
      String img = "./src/images/mikeross.png";
      
      if (position.equals("right")) {
      img ="./src/images/harveyspecter.png";
    }
      
    JTextArea label = generateComponent.newMessageLabel(
      lblText,
      20
    );

    JLabel avatar = generateComponent.newAvatarIcon(
      22,
      22,
      img
    );
    
    JPanel panel = generateComponent.newMessagePanel(label, avatar, pnlContent);

    if (position.equals("right")) {
      int x =
        pnlContent.getPreferredSize().width - panel.getPreferredSize().width;
      panel.setLocation(x, heightMessages);
    } else {
      panel.setLocation(0, heightMessages);
    }

    heightMessages += panel.getPreferredSize().height;
    nbrPanelMessages++;
    pnlContent.add(panel);
    pnlContent.revalidate();
    pnlContent.repaint();
    refreshScrollPane();
  }
  
  
  public void addConversation(String userName,String img,String lastMsg) {
      
    JTextArea conversation = generateComponent.newConversationLabel(
      userName,
      lastMsg,
      20
    );
    

    JLabel avatar = generateComponent.newAvatarIcon(
      22,
      22,
      img
    );
    
    JPanel panel = generateComponent.newConversationPanel(conversation, avatar, pnlConversation);

    heightPanelConversation += panel.getPreferredSize().height;
    nbrPanelConversation++;
    pnlConversation.add(panel);
    pnlConversation.revalidate();
    pnlConversation.repaint();
    refreshScrollPaneConversation();
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
        scrollPaneConversation = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        pnlConversation = new javax.swing.JPanel();
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

        pnlConversation.setBackground(new java.awt.Color(44, 62, 80));

        javax.swing.GroupLayout pnlConversationLayout = new javax.swing.GroupLayout(pnlConversation);
        pnlConversation.setLayout(pnlConversationLayout);
        pnlConversationLayout.setHorizontalGroup(
            pnlConversationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );
        pnlConversationLayout.setVerticalGroup(
            pnlConversationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConversation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConversation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scrollPaneConversation.setViewportView(jPanel4);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneConversation)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneConversation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        txtMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMsgActionPerformed(evt);
            }
        });

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

    private void txtMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMsgActionPerformed

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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblReceiverIcon;
    private javax.swing.JLabel lblReceiverName;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JLabel lblSenderIcon;
    private javax.swing.JLabel lblSenderName;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlConversation;
    private javax.swing.JScrollPane scrollPaneConversation;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
