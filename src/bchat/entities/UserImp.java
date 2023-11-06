/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.entities;

import bchat.gui.UserChatFrame;
import bchat.gui.MainUser;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;

/**
 *
 * @author Blacky
 */
public class UserImp extends UnicastRemoteObject implements IUser {

    private JTextArea chatArea;
    private int iduser;
    private String username, password, firstName, lastName;

    public UserImp() throws RemoteException{
        
    }
    
    public UserImp(JTextArea area) throws RemoteException {
        this.chatArea = area;
    }

    public UserImp(int iduser, String username, String password, String firstName, String lastName) throws RemoteException {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public void notify(String message, int senderId) throws RemoteException {
//        chatArea.append(message + "\n");
        UserChatFrame tmpFrame = MainUser.userChatFrames.get(senderId);
        tmpFrame.chatArea.append(message + "\n");
    }

}
