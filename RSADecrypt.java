package rsaProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class RSADecrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String dString = "";
		String nString = "";
		System.out.println("Will read the private key");

		FileReader file = null;
		try {
			file = new FileReader("pri_key.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(file);

		// don"t declare it here
		String key = "";
		String line = null;
		try {
			line = reader.readLine();
			System.out.println("Line1 : " + line);
			dString = line.substring(2, line.length());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// while (line != null) {
		key += line;
		try {
			line = reader.readLine();
			System.out.println("Line2 : " + line);
			nString = line.substring(2, line.length());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		BigInteger d = new BigInteger(dString);
		BigInteger n = new BigInteger(nString);


		
		
		//Read test.enc and decrypt
		try (BufferedReader br = new BufferedReader(new FileReader("test.enc"))) {
		    String line2;
		    while ((line2 = br.readLine()) != null) {
//		    	System.out.println("Ciphertext: " + line2);
//				System.out.println("The plaintext for above: "+ String.format("%06d",new BigInteger(line2).modPow(d, n))); //correct for decryption
				String plaintext = String.format("%06d",new BigInteger(line2).modPow(d, n));
				
				String char1 = plaintext.charAt(0) + "" + plaintext.charAt(1);
				String char2 = plaintext.charAt(2) + "" + plaintext.charAt(3);
				String char3 = plaintext.charAt(4) + "" + plaintext.charAt(5);
				
				System.out.print(printASCII(char1) + printASCII(char2) + printASCII(char3)) ;
//				System.out.println("\nThe equivalent in CASCII: " + printASCII(char1));
//				System.out.println(" " + printASCII(char2));
//				System.out.println(" " + printASCII(char3) + "\n");
		       // process the line.
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	}
	
	
	public static String printASCII(String charIn) {
	    switch ( charIn )
	    {
	      case "00":
	        return "A";
	      case "01":
	        return "B";
	      case "02":
	        return "C";
	      case "03":
	        return "D";
	      case "04":
	        return "E";
	      case "05":
	        return "F";
	      case "06":
	        return "G";
	      case "07":
	        return "H";
	      case "08":
	        return "I";
	      case "09":
	        return "J";
	      case "10":
	        return "K";
	      case "11":
	        return "L";
	      case "12":
	        return "M";
	      case "13":
	        return "N";
	      case "14":
	        return "O";
	      case "15":
	        return "P";
	      case "16":
	        return "Q";
	      case "17":
	        return "R";
	      case "18":
	        return "S";
	      case "19":
	        return "T";
	      case "20":
	        return "U";
	      case "21":
	        return "V";
	      case "22":
	        return "W";
	      case "23":
	        return "X";
	      case "24":
	        return "Y";
	      case "25":
	        return "Z";
	      case "26":
	        return " ";
	      case "27":
	        return ",";
	      case "28":
	        return "?";
	      case "29":
	        return ":";
	      case "30":
	        return ".";
	      case "31":
	        return "\"";

	      default:
	        throw new java.lang.IllegalArgumentException("Argument must be be on interval [00, 31]");
	      }
		
	}

}
