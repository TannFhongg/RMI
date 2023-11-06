/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blacky
 */
public class CreateRegistry {

    public static void main(String[] args) {
        File regFile = new File("registry.txt");
        
        try {
            int port = 2005;
            LocateRegistry.createRegistry(port);

            // create a temporary file which will indicates if the registry is
            // currently running or not
            if (regFile.createNewFile()) {
                System.out.println("File created: " + regFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Registry is running...");
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to stop the registry!");
        in.nextLine();

        if (regFile.delete()) {
            System.out.println("Deleted the file: " + regFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

}
