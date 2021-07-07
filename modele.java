/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacryptographique;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import javafx.scene.control.Label;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFileChooser;

/**
 *
 * @author Lenovo
 */
public class modele {
    
   
     JFileChooser jfc = new JFileChooser();
     public static String keyFile;
      public final String TRANSFORM="AES/CBC/PKCS5Padding";
    public final byte [] IV="AseizeCaracteres".getBytes();
    public final String ALGO="AES";
    public final int KEYSIZE=128;
     private Cipher c;
        private CipherInputStream cis;
        public  String fileToEncrypt,encryptedFilePath,decryptedFilePath;
       
   
        
        
    
    

public  void findAllFilesInFolderchiff(File folder) {
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (!file.getName().endsWith(".khd")){
					System.out.println(file.getName());
                    this.Chiffrement(folder+"/"+file.getName(), keyFile);
					file.delete();
				}
			} else {
				findAllFilesInFolderchiff(file);
			}
		}
		
	}

 public static File folderToencrypt(){
        System.out.println("Choisir le dossier a chiffrer");
        JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		f.showSaveDialog(null);
		System.out.println(f.getSelectedFile());
        return f.getSelectedFile();
    }
 
 public void Chiffrement(String fileToencrypt,String keyfile){
            try {

                FileInputStream fis = new FileInputStream(fileToencrypt);
                cis = new CipherInputStream(fis, getCipher(keyfile,Cipher.ENCRYPT_MODE));
                FileOutputStream fos = new FileOutputStream(fileToencrypt+".khd");//+".khd"
                byte[] buffer = new byte[1024];
                int nbBytesLu=0;

                while ((nbBytesLu = cis.read(buffer)) != -1){
                     fos.write(buffer, 0, nbBytesLu);
               }
               //obj.close();
               fis.close();
               fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(fileToEncrypt);
        }
 
 public void Dechiffrement(String fileTodecrypt,String keyfile){
            try {

                FileInputStream fis = new FileInputStream(fileTodecrypt);
                FileOutputStream fos = new FileOutputStream(fileTodecrypt.substring(0, fileTodecrypt.length()-4));
                CipherOutputStream cos = new CipherOutputStream(fos, getCipher(keyfile, Cipher.DECRYPT_MODE));

                byte[] buffer = new byte[1024];
                int nbBytesLu=0;

                while ((nbBytesLu = fis.read(buffer)) != -1){
                     fos.write(buffer, 0, nbBytesLu);
               }
               cos.close();
               fis.close();
               fos.close();
               System.out.println("Decryption succeed!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(fileTodecrypt);
        }

   
   
   
   
    
    public SecretKey genKey() {
            
            try {
                KeyGenerator gen = KeyGenerator.getInstance(ALGO);
                gen.init(KEYSIZE);
                SecretKey key = gen.generateKey();
                return key;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return null;
        }

     public Cipher getCipher(String keyfile, int mode){
            try {

                c = Cipher.getInstance(TRANSFORM);
                IvParameterSpec iv = new IvParameterSpec(IV);
                c.init(mode,getKey(keyfile), iv);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }
     

    
    public String saveKey(SecretKey key, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream obj = new ObjectOutputStream(fos);
            obj.writeObject(key);
            obj.close();
            fos.close();
            return filePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return null;
    }
 public SecretKey getKey(String keyfile) {
            try {
                FileInputStream fis = new FileInputStream(keyfile);
                
                try {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    SecretKey key = (SecretKey) ois.readObject();

                    return key;
                } catch (Exception e) {
                    e.printStackTrace();
                }   
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
            return null;
        }
    
   
    
     public void showSaveDg( SecretKey key){
        int option = jfc.showSaveDialog(jfc);


        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            this.saveKey(key, filePath);
        }
        String encoding = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encoding);
    }
     
     public String showOpenKeyFile(SecretKey key){
        int option = jfc.showOpenDialog(jfc);
        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
             key= this.getKey(filePath);
            keyFile = filePath;
        }
       
        return keyFile;
    }
     
      public String showOpenFileToEncrypt(){

        int option = jfc.showOpenDialog(jfc);
        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            fileToEncrypt = filePath;
        }

        return  fileToEncrypt;
    }
       public String showSavechiffremnt(){
           
             jfc.setDialogTitle("Choisissez le chemin du fichier chiffrer pour l'enregistré");
           int option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                encryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
         return encryptedFilePath;
        
        }
       
        public String showOpenFileToDecrypt(){
        return showOpenFileToEncrypt();
    }
         public String showSaveDechiffremnt(){
           
            jfc.setDialogTitle("Choisissez le chemin du fichier déchiffrer pour l'enregistré");
           int  option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                decryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
         return decryptedFilePath;
        
        }
         
          public  void findAllFilesInFolderDechiff(File folder) {
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (file.getName().endsWith(".khd")){
					System.out.println(file.getName());
                    this.Dechiffrement(folder+"/"+file.getName(), keyFile);
					file.delete();
				}
			} else {
				findAllFilesInFolderDechiff(file);
			}
		}
		
	}
       
   
}