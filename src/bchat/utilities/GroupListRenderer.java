/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.utilities;

import bchat.entities.GroupImp;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Blacky
 */
public class GroupListRenderer extends JLabel implements ListCellRenderer<GroupImp> {

    public GroupListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends GroupImp> list, GroupImp group, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/bchat/res/group.png"));
        setIcon(icon);
        list.setForeground(Color.WHITE);

        setText(group.getGroupName());

        setPreferredSize(new Dimension(200, 40));

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
