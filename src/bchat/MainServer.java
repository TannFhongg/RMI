/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat;

import bchat.entities.IServer;
import bchat.entities.ServerImp;
import bchat.entities.UserImp;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blacky
 */
public class MainServer {

    public static List<UserImp> allUsers = new ArrayList();

    public static void main(String[] args) {
        File serverFile = new File("server.txt");

        try {
            IServer server = new ServerImp();
            Naming.rebind("rmi://192.168.1.6:2005/bchat", server);
            
            // create a temporary file which will indicates if the registry is
            // currently running or not
            if (serverFile.createNewFile()) {
                System.out.println("File created: " + serverFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (RemoteException | MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("Server is running...");
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to stop the server!");
        in.nextLine();

        if (serverFile.delete()) {
            System.out.println("Deleted the file: " + serverFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

}