package rsaProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAGenKey {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter p, q, e respectively: ");
		System.out.println("Enter Big Integer for p: ");
		BigInteger p = in.nextBigInteger();
		
		System.out.println("Enter Big Integer for q: ");
		BigInteger q = in.nextBigInteger();
		
		System.out.println("Enter Big Integer for e: ");
		BigInteger e = in.nextBigInteger();
		
		//Start of computations
		BigInteger one = new BigInteger("1"); //We need big int to multiply p-1 * q-1
		BigInteger n = p.multiply(q); //this is saved inside PUBLIC & PRIVATE KEYS
		System.out.println("p * q = " + n);
		
		BigInteger nMinusOne = (p.subtract(one)).multiply((q.subtract(one))); //(p-1) * (q-1)		
		System.out.println("THIS IS THE BIG INT AFTER MULTUPLING " + nMinusOne);
		
		BigInteger inverseMod = e.modInverse(nMinusOne); //inverseMod === d
		 
	    String str = e + " mod " + nMinusOne + " is: " + inverseMod;
	     
	    // print inverseMod value
	    System.out.println( str );
	    
	    System.out.println("\n\nStarting to write text files: ");

	    //Write out Public key text file
	    try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("pub_key.txt",false));
			outStream.println("e="+e);
			outStream.println("n="+n);
			outStream.close();
			System.out.println("Public key text file done.");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    //Write out Private key text file
	    try {
			PrintWriter outStream = new PrintWriter(new FileOutputStream("pri_key.txt",false));
			outStream.println("d="+inverseMod);
			outStream.println("n="+n);
			outStream.close();
			System.out.println("Private key text file done.");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
//	    //encrypt part
//	    
//		//System.out.println("THIS IS CASCII A?: " + (CASCII.A));
//		System.out.println("This is the start of extracting the stuff");
//		//String test = "THIS IS A TEST";
//		//String test = "N A";
//		String test = "WHEN A GAMERTAG COMES UP AS VIOLATING OUR POLICIES FOR ONLINE BEHAVIOR, THE PERSON WHO OWNS THAT GAMERTAG IS PUNISHED BY BEING BANNED FROM THE SERVICE. KEEPIN MIND, THIS IS NOT JUST A BAN ON A PARTICULAR GAME. THIS IS A BAN ON THE XBOX LIVE SERVICE AS A WHOLE, SO YOU WILL NOT BE ABLE TO GO ONLINE AT ALL DURING YOUR BAN ";
//		//String test = "When a Gamertag comes up as violating our policies for online behavior, the person who owns that Gamertag is punished by being banned from the service. Keep in mind, this is not just a ban on a particular game. This is a ban on the Xbox Live service as a whole, so you will not be able to go online at all during your ban".toUpperCase();
////		5800830286003776393609366128967791759466906208965096218042286611138059385282235873170628691003002 171085904433840217072986908760061153062025249598844480475682409662470814858171304632406440777048331340108509473852956450719367740611973265574242372176176746207763716420760033708533328853214470885955136670294831
//
//		
//		System.out.println("THE LENGTH OF TEST STRING: " + test.length());
//		
//		System.out.println("Will read the pub key");
//		String key = "";
//		 FileReader file = null;
//		try {
//			file = new FileReader("pub_key.txt");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		 BufferedReader reader = new BufferedReader(file);
//
//		 // don't declare it here
//		 // String key = "";
//		 String line = null;
//		try {
//			line = reader.readLine();
//			System.out.println("Line1 : " + line);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		 while (line != null) {
//		      key += line;
//		      try {
//				line = reader.readLine();
//				System.out.println("Line2 : " + line);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		  }
//		//System.out.println(key);
//		
//		
//		
//		
//		
//		/* WORKS TO ENCRYPT AND DECYPT
//		BigInteger d =  new BigInteger("5800830286003776393609366128967791759466906208965096218042286611138059385282235873170628691003002171085904433840217072986908760061153062025249598844480475682409662470814858171304632406440777048331340108509473852956450719367740611973265574242372176176746207763716420760033708533328853214470885955136670294831");
//		BigInteger e = new BigInteger("35535");
//		BigInteger n = new BigInteger("115935041739676149688925098646158875237714573754541447754855261376147885408326350817276878815968325168468849300625485764111250162414552339182927162507656772727460097082714127730434960500556347274566628060099924037102991424472292215772798531727033839381334692684137327622000966676671831831088373420823444370953");
//		//115935041739676149688925098646158875237714573754541447754855261376147885408326350817276878815968325168468849300625485764111250162414552339182927162507656772727460097082714127730434960500556347274566628060099924037102991424472292215772798531727033839381334692684137327622000966676671831831088373420823444370953
//		*/
//		String formatText = "";
//		int count = 1;
//		for(int i = 0; i < test.length(); i+=3) {
//			char temp = (char) ((test.charAt(i)));
//			char temp2 = (char) ((test.charAt(i+1)));
//			char temp3 = (char) ((test.charAt(i+2)));
//			if(temp == " ".charAt(0)) { //its space so change t to 26
//				//System.out.println("THIS IS A SPACE");
//				//temp = (char) (" ".charAt(0) +26);
//			}
//			else {
//				//temp=(char) (temp);
//			}
////			System.out.println("We are in COUNT: " + count++);
////			System.out.print(String.format("%02d", CASCII.Convert(test.charAt(i))) + "");
////			String formatText1 = String.format("%02d", CASCII.Convert(test.charAt(i) )) + "" + String.format("%02d", CASCII.Convert(test.charAt(i+1) )) + "" +String.format("%02d", CASCII.Convert(test.charAt(i+2) )) + "";
////			System.out.println("THE FORMAT TEXT IN LOOP " + formatText);
////
////			System.out.println("THE FORMATTED TEXTTTTTT: " + formatText1);
////			BigInteger formatBig = new BigInteger(formatText1);
////			System.out.println("THE BIGINT OF FORMAT TEXT: " + formatBig);
////			System.out.println("THe cipher is: " + formatBig.modPow(e, n)); //this is correct
////			BigInteger cipher = formatBig.modPow(e, n);
//		//	String formatedNum = String.format("%2s", Integer.toBinaryString(S1[r2][col2])).replace(' ', '0');
//		}

		
		//Now to decipher
		
		//System.out.println("THe plaintext = "+ cipher.modPow(inverseMod, n)); //correct
	}

	// p = 6551
	// q = 4733
	// e = 8311
}
