 
import java.io.File;
 
public class Fichier_Rename
 {
  public static void main(String[] argv)
  	{
 
    File ancien_nom = new File("/home/khalycoder/Bureau/logiciel/TestCrypto/cle.pdf");
    File nouveau_nom = new File("/home/khalycoder/Bureau/logiciel/TestCrypto/cle.khd");
 
    if (ancien_nom.renameTo(nouveau_nom))
    {
      System.out.println("Le fichier a été renommé");
    }
    	else
    {
      System.out.println("Erreur, impossible de renommer le fichier");
    }
  }
}
