import java.util.Scanner;
import java.io.*; 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JPanel;

public class GenkeyMain extends JPanel{
    public static SecretKey key;
    public static  String keyFile;
    public static String[] tablFile = new String[1];
    
    public static void main(String[] args) {
        Genkey crypto = new Genkey();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous generer une nouvelle cle : ");
        String reponse = sc.nextLine();
        if(reponse.equals("oui")){
            key = crypto.genKey();
            crypto.showSaveDg(key);
        }
        else{
            System.out.println("Selectionnez une  cle");
            keyFile = crypto.showOpenKeyFile(key);
            System.out.println("Tapez 1 pour chiffrer ou 2 pour dechiffrer");
            reponse = sc.nextLine();

            if(reponse.equals("1")){

                crypto.getCipher(keyFile,Cipher.ENCRYPT_MODE );
                File dir  = new File("/home/khalycoder/Bureau/logiciel/TestCrypto");
                File[] liste = dir.listFiles();
                
                for(File item : liste){
                    if(item.isFile())
                    { 
                        if (!item.getName().endsWith(".khd")){
                            //System.out.format("Nom du fichier: %s%n", item.getName()); 
                            crypto.Chiffrement("/home/khalycoder/Bureau/logiciel/TestCrypto/"+item.getName(), keyFile);
                            item.delete();
                        }
                    } 
                    
                    // else if(item.isDirectory())
                    // {
                    //   System.out.format("Nom du répertoir: %s%n", item.getName());
                    //  //crypto.Chiffrement("/home/khalycoder/Bureau/logiciel/TestCrypto/"+item.getName(), keyFile); 
                    // } 
                  }
               // crypto.Chiffrement(tablFile[0], keyFile);
              
            }

            else if(reponse.equals("2")){
                crypto.getCipher(keyFile,Cipher.DECRYPT_MODE );
                String fileToDecrypt = crypto.showOpenFileToDecrypt();
                crypto.Dechiffrement(fileToDecrypt, keyFile);
            }

            else{
                System.out.println("Faut respecter les regles");
            }
            
        
           
        }
        

        
        
       
     

        // JFileChooser jfc = new JFileChooser();
        // //int option = jfc.showSaveDialog(jfc);
        // int option = jfc.showOpenDialog(jfc);

        // if(JFileChooser.APPROVE_OPTION==option){
        //     String filePath = jfc.getSelectedFile().getAbsolutePath();
        //     crypto.saveKey(key, filePath);
        // }
        // String encoding = Base64.getEncoder().encodeToString(key.getEncoded());
        // System.out.println(encoding);







        
        //SecretKey key2 = crypto.genKey();
        // option = jfc.showSaveDialog(jfc);
        // if(JFileChooser.APPROVE_OPTION==option){
        //     String filePath = jfc.getSelectedFile().getAbsolutePath();
        //     key2= crypto.getKey(filePath);
        // }
        
        //crypto.saveKey(key, "mykey.khd");
        // String encoding2 = Base64.getEncoder().encodeToString(key2.getEncoded());
        // System.out.println(encoding2);
        
    }
    
}
