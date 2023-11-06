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
public interface IServer extends Remote {
    
//    public List<UserImp> getAllUsersFromDB() throws RemoteException;
    
    public void reconnect(int id, IUser user) throws RemoteException;

    public void disconnect(int id) throws RemoteException;

    public void send(String message, int remoteId, int senderId) throws RemoteException;
    
    public boolean isConnected(int idUser) throws RemoteException;

    public ArrayList<Integer> getOnlineUsersIDs() throws RemoteException;
    
    public String getOfflineMessages(String idComb) throws RemoteException;
    
    public void clearOfflineMessages(String idComb) throws RemoteException;
    
    // Groups Services
    public void createGroup(String idGroupComb, IGroup group) throws RemoteException;
    
    public void deleteGroup(String idGroupComb) throws RemoteException;
    
    public void deleteAllUserGroups(int idUser) throws RemoteException;
    
    public boolean groupExist(String idComb) throws RemoteException;
    
    public ArrayList<Integer> getUserGroupIDs(int idUser) throws RemoteException;
    
    public ArrayList<Integer> getGroupUsersIDs(int idGroup) throws RemoteException;
    
    public void sendToGroup(String message, String idGroupComb) throws RemoteException;
    
    public String getGroupOfflineMessages(String idComb) throws RemoteException;

    public void clearGroupOfflineMessages(String idComb) throws RemoteException;


}
