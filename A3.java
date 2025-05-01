import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner; 
 
public class A3{ 
    public static String Algorithm(String K, String RAND) { 
        try { 
            String combinedInput = K + RAND; 
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); 
            byte[] hash = digest.digest(combinedInput.getBytes()); 
             
            StringBuilder sb = new StringBuilder(); 
            for (byte b : hash) { 
                sb.append(String.format("%02x", b)); 
            } 
            return sb.substring(0, 8); 
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
         
        String SRES = a3Algorithm(K, RAND); 
        System.out.println("Authentication Response (SRES): " + SRES); 
    } 
}
