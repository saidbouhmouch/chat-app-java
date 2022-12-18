package com.chatapp.service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author mac
 */
public class GenerateComponent {

  public JLabel newAvatarIcon(int height, int width, String path) {
    ImageIcon avatar = new ImageIcon(
      new ImageIcon(path)
        .getImage()
        .getScaledInstance(width, height, Image.SCALE_DEFAULT)
    );

    JLabel label = new JLabel(avatar);
    label.setSize(width, height);
    LineBorder line = new LineBorder(Color.BLACK, 1, true);
    label.setBorder(line);
    label.setPreferredSize(new Dimension(width, height));
    label.setOpaque(true);
    return label;
  }

  public JTextArea newMessageLabel(String txt, int positionX) {
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

  public JPanel newMessagePanel(JTextArea label, JLabel avatar, JPanel parent) {
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

  public JTextArea newConversationLabel(String userName,String LastMsg,int positionX ) {
      
    JTextArea label = new JTextArea();
    label.setBorder(new EmptyBorder(10, 10, 10, 10));
    label.append(userName + "\n");
    label.append(LastMsg);
    label.setEditable(false);
    label.setSize(270, 80);
    label.setPreferredSize(new Dimension(270, 50));
    label.setAlignmentX(50);
    label.setLocation(200, 0);
    label.setBackground(Color.decode("#435f7a"));
    label.setForeground(Color.white);
    label.setOpaque(true);
    return label;
    
  }

  public JPanel newConversationPanel(JTextArea label,JLabel avatar,JPanel parent) {
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
}
