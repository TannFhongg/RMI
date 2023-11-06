/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.utilities;

import bchat.controllers.GroupController;
import bchat.controllers.UserController;
import bchat.entities.GroupImp;
import bchat.entities.IGroup;
import bchat.entities.UserImp;
import bchat.gui.GroupChatFrame;
import bchat.gui.MainUser;
import bchat.gui.UserChatFrame;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Blacky
 */
public class ParallelTask implements Runnable {

    @Override
    public void run() {
        try {
            while (MainUser.loggedIN == true) {
                checkUsersListChanges();
                Thread.sleep(5000);
                checkGrpAddChanges();
                checkGrpRemoveChanges();
                Thread.sleep(5000);
            }
            // thread killed on logout
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void checkUsersListChanges() {
        try {
            for (UserImp remoteUser : UserController.INSTANCE.findAll()) {
                // if user not in list -> add to list
                if (remoteUser.getIduser() != MainUser.user.getIduser() && !MainUser.allUsersFrame.listModel.contains(remoteUser)) {

                    // create a chatFrame for remoteUser
                    if (!MainUser.userChatFrames.containsKey(remoteUser.getIduser())) {
                        // add remoteUser to Jlist
                        MainUser.allUsersFrame.addToJList(remoteUser);

                        // create frame
                        UserChatFrame tmpFrame = new UserChatFrame(remoteUser.getIduser());

                        // add it to desktopPane (desk2)
                        MainUser.desk2.add(tmpFrame);

                        // add it to hashMap of all userChatFrames
                        MainUser.userChatFrames.put(remoteUser.getIduser(), tmpFrame);
                        System.out.println("new userChatFrame added for " + remoteUser.getFirstName());
                    }
                }
            }
        } catch (SQLException | RemoteException ex) {
            System.out.println(ex.getMessage());
        }

        MainUser.allUsersFrame.refresh();

    }

    private void checkGrpAddChanges() {
        try {
            // get user groups IDs from server
            for (int groupId : MainUser.proxy.getUserGroupIDs(MainUser.user.getIduser())) {
                // check if groupId exists locally
                if (!MainUser.userGroups.containsKey(groupId)) {
                    // no -> user was added to this group recently
                    GroupImp group = GroupController.INSTANCE.findById(groupId);
                    IGroup igroup = group;
                    System.out.println("[" + MainUser.user.getFirstName() + "] is now in group [" + group.getGroupName() + "]:");

                    // add to hashmap userGroups (locally)
                    MainUser.userGroups.put(groupId, igroup);

                    // add to allgroups (server)
                    String idComb = groupId + "-" + MainUser.user.getIduser();
                    MainUser.proxy.createGroup(idComb, igroup);
                    System.out.println("added " + idComb + " to server");

                    // add to Jlist
                    MainUser.allGroupsFrame.addToJList(group);

                    // add to chatFrame
//                    if (!MainUser.groupChatFrames.containsKey(groupId)) {
                        // create frame
                        GroupChatFrame tmpFrame = new GroupChatFrame(groupId);

                        tmpFrame.adminLabel.setVisible(false);
                        
                        // add it to desktopPane (desk2)
                        MainUser.desk2.add(tmpFrame);

                        // add it to hashMap of all groupChatFrames
                        MainUser.groupChatFrames.put(groupId, tmpFrame);
                        System.out.println("new groupChatFrame added for " + group.getGroupName());

                        // display add notice
                        MainUser.groupChatFrames.get(groupId).chatArea.append(" *** You were added ***\n");
//                    }
                }

            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void checkGrpRemoveChanges() {
        try {
            // get user groups IDs locally
            ArrayList<Integer> userGroupIDs = MainUser.proxy.getUserGroupIDs(MainUser.user.getIduser());
            ArrayList<Integer> toRemove = new ArrayList();
            for (int groupId : MainUser.userGroups.keySet()) {
                // check if groupId exists in server
                if (!userGroupIDs.contains(groupId)) {
                    // no -> user was removed to this group recently
                    // remove groupChatFrame
                    GroupChatFrame tmpFrame = MainUser.groupChatFrames.get(groupId);
//                    tmpFrame.setVisible(false);
                    tmpFrame.chatArea.append(" *** You were removed ***\n");
                    MainUser.desk2.remove(tmpFrame);
                    tmpFrame.dispose();
                    System.out.println("removed chatFrame of group " + groupId);

                    // remove group from jlist
                    MainUser.allGroupsFrame.removeFromJList(groupId);
                    System.out.println("removed group from jlist" + groupId);

                    // add to toRemove list
                    toRemove.add(groupId);  
                }
            }
            // update hashmap of userGroups (locally)
            MainUser.userGroups.keySet().removeAll(toRemove);
            
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
