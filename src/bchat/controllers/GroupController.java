/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.controllers;

import bchat.Datasource;
import bchat.entities.GroupImp;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Blacky
 */
public class GroupController {

    private String createString = "INSERT INTO `group` VALUES(default,?,?)";
    private String findAllString = "SELECT * FROM `group`";
    private String findByKeyString = "SELECT * FROM `group` WHERE lower(groupname) LIKE ?";
    private String findByIdString = "SELECT * FROM `group` WHERE idgroup = ?";
    private String deleteString = "DELETE FROM `group` WHERE idgroup = ?";
    
    private PreparedStatement createStmt;
    private PreparedStatement findAllStmt;
    private PreparedStatement findByKeyStmt;
    private PreparedStatement findByIdStmt;
    private PreparedStatement deleteStmt;
    public GroupController() {
        try {
            createStmt = Datasource.getConnection().prepareStatement(createString);
            findAllStmt = Datasource.getConnection().prepareStatement(findAllString);
            findByKeyStmt = Datasource.getConnection().prepareStatement(findByKeyString);
            findByIdStmt = Datasource.getConnection().prepareStatement(findByIdString);
            deleteStmt = Datasource.getConnection().prepareStatement(deleteString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int create(String groupName, int adminId) throws SQLException {
        createStmt.setString(1, groupName);
        createStmt.setInt(2, adminId);
        createStmt.executeUpdate();

        GroupImp group = findByKey(groupName);
        return group.getGroupId();
    }

    public List<GroupImp> findAll() throws SQLException, RemoteException {
        List<GroupImp> ls = new ArrayList();
        ResultSet set = findAllStmt.executeQuery();
        int id, adminId;
        String groupName;
        while (set.next()) {
            id = set.getInt(1);
            groupName = set.getString(2);
            adminId = set.getInt(3);
            ls.add(new GroupImp(id, groupName, adminId));
        }
        set.close();
        return ls;
    }

    public GroupImp findByKey(String groupName) {
        try {
            findByKeyStmt.setString(1, groupName);
            ResultSet set = findByKeyStmt.executeQuery();
            if (set.next()) {
                return new GroupImp(set.getInt(1), set.getString(2), set.getInt(3));
            }
            set.close();
        } catch (SQLException | RemoteException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public GroupImp findById(int groupId) {
        try {
            findByIdStmt.setInt(1, groupId);
            ResultSet set = findByIdStmt.executeQuery();
            if (set.next()) {
                GroupImp group = new GroupImp(set.getInt(1), set.getString(2), set.getInt(3));
                // fill group by its users
                for (int userId : UserGroupController.INSTANCE.getGroupUsersId(groupId)) {
                    group.addUser(userId);
                }
                return group;
            }
            set.close();
        } catch (SQLException | RemoteException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void delete(int groupId) throws SQLException {
        deleteStmt.setInt(1, groupId);
        deleteStmt.executeUpdate();
        
        // delete group users
        UserGroupController.INSTANCE.removeAllUsersFromGroup(groupId);
    }
    
    public static final GroupController INSTANCE = new GroupController();
    
}
