/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bchat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuanv
 */
class RemoteFileTransferService {
    public void sendFile(byte[] bytes) throws RemoteException, FileNotFoundException {
    try (FileOutputStream fos = new FileOutputStream(new File("received_file.bin"))) {
        fos.write(bytes);
    }   catch (IOException ex) {
            Logger.getLogger(RemoteFileTransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
