/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacryptographique;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFileChooser;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable {
    
    modele modele = new modele();
    public static SecretKey key;
    public static  String keyFile;
    public static String[] tablFile = new String[1];
    
    public static final String cipherAlgorithm = "AES/CBC/PKCS5Padding";
    @FXML
    private void ChiffrerUnFichier(ActionEvent event) {
        keyFile = modele.showOpenKeyFile(key);
        modele.getCipher(keyFile,Cipher.ENCRYPT_MODE );
        String fileEncrypt= modele.showOpenFileToEncrypt();
        modele.Chiffrement(fileEncrypt, keyFile);
    }
    
    @FXML
    private void GenSaveKey(ActionEvent event) {
            SecretKey key = modele.genKey();
            modele.showSaveDg(key);
           }
    
     @FXML
    private void ChiffrerUnDossier(ActionEvent event) {
        keyFile = modele.showOpenKeyFile(key);
        modele.getCipher(keyFile,Cipher.ENCRYPT_MODE );
         modele.findAllFilesInFolderchiff(modele.folderToencrypt());
        }
    
    @FXML
    private void DechiffrerUnFichier(ActionEvent event) {
        
         keyFile = modele.showOpenKeyFile(key);
        modele.getCipher(keyFile,Cipher.ENCRYPT_MODE );
        String fileEncrypt= modele.showOpenFileToEncrypt();
        modele.Dechiffrement(fileEncrypt, keyFile);
      
        
      
        
    }
    @FXML
    private void DechiffrerUnDossier (ActionEvent event) {
        
        keyFile = modele.showOpenKeyFile(key);
        modele.getCipher(keyFile,Cipher.DECRYPT_MODE );
                  
         modele.findAllFilesInFolderDechiff(modele.folderToencrypt());
       
        
    }
    
            
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
