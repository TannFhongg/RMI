/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.utilities;

import bchat.entities.UserImp;
import bchat.gui.MainUser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Blacky
 */
public class GroupUsersListRenderer extends JLabel implements ListCellRenderer<UserImp> {

    private final int adminId;
    
    public GroupUsersListRenderer(int adminId) {
        setOpaque(true);
        this.adminId = adminId;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends UserImp> list, UserImp user, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon icon;
        if(user.getIduser() == adminId)
            icon = new ImageIcon(getClass().getResource("/bchat/res/groupAdmin.png"));
        else
            icon = new ImageIcon(getClass().getResource("/bchat/res/groupUser.png"));
        
        setIcon(icon);
        list.setForeground(Color.WHITE);
        
        try {
            // check if user is online
            if (MainUser.proxy.isConnected(user.getIduser()) == true) {
                list.setForeground(MyColors.LIGHT_GREEN);
            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

        if(user.getIduser() == MainUser.user.getIduser())
            setText(user.getFirstName() + " " + user.getLastName() + " (You)");
        else
            setText(user.getFirstName() + " " + user.getLastName());

        setPreferredSize(new Dimension(190, 40));

        // handle item selection
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
             
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }

}
