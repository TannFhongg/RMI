/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.entities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Blacky
 */
public interface IGroup extends Remote {

    public void notifyGroup(String message, int groupId) throws RemoteException;
    
    public void addUser(int id) throws RemoteException;
    
    public void removeUser(int id) throws RemoteException;
    
    public void removeAllUsers() throws RemoteException;
    
    public ArrayList<Integer> getAllUsers() throws RemoteException;

}
