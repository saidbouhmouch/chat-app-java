/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.models;

import com.chatapp.models.ConnexionModel;
import java.sql.*;

/**
 *
 * @author mac
 */
public class UserModel {
    
    private int id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String picture;
    private boolean status;
    
    
     /*  DATABASE */
    
    private PreparedStatement st = null;
    private ResultSet rs = null;
    private Connection con = null;

    public UserModel() {
    }

    public UserModel( String username, String password, String first_name, String last_name, String picture, boolean status) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.picture = picture;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
         this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPicture() {
        return picture;
    }

    public boolean getStatus() {
        return status;
    }

  

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    public int insert(){
    
            int save = 0;
        con = ConnexionModel.getCon();
        String insert = "INSERT INTO users(username,password,first_name,last_name,picture,status)VALUES(?,?,?,?,?,?)";
 
        try {
            st = con.prepareStatement(insert);
            st.setString(1, this.getUsername());
            st.setString(2, this.getPassword());
            st.setString(3, this.getFirst_name());           
            st.setString(4, this.getLast_name());
            st.setString(5, this.getPicture());
            st.setBoolean(6, this.getStatus());
            save = st.executeUpdate();
            st.close();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return save;
    
    }
    
    public int authenticate(String login, String pass){
         
        con = ConnexionModel.getCon();
        String select = "SELECT * FROM users where username=? and password=?";
        int _login = 0;
        
         try {
            st = con.prepareStatement(select);
            st.setString(1, login);
            st.setString(2, pass);
            rs = st.executeQuery();
            while (rs.next()) {
                this.setId(rs.getInt("id"));
               _login = 1;
            }
            st.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
         return _login;
    }
    
    
    
    
    
    
    
     
    
    
    
            
            
       
    
}
