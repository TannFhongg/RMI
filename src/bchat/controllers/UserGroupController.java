/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.controllers;

import bchat.Datasource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Blacky
 */
public class UserGroupController {
    
    private String getGroupByUserString = "SELECT groupId FROM user_group WHERE userId = ?";
    private String getUserByGroupString = "SELECT userId FROM user_group WHERE groupId = ?";
    private String addUserString = "INSERT INTO user_group VALUES(?,?)";
    private String removeUserString = "DELETE FROM user_group WHERE userId = ? AND groupId = ?";
    private String removeGrpUsersString = "DELETE FROM user_group WHERE groupId = ?";
    private String removeUserGroupsString = "DELETE FROM user_group WHERE userId = ?";
    
    private PreparedStatement getGroupByUserStmt;
    private PreparedStatement getUserByGroupStmt;
    private PreparedStatement addUserStmt;
    private PreparedStatement removeUserStmt;
    private PreparedStatement removeGrpUsersStmt;
    private PreparedStatement removeUserGroupsStmt;
    
    public UserGroupController() {
        try {
            getGroupByUserStmt = Datasource.getConnection().prepareStatement(getGroupByUserString);
            getUserByGroupStmt = Datasource.getConnection().prepareStatement(getUserByGroupString);
            addUserStmt = Datasource.getConnection().prepareStatement(addUserString);
            removeUserStmt = Datasource.getConnection().prepareStatement(removeUserString);
            removeGrpUsersStmt = Datasource.getConnection().prepareStatement(removeGrpUsersString);
            removeUserGroupsStmt = Datasource.getConnection().prepareStatement(removeUserGroupsString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Integer> getUserGroupsId(int userId) throws SQLException {
        ArrayList<Integer> ls = new ArrayList();
        getGroupByUserStmt.setInt(1, userId);
        ResultSet set = getGroupByUserStmt.executeQuery();
        
        while(set.next()) {
            ls.add(set.getInt(1));
        }
        set.close();
        return ls;
    }
    
    public ArrayList<Integer> getGroupUsersId(int groupId) throws SQLException {
        ArrayList<Integer> ls = new ArrayList();
        getUserByGroupStmt.setInt(1, groupId);
        ResultSet set = getUserByGroupStmt.executeQuery();
        
        while(set.next()) {
            ls.add(set.getInt(1));
        }
        set.close();
        return ls;
    }
    
    public void addUserToGroup(int userId, int groupId) throws SQLException {
        addUserStmt.setInt(1, userId);
        addUserStmt.setInt(2, groupId);
        addUserStmt.executeUpdate();
    }
    
    public void removeUserFromGroup(int userId, int groupId) throws SQLException {
        removeUserStmt.setInt(1, userId);
        removeUserStmt.setInt(2, groupId);
        removeUserStmt.executeUpdate();
    }
    
    public void removeAllUsersFromGroup(int groupId) throws SQLException {
        removeGrpUsersStmt.setInt(1, groupId);
        removeGrpUsersStmt.executeUpdate();
    }
    
    public void removeUserGroups(int userId) throws SQLException {
        removeUserGroupsStmt.setInt(1, userId);
        removeUserGroupsStmt.executeUpdate();
    }
    
    public static final UserGroupController INSTANCE = new UserGroupController();

    public static void main(String[] args) throws SQLException {
        INSTANCE.removeUserFromGroup(8, 5);
    }
}
