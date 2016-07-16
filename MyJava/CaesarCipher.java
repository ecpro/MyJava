import edu.duke.*;

public class CaesarCipher {
	private String alphaLow = "abcdefghijklmnopqrstuvwxyz";
	private String alphaUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public String encrypt(String input , int key) {
		key = key % 26 ;
		if(key == 0) return input;
		String shiftedLow = alphaLow.substring(key, alphaLow.length()) + alphaLow.substring(0, key);
		String shiftedUp = alphaUp.substring(key, alphaUp.length()) + alphaUp.substring(0, key);
		
		StringBuilder encrypted = new StringBuilder("");
		for(int i = 0;i<input.length();i++) {
			char ch = input.charAt(i);
			int index = 0;
			if(Character.isLowerCase(ch)) {
				index = alphaLow.indexOf(ch);
				encrypted = encrypted.append(shiftedLow.charAt(index));
			} else if(Character.isUpperCase(ch)) {
				index = alphaUp.indexOf(ch);
				encrypted = encrypted.append(shiftedUp.charAt(index));
			} else {
				encrypted = encrypted.append(ch);
			}
			
		}
		return encrypted.toString();
		
	}
	public String encryptTwoKeys(String input, int key1 ,int key2) {
		
		// modulo-26 for the key values to keep key in 0 to 25 range
		
		key1 = key1 % 26;
		key2 = key2 % 26;
		String LowerShiftedByKey1 = "",UpperShiftedByKey1 = "",LowerShiftedByKey2 = "",UpperShiftedByKey2 = "";
		// Alphabet string shifted by key1 for both the Lower and UPper case alphabet
		if(key1 !=0)
		{
			LowerShiftedByKey1 = alphaLow.substring(key1, alphaLow.length()) + alphaLow.substring(0, key1);
			UpperShiftedByKey1 = alphaUp.substring(key1, alphaUp.length()) + alphaUp.substring(0, key1);
		}
		else {
			LowerShiftedByKey1 = alphaLow;
			UpperShiftedByKey1 = alphaUp;
		}
		// Alphabet string shifted by key2 for both the Lower and Upper case alphabet
		if(key2 !=0) {
			LowerShiftedByKey2 = alphaLow.substring(key2, alphaLow.length()) + alphaLow.substring(0, key2);
			UpperShiftedByKey2 = alphaUp.substring(key2, alphaUp.length()) + alphaUp.substring(0,key2);
		}
		else {
			LowerShiftedByKey2 = alphaLow;
			UpperShiftedByKey2 = alphaUp;
		}
		StringBuilder encrpyted = new StringBuilder("");
		for(int i = 0;i<input.length();i++) {
			char ch = input.charAt(i);
			int index = 0;
			if(i % 2 == 0) {
				if(Character.isLowerCase(ch)) {
					index = alphaLow.indexOf(ch);
					encrpyted = encrpyted.append(LowerShiftedByKey1.charAt(index));
				}
				else if(Character.isUpperCase(ch)) {
					index = alphaUp.indexOf(ch);
					encrpyted = encrpyted.append(UpperShiftedByKey1.charAt(index));
				}
				else {
					encrpyted = encrpyted.append(ch);
				}
			}
			else {
				if(Character.isLowerCase(ch)) {
					index = alphaLow.indexOf(ch);
					encrpyted = encrpyted.append(LowerShiftedByKey2.charAt(index));
				}
				else if(Character.isUpperCase(ch)) {
					index = alphaUp.indexOf(ch);
					encrpyted = encrpyted.append(UpperShiftedByKey2.charAt(index));
				}
				else {
					encrpyted = encrpyted.append(ch);
				}
			}
		}
		
		return encrpyted.toString();
	}
	
	public void testCaesar() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encrypt(message, 23);
		System.out.println(message);
		System.out.println(encrypted);
		
	}
	
	public static void main(String[] args) {
		
		CaesarCipher cc = new CaesarCipher();
		FileResource fr = new FileResource();
		String message = fr.asString();
		//System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
		//System.out.println(cc.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 12, 2));
		//System.out.println(cc.encrypt("Fy stts gj ns ymj htskjwjshj wttr bnym dtzw mfy ts ktw f xzwuwnxj ufwyd. DJQQ QTZI!", 21));
//		for(int i = 1 ;i < 26 ;i++){
//			for(int j = 1 ; j < 26 ; j++) {
//		
//				System.out.println(i + " " + j + " " + cc.encryptTwoKeys("Uybi",i,j) +"\n");
//			}
//		System.out.println(cc.encryptTwoKeys(message,,3));
//		
//		}
		
		System.out.println(cc.encryptTwoKeys(message, 9, 22));
		
	}
		//cc.testCaesar();
		
	
	
}
