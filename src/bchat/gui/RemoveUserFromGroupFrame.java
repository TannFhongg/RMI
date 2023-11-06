/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.gui;

import bchat.controllers.UserController;
import bchat.controllers.UserGroupController;
import bchat.entities.IGroup;
import bchat.utilities.UserListRenderer;
import bchat.entities.UserImp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Blacky
 */
public class RemoveUserFromGroupFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form LoginFrame
     */
    public DefaultListModel<UserImp> listModel = new DefaultListModel<>();
    private int groupId;

    public RemoveUserFromGroupFrame(int groupId) {
        try {
            initComponents();
            this.groupId = groupId;

            // remove the north pane of this frame
            BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
            bi.setNorthPane(null);
            setLocation(0, 0);

            // get group members
            IGroup group = MainUser.userGroups.get(this.groupId);
            ArrayList<Integer> groupMembersIds = UserGroupController.INSTANCE.getGroupUsersId(groupId);
//            ArrayList<Integer> groupMembersIds = MainUser.proxy.getGroupUsersIDs(this.groupId);

            // display users that can be revoved
            for (UserImp remoteUser : UserController.INSTANCE.findAll()) {
                if (groupMembersIds.contains(remoteUser.getIduser()) && remoteUser.getIduser() != MainUser.user.getIduser()) {
                    this.addToJList(remoteUser);
                    System.out.println(remoteUser.getFirstName() + " added to jlist");
                }
            }

            usersJlist.setCellRenderer(new UserListRenderer());

            RemoveUserFromGroupFrame thisFrame = this;

            // mouse listener to add members
            usersJlist.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        e.consume();

                        if (MainUser.loggedIN == false) {
                            return;
                        }

                        UserImp remoteUser = usersJlist.getSelectedValue();
                        System.out.println("remoteUserID: " + remoteUser.getIduser());

                        int choice = JOptionPane.showConfirmDialog(thisFrame,
                                "<html><b>Remove \"" + remoteUser.getFirstName() + "\"?</b></html>",
                                "Confirm Remove Action",
                                JOptionPane.YES_NO_OPTION);

                        switch (choice) {
                            case JOptionPane.YES_OPTION:
                                try {
                                    // remove user from group (locally)
                                    group.removeUser(remoteUser.getIduser());
                                    
                                    // update hashmap of userGroups (locally)
                                    MainUser.userGroups.put(thisFrame.groupId, group);
                                    
                                    // remove user from group (server)
                                    String idComb = thisFrame.groupId + "-" + remoteUser.getIduser();
                                    MainUser.proxy.deleteGroup(idComb);
                                    
                                    // remove user from group (db)
                                    UserGroupController.INSTANCE.removeUserFromGroup(remoteUser.getIduser(), thisFrame.groupId);

                                    // display remove notice
                                    MainUser.groupChatFrames.get(groupId).chatArea.append(" *** You removed " + remoteUser.getFirstName() + " " + remoteUser.getLastName() + " ***\n");
                                    
                                    // send notice to all group members
                                    String noticeMessage = "*** " + remoteUser.getFirstName() + " " + remoteUser.getLastName() + " was removed ***";
                                    String noticeIdComb = groupId + "-" + MainUser.user.getIduser();
                                    MainUser.proxy.sendToGroup(noticeMessage , noticeIdComb);
                                    
                                } catch (RemoteException | SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }

                                thisFrame.dispose();

                                RemoveUserFromGroupFrame frame = new RemoveUserFromGroupFrame(thisFrame.groupId);
                                MainUser.desk2.add(frame);
                                frame.setLocation(317, 0);
                                frame.setVisible(true);
                                
                                break;

                            case JOptionPane.NO_OPTION:
                                break;

                            default:
                                break;
                        }
                    }
                }
            });

        } catch (RemoteException | SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addToJList(UserImp user) {
        listModel.addElement(user);
        usersJlist.validate();
    }

    public void clearJList() {
        listModel.clear();
        usersJlist.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersJlist = new javax.swing.JList<>();
        viewAddMembers = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();

        setBorder(null);
        setEnabled(false);

        jPanel1.setBackground(new java.awt.Color(5, 100, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 410));

        usersJlist.setBackground(new java.awt.Color(5, 100, 153));
        usersJlist.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        usersJlist.setForeground(new java.awt.Color(255, 255, 255));
        usersJlist.setModel(listModel);
        usersJlist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        usersJlist.setToolTipText("Double-Click to Remove");
        jScrollPane1.setViewportView(usersJlist);

        viewAddMembers.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        viewAddMembers.setForeground(new java.awt.Color(255, 255, 255));
        viewAddMembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewAddMembers.setText("Remove a Member");
        viewAddMembers.setToolTipText("");

        cancelBtn.setBackground(new java.awt.Color(5, 100, 153));
        cancelBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("OK");
        cancelBtn.setBorder(null);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(viewAddMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addGap(97, 97, 97))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewAddMembers, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelBtn)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JList<UserImp> usersJlist;
    private javax.swing.JLabel viewAddMembers;
    // End of variables declaration//GEN-END:variables
}
