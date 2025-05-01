import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner; 
 
public class A5 { 
    public static String Algorithm(String K, int FN, String data) { 
        try { 
            String combinedInput = K + FN; 
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); 
            byte[] hash = digest.digest(combinedInput.getBytes()); 
             
            StringBuilder keyStream = new StringBuilder(); 
            for (byte b : hash) { 
                keyStream.append(String.format("%02x", b)); 
            } 
             
            StringBuilder encryptedData = new StringBuilder(); 
            for (int i = 0; i < data.length(); i++) { 
                encryptedData.append((char)(data.charAt(i) ^ keyStream.charAt(i % keyStream.length()))); 
            } 
            return encryptedData.toString(); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
         
        System.out.print("Enter secret key (K): "); 
        String K = scanner.nextLine(); 
         
        System.out.print("Enter frame number (FN): "); 
        int FN = scanner.nextInt(); 
        scanner.nextLine(); 
         
        System.out.print("Enter message to encrypt: "); 
        String data = scanner.nextLine(); 
         
        String encryptedData = a5Algorithm(K, FN, data); 
        System.out.println("Encrypted Data: " + encryptedData); 
         
        String decryptedData = a5Algorithm(K, FN, encryptedData); 
        System.out.println("Decrypted Data: " + decryptedData); 
    }
}
