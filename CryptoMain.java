import java.util.Scanner;
import java.io.*; 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class CryptoMain extends JPanel{
    public static SecretKey key;
    public static  String keyFile;
    public static String[] tablFile = new String[1];
    static CryptoImpl crypto = new CryptoImpl();
    static Scanner sc = new Scanner(System.in);
/**
 * @param folder :c'est le dossier a chiffrer
 * On parcourt tout le dossier si on rencontre un fichier on le chiffre et on supprime l'original
 * s'il y a un dossier on entre dans ce dossier on regarde s'il y a des fichiers et ainsi de suite 
 * jusqu a qu'il n y est plus de fichier a chiffrer
 * A note aussi que si un fichier est trouve chiffrer on va pas le rechiffrer
 * 
 * !file.isDirectory(): retourne true si le file n'est pas un dossier
 * file.getName(): c'est le nom fichier au courant
 * !file.getName().endsWith(".khd"): verifie si le nom du fichier se terminepar .khd
 * folder/file.getName(): on recupere le chemin
 * file.delete(): sert a supprimer le fichier au courant
 */
    public static void findAllFilesInFolderchiff(File folder) {
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (!file.getName().endsWith(".khd")){
					System.out.println(file.getName());
                    crypto.Chiffrement(folder+"/"+file.getName(), keyFile);
					file.delete();
				}
			} else {
				findAllFilesInFolderchiff(file);
			}
		}
		
	}
/**
 * @param folder : le dossier a dechiffrer
 * Ici on fait l'operation inverse du methode findAllFilesInFolderchiff(File folder) 
 *  
 */
    public static void findAllFilesInFolderDechiff(File folder) {
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (file.getName().endsWith(".khd")){
					System.out.println(file.getName());
                    crypto.Dechiffrement(folder+"/"+file.getName(), keyFile);
					file.delete();
				}
			} else {
				findAllFilesInFolderDechiff(file);
			}
		}
		
	}
/**
 * 
 * @return :le dossier a chiffrer
 */
    public static File folderToencrypt(){
        System.out.println("Choisir le dossier a chiffrer");
        JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		f.showSaveDialog(null);
		System.out.println(f.getSelectedFile());
        return f.getSelectedFile();
    }
    public static void main(String[] args) {
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
                try {
                    
                    crypto.getCipher(keyFile,Cipher.ENCRYPT_MODE );
                
		             findAllFilesInFolderchiff(folderToencrypt());
                } catch (Exception e) {
                   
                }
        }
            else if(reponse.equals("2")){

                try {
                    crypto.getCipher(keyFile,Cipher.DECRYPT_MODE );
                    //folderToencrypt().renameTo(dest)
                    findAllFilesInFolderDechiff(folderToencrypt());
                } catch (Exception e) {
                   
                }
            }

            else{
                System.out.println("Faut respecter les regles toi aussi !!!");
            }
    
        }
        
    }
    
}
