/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.gui;

import bchat.controllers.GroupController;
import bchat.controllers.UserGroupController;
import bchat.entities.GroupImp;
import bchat.entities.IGroup;
import bchat.utilities.MyColors;
import java.awt.Color;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Blacky
 */
public class CreateGroupFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form LoginFrame
     */
    public CreateGroupFrame(int userId) {
        initComponents();

        // remove the north pane of this frame
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        // change the color of jOptionPane
        UIManager.put("OptionPane.background", new Color(5, 100, 153));
        UIManager.getLookAndFeelDefaults().put("Panel.background", new Color(5, 100, 153));
        UIManager.put("control", new Color(5, 100, 153));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        this.requestFocusInWindow();
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
        jLabel1 = new javax.swing.JLabel();
        groupName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addGroup = new javax.swing.JButton();
        cancel = new javax.swing.JLabel();

        setBorder(null);
        setEnabled(false);

        jPanel1.setBackground(new java.awt.Color(5, 100, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create a new Group");

        groupName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        groupName.setForeground(new java.awt.Color(204, 204, 204));
        groupName.setText("Group Name");
        groupName.setToolTipText("");
        groupName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        groupName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                groupNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                groupNameFocusLost(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/group2.png"))); // NOI18N
        jLabel6.setToolTipText("Group Name");

        addGroup.setBackground(new java.awt.Color(5, 100, 153));
        addGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/addGroup.png"))); // NOI18N
        addGroup.setToolTipText("Create a Group");
        addGroup.setBorder(null);
        addGroup.setBorderPainted(false);
        addGroup.setContentAreaFilled(false);
        addGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setText("Cancel");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addGroup))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(cancel)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addGroup)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void groupNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_groupNameFocusGained
        if (groupName.getText().equals("Group Name")) {
            groupName.setText("");
            groupName.setForeground(MyColors.LIGHT_BLUE);
        }
    }//GEN-LAST:event_groupNameFocusGained

    private void groupNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_groupNameFocusLost
        if (groupName.getText().equals("")) {
            groupName.setText("Group Name");
            groupName.setForeground(MyColors.GRAY);
        }
    }//GEN-LAST:event_groupNameFocusLost

    private void addGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupActionPerformed
        String groupN = groupName.getText();

        if (groupN == null || groupN.equals("") || groupN.equals("Group Name")) {
            JOptionPane.showMessageDialog(this,
                    "<html><b>Group Name field required</b></html>",
                    "Entry Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "<html><b>Create group \"" + groupN + "\"?</b></html>\n",
                "Confirm Group Creation",
                JOptionPane.YES_NO_OPTION);

        switch (choice) {
            case JOptionPane.YES_OPTION:
                try {
                    // add group to db
                    int adminId = MainUser.user.getIduser();
                    int groupId = GroupController.INSTANCE.create(groupN, adminId);
                    GroupImp group = new GroupImp(groupId, groupN, adminId);
                    group.addUser(adminId);

                    // add group to server
                    IGroup newGroup = group;
                    String idComb = groupId + "-" + adminId;
                    MainUser.proxy.createGroup(idComb, newGroup);
                    MainUser.userGroups.put(groupId, newGroup);

                    // add admin as a group member in db
                    UserGroupController.INSTANCE.addUserToGroup(adminId, groupId);

                    // add to Jlist
                    MainUser.allGroupsFrame.addToJList(group);

                    // add to chatFrame
                    if (!MainUser.groupChatFrames.containsKey(groupId)) {
                        // create frame
                        GroupChatFrame tmpFrame = new GroupChatFrame(groupId);

                        // add it to desktopPane (desk2)
                        MainUser.desk2.add(tmpFrame);

                        // add it to hashMap of all groupChatFrames
                        MainUser.groupChatFrames.put(groupId, tmpFrame);

                        // display add notice
                        MainUser.groupChatFrames.get(groupId).chatArea.append(" *** You created " + group.getGroupName() + " ***\n");

                        this.dispose();
                    }
                } catch (SQLException | RemoteException ex) {
                    System.out.println(ex.getMessage());
                }
                break;

            case JOptionPane.NO_OPTION:
                break;

            default:
                break;
        }

    }//GEN-LAST:event_addGroupActionPerformed

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_cancelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGroup;
    private javax.swing.JLabel cancel;
    private javax.swing.JTextField groupName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
