/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat.controllers;

import bchat.Datasource;
import bchat.entities.UserImp;
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
public class UserController {

    private String createString = "INSERT INTO user VALUES(default,?,?,?,?)";
    private String findAllString = "SELECT * FROM user";
    private String findByKeyString = "SELECT * FROM user WHERE lower(username) LIKE ?";
    private String updateString = "UPDATE user SET username = ?, password = ? WHERE iduser = ?";
    private String deleteString = "DELETE FROM user WHERE iduser = ?";
    
    private PreparedStatement createStmt;
    private PreparedStatement findAllStmt;
    private PreparedStatement findByKeyStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    
    public UserController() {
        try {
            createStmt = Datasource.getConnection().prepareStatement(createString);
            findAllStmt = Datasource.getConnection().prepareStatement(findAllString);
            findByKeyStmt = Datasource.getConnection().prepareStatement(findByKeyString);
            updateStmt = Datasource.getConnection().prepareStatement(updateString);
            deleteStmt = Datasource.getConnection().prepareStatement(deleteString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(String username, String password, String fName, String lName) throws SQLException {
        createStmt.setString(1, username);
        createStmt.setString(2, password);
        createStmt.setString(3, fName);
        createStmt.setString(4, lName);

        createStmt.executeUpdate();
    }

    public void update(String username, String password, int userId) throws SQLException {
        updateStmt.setString(1, username);
        updateStmt.setString(2, password);
        updateStmt.setInt(3, userId);
        
        updateStmt.executeUpdate();
    }
    
    public void delete(int userId) throws SQLException {
        deleteStmt.setInt(1, userId);
        deleteStmt.executeUpdate();
    }
    
    public List<UserImp> findAll() throws SQLException, RemoteException {
        List<UserImp> ls = new ArrayList();
        ResultSet set = findAllStmt.executeQuery();
        int id;
        String username, password, fName, lName;
        while (set.next()) {
            id = set.getInt(1);
            username = set.getString(2);
            password = set.getString(3);
            fName = set.getString(4);
            lName = set.getString(5);
            ls.add(new UserImp(id, username, password, fName, lName));
        }
        set.close();
        return ls;
    }

    public UserImp findByKey(String username) {
        try {
            findByKeyStmt.setString(1, username);
            ResultSet set = findByKeyStmt.executeQuery();
            if (set.next()) {
                return new UserImp(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5));
            }
            set.close();
        } catch (SQLException | RemoteException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static final UserController INSTANCE = new UserController();

}
