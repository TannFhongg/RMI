/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.entities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Blacky
 */

public class ServerImp extends UnicastRemoteObject implements IServer {

    private final HashMap<Integer, IUser> connectedUsers = new HashMap();
    private final HashMap<String, IGroup> allGroups = new HashMap();

    // first string: is a combination of senderId-receiverId
    private final HashMap<String, String> offlineMessages = new HashMap();

    private final HashMap<String, String> offlineGroupMessages = new HashMap();

    public ServerImp() throws RemoteException {

    }

//    @Override
//    public List<UserImp> getAllUsersFromDB() throws RemoteException {
//        try {
//            System.out.println("Fetching users from db...");
//            return UserController.INSTANCE.findAll();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
    @Override
    public void reconnect(int id, IUser user) throws RemoteException {
        connectedUsers.put(id, user);
        System.out.println("User " + id + " Connected");
//        String message = offlineMessages.get(id);
//        if (message != null) {
////            user.notify(message);
//            offlineMessages.put(id, null);
//        }
    }

    @Override
    public void disconnect(int id) throws RemoteException {
        connectedUsers.remove(id);
        System.out.println("User " + id + " disconnected");
    }

    @Override
    public void send(String message, int receiverId, int senderId) throws RemoteException {
        IUser user = connectedUsers.get(receiverId);
        if (user != null) {
            // user online
            user.notify(message, senderId);
        } else {
            //user offline
            String oldMessages = offlineMessages.get(receiverId + "-" + senderId);
            if (oldMessages == null) {
                oldMessages = "";
            }
            oldMessages += message + "\n";
            offlineMessages.put(receiverId + "-" + senderId, oldMessages);
        }
    }

    @Override
    public boolean isConnected(int idUser) {
        return connectedUsers.containsKey(idUser);
    }

    @Override
    public ArrayList<Integer> getOnlineUsersIDs() throws RemoteException {
        ArrayList<Integer> ls = new ArrayList();
        connectedUsers.keySet().forEach((userId) -> {
            ls.add(userId);
        });
        return ls;
    }
    
    @Override
    public String getOfflineMessages(String idComb) throws RemoteException {
        return offlineMessages.get(idComb);
    }

    @Override
    public void clearOfflineMessages(String idComb) throws RemoteException {
        offlineMessages.put(idComb, null);
    }

    // Group Services
    @Override
    public void createGroup(String idGroupComb, IGroup group) throws RemoteException {
        // if group already exist -> update
        allGroups.put(idGroupComb, group);
    }

    @Override
    public void deleteGroup(String idGroupComb) throws RemoteException {
        if (allGroups.containsKey(idGroupComb)) {
            allGroups.remove(idGroupComb);
        }
    }

    @Override
    public void deleteAllUserGroups(int idUser) throws RemoteException {
        ArrayList<String> toRemove = new ArrayList();
        for (String key : allGroups.keySet()) {
            String[] idComb = key.split("-");
            int userId = Integer.parseInt(idComb[1]);

            if (idUser == userId) {
                toRemove.add(key);
            }
        }
        allGroups.keySet().removeAll(toRemove);
        
    }
    
    @Override
    public boolean groupExist(String idComb) throws RemoteException {
        return allGroups.containsKey(idComb);
    }

    @Override
    public ArrayList<Integer> getUserGroupIDs(int idUser) throws RemoteException {
        ArrayList<Integer> ls = new ArrayList();
        for (String key : allGroups.keySet()) {
            String[] idComb = key.split("-");
            int groupId = Integer.parseInt(idComb[0]);
            int userId = Integer.parseInt(idComb[1]);

            if (idUser == userId) {
                ls.add(groupId);
            }
        }
        return ls;
    }

    @Override
    public ArrayList<Integer> getGroupUsersIDs(int idGroup) throws RemoteException {
        ArrayList<Integer> ls = new ArrayList();
        for (String key : allGroups.keySet()) {
            String[] idComb = key.split("-");
            int groupId = Integer.parseInt(idComb[0]);
            int userId = Integer.parseInt(idComb[1]);

            if (idGroup == groupId) {
                ls.add(userId);
            }
        }
        return ls;
    }

    @Override
    public void sendToGroup(String message, String idGroupComb) throws RemoteException {
        String[] idComb = idGroupComb.split("-");
        int groupId = Integer.parseInt(idComb[0]);
        int senderId = Integer.parseInt(idComb[1]);
        System.out.println("group: " + groupId);
        System.out.println("sender " + senderId);

        for (int userId : getGroupUsersIDs(groupId)) {
            // dont send the message to the initial sender -> skip
            if (userId == senderId) {
                System.out.println(userId + " is sender -> skip");
                continue;
            }
            if (connectedUsers.containsKey(userId)) {
                // user online
                System.out.println(userId + " is online -> send");
                allGroups.get(groupId + "-" + userId).notifyGroup(message, groupId);
                System.out.println("send to: " + groupId + "-" + userId);
            } else {
                // user offline
                String oldMessages = offlineGroupMessages.get(groupId + "-" + userId);
                if (oldMessages == null) {
                    oldMessages = "";
                }
                oldMessages += message + "\n";
                offlineGroupMessages.put(groupId + "-" + userId, oldMessages);
            }
        }
    }

    @Override
    public String getGroupOfflineMessages(String idComb) throws RemoteException {
        return offlineGroupMessages.get(idComb);
    }

    @Override
    public void clearGroupOfflineMessages(String idComb) throws RemoteException {
        // make sure that all received message before clearing
        for (int userId : allGroups.get(idComb).getAllUsers()) {
            // if at least one user is not online -> dont clear
            if (!connectedUsers.containsKey(userId)) {
                return;
            }
        }
        offlineGroupMessages.put(idComb, null);
    }



}
