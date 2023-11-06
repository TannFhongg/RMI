/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.gui;

import bchat.entities.IGroup;
import bchat.utilities.MyColors;
import bchat.entities.IServer;
import bchat.entities.UserImp;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Blacky
 */
public class MainUser extends javax.swing.JFrame {

    /**
     * Creates new form MainUser
     */
    public static IServer proxy;
    public static UserImp user;
    public static HashMap<Integer, IGroup> userGroups = new HashMap();

    public static LoginFrame loginFrame = new LoginFrame();
    public static SignupFrame signupFrame = new SignupFrame();
    public static UserListFrame allUsersFrame = new UserListFrame();
    public static GroupListFrame allGroupsFrame = new GroupListFrame();
    public static BroadcastFrame broadcastFrame = new BroadcastFrame();
    public static SettingFrame settingFrame = new SettingFrame();
    public static WallpaperFrame wallpaperFrame = new WallpaperFrame();

    public static HashMap<Integer, UserChatFrame> userChatFrames = new HashMap();
    public static HashMap<Integer, GroupChatFrame> groupChatFrames = new HashMap();
    public static boolean loggedIN = false;

    public MainUser() {
        initComponents();
        this.setTitle("BChat - Login");
        this.setResizable(false);

        // check if registry is created by checking if registry.txt file exist
        File regFile = new File("registry.txt");
        if (!regFile.exists()) {
            // file does not exist -> registry off
            JOptionPane.showMessageDialog(this,
                    "<html><b>Registry not found! Please run it then try again.</b></html>\n",
                    "Registry Missing",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // check if server is created
        File serverFile = new File("server.txt");
        if (!serverFile.exists()) {
            // file does not exist -> server off
            JOptionPane.showMessageDialog(this,
                    "<html><b>Server down! Please run it then try again.</b></html>\n",
                    "Server Missing",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        desk1.add(loginFrame);
        desk1.add(signupFrame);
        desk1.add(allUsersFrame);
        desk1.add(allGroupsFrame);
        desk1.add(broadcastFrame);
        desk1.add(settingFrame);

        desk2.add(wallpaperFrame);

        loginFrame.setVisible(true);
        wallpaperFrame.setVisible(true);

        chatBtn.setVisible(false);
        groupBtn.setVisible(false);
        logoutBtn.setVisible(false);
        broadcastBtn.setVisible(false);
        settingBtn.setVisible(false);

        try {
            user = new UserImp();
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

        JFrame thisFrame = this;

        // handle close operation (disconnect user when x button is pressed)
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int choice = JOptionPane.showConfirmDialog(thisFrame,
                        "<html><b>Close Application?</b></html>",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);

                switch (choice) {
                    case JOptionPane.YES_OPTION:
                        if (!loginFrame.isVisible() && !signupFrame.isVisible()) {
                            try {
                                // disconnecting user
                                proxy.disconnect(user.getIduser());
                            } catch (RemoteException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        System.exit(0);

                    case JOptionPane.NO_OPTION:
                        break;

                    default:
                        break;
                }

            }
        });
    }

    public static void hideChatComponents() {
        userChatFrames.values().forEach((frame) -> {
            frame.setVisible(false);
        });
        allUsersFrame.setVisible(false);
    }

    public static void showChatComponents() {
        allUsersFrame.setVisible(true);
    }

    public static void hideGroupComponents() {
        groupChatFrames.values().forEach((frame) -> {
            frame.setVisible(false);
            frame.groupMenuFrame.setVisible(false);
        });
        allGroupsFrame.setVisible(false);
    }

    public static void showGroupComponents() {
        allGroupsFrame.setVisible(true);
    }

    public static void hideBroadcastComponents() {
        broadcastFrame.setVisible(false);
    }

    public static void showBroadcastComponents() {
        broadcastFrame.setVisible(true);
    }

    public static void hideSettingComponents() {
        settingFrame.setVisible(false);
    }

    public static void showSettingComponents() {
        settingFrame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionPane = new javax.swing.JPanel();
        homeBtn = new javax.swing.JButton();
        groupBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        broadcastBtn = new javax.swing.JButton();
        settingBtn = new javax.swing.JButton();
        chatBtn = new javax.swing.JButton();
        desk1 = new javax.swing.JDesktopPane();
        desk2 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        optionPane.setBackground(new java.awt.Color(8, 151, 230));

        homeBtn.setBackground(new java.awt.Color(5, 100, 153));
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/home.png"))); // NOI18N
        homeBtn.setBorder(null);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setOpaque(true);
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        groupBtn.setBackground(new java.awt.Color(8, 151, 230));
        groupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/group2.png"))); // NOI18N
        groupBtn.setBorder(null);
        groupBtn.setBorderPainted(false);
        groupBtn.setContentAreaFilled(false);
        groupBtn.setOpaque(true);
        groupBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupBtnMouseClicked(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(8, 151, 230));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/logout.png"))); // NOI18N
        logoutBtn.setBorder(null);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setOpaque(true);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        broadcastBtn.setBackground(new java.awt.Color(8, 151, 230));
        broadcastBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/broadcast.png"))); // NOI18N
        broadcastBtn.setBorder(null);
        broadcastBtn.setBorderPainted(false);
        broadcastBtn.setContentAreaFilled(false);
        broadcastBtn.setOpaque(true);
        broadcastBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                broadcastBtnMouseClicked(evt);
            }
        });

        settingBtn.setBackground(new java.awt.Color(8, 151, 230));
        settingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/setting.png"))); // NOI18N
        settingBtn.setBorder(null);
        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setOpaque(true);
        settingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingBtnMouseClicked(evt);
            }
        });
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

        chatBtn.setBackground(new java.awt.Color(8, 151, 230));
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bchat/res/chat.png"))); // NOI18N
        chatBtn.setBorder(null);
        chatBtn.setBorderPainted(false);
        chatBtn.setContentAreaFilled(false);
        chatBtn.setOpaque(true);
        chatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout optionPaneLayout = new javax.swing.GroupLayout(optionPane);
        optionPane.setLayout(optionPaneLayout);
        optionPaneLayout.setHorizontalGroup(
            optionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPaneLayout.createSequentialGroup()
                .addGroup(optionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(optionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(optionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(groupBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(broadcastBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(settingBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        optionPaneLayout.setVerticalGroup(
            optionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPaneLayout.createSequentialGroup()
                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(broadcastBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        desk1.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout desk1Layout = new javax.swing.GroupLayout(desk1);
        desk1.setLayout(desk1Layout);
        desk1Layout.setHorizontalGroup(
            desk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        desk1Layout.setVerticalGroup(
            desk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desk2.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout desk2Layout = new javax.swing.GroupLayout(desk2);
        desk2.setLayout(desk2Layout);
        desk2Layout.setHorizontalGroup(
            desk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        desk2Layout.setVerticalGroup(
            desk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(optionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desk2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desk1)
            .addComponent(desk2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtnMouseClicked
        chatBtn.setBackground(MyColors.DARK_BLUE);
        groupBtn.setBackground(MyColors.LIGHT_BLUE);
        broadcastBtn.setBackground(MyColors.LIGHT_BLUE);
        settingBtn.setBackground(MyColors.LIGHT_BLUE);

        showChatComponents();
        hideBroadcastComponents();
        hideGroupComponents();
        hideSettingComponents();
    }//GEN-LAST:event_chatBtnMouseClicked

    private void groupBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupBtnMouseClicked
        groupBtn.setBackground(MyColors.DARK_BLUE);
        chatBtn.setBackground(MyColors.LIGHT_BLUE);
        broadcastBtn.setBackground(MyColors.LIGHT_BLUE);
        settingBtn.setBackground(MyColors.LIGHT_BLUE);

        showGroupComponents();
        hideChatComponents();
        hideBroadcastComponents();
        hideSettingComponents();
    }//GEN-LAST:event_groupBtnMouseClicked

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        int choice = JOptionPane.showConfirmDialog(this,
                "<html><b>You're about to logout from your account.</b></html>\n"
                + "<html><b>Continue?</b></html>",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        switch (choice) {
            case JOptionPane.YES_OPTION:
                try {
                    // disconnecting user
                    proxy.disconnect(user.getIduser());
                } catch (RemoteException ex) {
                    System.out.println(ex.getMessage());
                }

                loggedIN = false;

                // adjust the view
                chatBtn.setVisible(false);

                groupBtn.setBackground(MyColors.LIGHT_BLUE);
                groupBtn.setVisible(false);

                broadcastBtn.setBackground(MyColors.LIGHT_BLUE);
                broadcastBtn.setVisible(false);

                settingBtn.setBackground(MyColors.LIGHT_BLUE);
                settingBtn.setVisible(false);

                logoutBtn.setVisible(false);
                homeBtn.setVisible(true);

                // delete old userChatFrames
                userChatFrames.values().forEach((frame) -> {
                    frame.dispose();
                });

                // delete old groupChatFrames
                groupChatFrames.values().forEach((frame) -> {
                    frame.dispose();
                });

                userChatFrames.clear();
                groupChatFrames.clear();
                desk2.removeAll();

                allUsersFrame.clearJList();
                allGroupsFrame.clearJList();

                loginFrame.setVisible(true);

                try {
                    proxy.deleteAllUserGroups(user.getIduser());
                } catch (RemoteException ex) {
                    System.out.println(ex.getMessage());
                }

                userGroups.clear();

                this.setTitle("BChat - Login");

                break;

            case JOptionPane.NO_OPTION:
                break;

            default:
                break;
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void broadcastBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_broadcastBtnMouseClicked
        broadcastBtn.setBackground(MyColors.DARK_BLUE);
        groupBtn.setBackground(MyColors.LIGHT_BLUE);
        chatBtn.setBackground(MyColors.LIGHT_BLUE);
        settingBtn.setBackground(MyColors.LIGHT_BLUE);

        showBroadcastComponents();
        hideChatComponents();
        hideGroupComponents();
        hideSettingComponents();
    }//GEN-LAST:event_broadcastBtnMouseClicked

    private void settingBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingBtnMouseClicked
        settingBtn.setBackground(MyColors.DARK_BLUE);
        broadcastBtn.setBackground(MyColors.LIGHT_BLUE);
        groupBtn.setBackground(MyColors.LIGHT_BLUE);
        chatBtn.setBackground(MyColors.LIGHT_BLUE);

        showSettingComponents();
        hideChatComponents();
        hideBroadcastComponents();
        hideGroupComponents();
    }//GEN-LAST:event_settingBtnMouseClicked

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        if (!loginFrame.isVisible() && signupFrame.isVisible()) {
            loginFrame.setVisible(true);
            signupFrame.setVisible(false);
        }
        this.setTitle("BChat - Login");
    }//GEN-LAST:event_homeBtnActionPerformed

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        settingFrame.fullName.setText(MainUser.user.getFirstName() + " " + MainUser.user.getLastName());
        settingFrame.username.setText(MainUser.user.getUsername());
        settingFrame.password.setText(MainUser.user.getPassword());
    }//GEN-LAST:event_settingBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton broadcastBtn;
    public static javax.swing.JButton chatBtn;
    private javax.swing.JDesktopPane desk1;
    public static javax.swing.JDesktopPane desk2;
    public static javax.swing.JButton groupBtn;
    public static javax.swing.JButton homeBtn;
    public static javax.swing.JButton logoutBtn;
    private javax.swing.JPanel optionPane;
    public static javax.swing.JButton settingBtn;
    // End of variables declaration//GEN-END:variables

}
