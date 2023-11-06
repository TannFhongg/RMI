/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.entities;

import bchat.gui.GroupChatFrame;
import bchat.gui.MainUser;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Blacky
 */
public class GroupImp extends UnicastRemoteObject implements IGroup {

    private int groupId, adminId;
    private String groupName;
    private ArrayList<Integer> groupUsers = new ArrayList();
    
    public GroupImp() throws RemoteException {
    }

    public GroupImp(int groupId, String groupName) throws RemoteException {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public GroupImp(int groupId, String groupName, int adminId) throws RemoteException {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
    
    @Override
    public void notifyGroup(String message, int groupId) throws RemoteException {
        System.out.println("group " + groupId + " of " + MainUser.user.getFirstName() + " is notified");
        GroupChatFrame tmpFrame = MainUser.groupChatFrames.get(groupId);
        tmpFrame.chatArea.append(message + "\n");
    }

    @Override
    public void addUser(int id) throws RemoteException {
        if(!groupUsers.contains(id))
            groupUsers.add(id);
    }

    @Override
    public void removeUser(int id) throws RemoteException {
        if(groupUsers.contains(id))
            groupUsers.remove(new Integer(id));
    }

    @Override
    public void removeAllUsers() throws RemoteException {
        groupUsers.clear();
    }
    
    @Override
    public ArrayList<Integer> getAllUsers() throws RemoteException {
        return groupUsers;
    }
    
}
