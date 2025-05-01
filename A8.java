import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner; 
 
public class A8 { 
    public static String Algorithm(String K, String RAND) { 
        try { 
            String combinedInput = K + RAND; 
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); 
            byte[] hash = digest.digest(combinedInput.getBytes()); 
             
            StringBuilder sessionKey = new StringBuilder(); 
            for (int i = 0; i < 16; i++) { 
                sessionKey.append(String.format("%02x", hash[i])); 
            } 
            return sessionKey.toString(); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
         
        System.out.print("Enter secret key (K): "); 
        String K = scanner.nextLine(); 
         
        System.out.print("Enter random challenge (RAND): "); 
        String RAND = scanner.nextLine(); 
         
        String Kc = a8Algorithm(K, RAND); 
        System.out.println("Generated Session Key (Kc): " + Kc); 
    } 
}
