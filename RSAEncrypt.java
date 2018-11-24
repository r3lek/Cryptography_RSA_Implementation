package rsaProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class RSAEncrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String eString = "";
		String nString = "";
		//encrypt part

		//System.out.println("THIS IS CASCII A?: " + (CASCII.A));
		System.out.println("This is the start of extracting the stuff");
		//String test = "THIS IS A TEST";
		//String test = "N A";
		String test = "WHEN A GAMERTAG COMES UP AS VIOLATING OUR POLICIES FOR ONLINE BEHAVIOR, THE PERSON WHO OWNS THAT GAMERTAG IS PUNISHED BY BEING BANNED FROM THE SERVICE. KEEPIN MIND, THIS IS NOT JUST A BAN ON A PARTICULAR GAME. THIS IS A BAN ON THE XBOX LIVE SERVICE AS A WHOLE, SO YOU WILL NOT BE ABLE TO GO ONLINE AT ALL DURING YOUR BAN ";
		//String test = "When a Gamertag comes up as violating our policies for online behavior, the person who owns that Gamertag is punished by being banned from the service. Keep in mind, this is not just a ban on a particular game. This is a ban on the Xbox Live service as a whole, so you will not be able to go online at all during your ban".toUpperCase();
		//		5800830286003776393609366128967791759466906208965096218042286611138059385282235873170628691003002 171085904433840217072986908760061153062025249598844480475682409662470814858171304632406440777048331340108509473852956450719367740611973265574242372176176746207763716420760033708533328853214470885955136670294831


		System.out.println("THE LENGTH OF TEST STRING: " + test.length());

		System.out.println("Will read the pub key");

		FileReader file = null;
		try {
			file = new FileReader("pub_key.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(file);

		// don't declare it here
		String key = "";
		String line = null;
		try {
			line = reader.readLine();
			System.out.println("Line1 : " + line);
			eString = line.substring(2, line.length());

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
		//  }
		//System.out.println(key);

		BigInteger e = new BigInteger(eString);
		BigInteger n = new BigInteger(nString);


		//Read and store into file the msg text
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("test.txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String currLine = null;
			try {
				currLine = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (currLine != null) {
				sb.append(currLine);
				sb.append(System.lineSeparator());
				try {
					currLine = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			everything = sb.toString().replaceAll("(\\r|\\n)", "").toUpperCase();
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		System.out.println("READING EVERYTHING: \n" + everything +"\n");
		System.out.println("THE SIZE OF EVERYTHING: " + everything.length());

		/* WORKS TO ENCRYPT AND DECYPT
		BigInteger d =  new BigInteger("5800830286003776393609366128967791759466906208965096218042286611138059385282235873170628691003002171085904433840217072986908760061153062025249598844480475682409662470814858171304632406440777048331340108509473852956450719367740611973265574242372176176746207763716420760033708533328853214470885955136670294831");
		BigInteger e = new BigInteger("35535");
		BigInteger n = new BigInteger("115935041739676149688925098646158875237714573754541447754855261376147885408326350817276878815968325168468849300625485764111250162414552339182927162507656772727460097082714127730434960500556347274566628060099924037102991424472292215772798531727033839381334692684137327622000966676671831831088373420823444370953");
		//115935041739676149688925098646158875237714573754541447754855261376147885408326350817276878815968325168468849300625485764111250162414552339182927162507656772727460097082714127730434960500556347274566628060099924037102991424472292215772798531727033839381334692684137327622000966676671831831088373420823444370953
		 */
		String formatText = "";
		int count = 1;
		for(int i = 0; i < everything.length(); i+=3) {
			char temp = (char) ((test.charAt(i)));
			//char temp2 = (char) ((test.charAt(i+1)));
			//char temp3 = (char) ((test.charAt(i+2)));
			if(temp == " ".charAt(0)) { //its space so change t to 26
				//System.out.println("THIS IS A SPACE");
				//temp = (char) (" ".charAt(0) +26);
			}
			else {
				//temp=(char) (temp);
			}
			System.out.println("We are in COUNT: " + count++);
			System.out.print(String.format("%02d", CASCII.Convert(everything.charAt(i))) + "");
			String formatText1 = String.format("%02d", CASCII.Convert(everything.charAt(i) )) + "" + String.format("%02d", CASCII.Convert(everything.charAt(i+1) )) + "" +String.format("%02d", CASCII.Convert(everything.charAt(i+2) )) + "";
			System.out.println("THE FORMAT TEXT IN LOOP " + formatText);

			System.out.println("THE FORMATTED TEXTTTTTT: " + formatText1);
			BigInteger formatBig = new BigInteger(formatText1);
			formatBig = new BigInteger(String.format("%06d", formatBig));
			System.out.println("THE BIGINT OF FORMAT TEXT: " + String.format("%06d", formatBig));
			System.out.println("THe cipher is: " + formatBig.modPow(e, n)); //this is correct
			BigInteger cipher = formatBig.modPow(e, n);
			try {
				PrintWriter outStream = new PrintWriter(new FileOutputStream("test.enc",true));
				//outStream.println(String.format("%06d", formatBig));
				outStream.println(cipher);

				outStream.close();
				//System.out.println("Part of test.enc written.");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("THe plaintext = "+ String.format("%06d",new BigInteger("2627674").modPow(new BigInteger("11296191"), n))); //correct for decryption


	}

}
