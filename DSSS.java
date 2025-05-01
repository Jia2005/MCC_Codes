//Direct Sequence Spread Spectrum using Barker Code
import java.util.Scanner; 
 
public class Main { 
    private static final String BARKER_CODE = "11010111000"; 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
         
        System.out.print("Enter a 2 or 3 digit binary number (0s and 1s only): "); 
        String inputData = scanner.next(); 
         
        if (!inputData.matches("[01]{2,3}")) { 
            System.out.println("Invalid input. Enter a binary number with 2 or 3 digits."); 
            return; 
        } 
         
        String transmittedSignal = xorOperation(inputData, BARKER_CODE); 
        System.out.println("Transmitted Signal: " + transmittedSignal); 
         
        System.out.println("\nReceiver Side:"); 
        receiveSignal(transmittedSignal); 
         
        scanner.close(); 
    } 
     
    private static String xorOperation(String data, String barker) { 
        StringBuilder result = new StringBuilder(); 
        int barkerLength = barker.length(); 
         
        for (int i = 0; i < data.length(); i++) { 
            for (int j = 0; j < barkerLength; j++) { 
                result.append(data.charAt(i) ^ barker.charAt(j)); 
            } 
        } 
        return result.toString(); 
    } 
     
    private static void receiveSignal(String receivedSignal) { 
        String barkerCode = "11010111000"; 
        int segmentLength = barkerCode.length(); 
         
        StringBuilder decodedBits = new StringBuilder(); 
         
        System.out.println("Received Signal: " + receivedSignal); 
         
        for (int i = 0; i < receivedSignal.length(); i += segmentLength) { 
            if (i + segmentLength > receivedSignal.length()) 
                break; // Ignore incomplete segments 
             
            String segment = receivedSignal.substring(i, i + segmentLength); 
            String xorResult = xorBinary(segment, barkerCode); 
             
            System.out.println("Segment XOR: " + xorResult); 
             
            int onesCount = countOnes(xorResult); 
             
            if (onesCount > 7) { 
                decodedBits.append("1"); 
            } else if (onesCount < 4) { 
                decodedBits.append("0"); 
            } 
        } 
         
        System.out.println("Decoded Output: " + decodedBits.toString()); 
    } 
     
    private static String xorBinary(String a, String b) { 
        StringBuilder result = new StringBuilder(); 
        for (int i = 0; i < a.length(); i++) { 
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1'); 
        } 
        return result.toString(); 
    } 
     
    private static int countOnes(String binary) { 
        int count = 0; 
        for (char c : binary.toCharArray()) { 
            if (c == '1') count++; 
} 
return count; 
} 
}
