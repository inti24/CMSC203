/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */

public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_";
    // had to change this so the space is the first place unlike what document show where ' ' is after 9

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */   

    public static String vigenereEncryption(String plainText, String key) {
         
    // check if the string or key are within the allowed 64 set
    	if(!isStringInBounds(plainText) || !isStringInBounds(key)) {
    	System.out.println("The selected string is not in bounds, try again.");
    	}
    	
    	StringBuilder encryptedText = new StringBuilder();
    	
    	for(int i = 0; i <plainText.length(); i++) {
    		char textLetter = plainText.charAt(i); //gets the character from the plain text and singles it out 
    		int textIndex = ALPHABET64.indexOf(textLetter); // this then finds the character from plain text and finds it in mod 64 and turns it into a number
    		
    		char keyLetter = key.charAt(i%key.length()); // similar to textLetter but i%key.length() makes it so that it wraps around so it stays in bounds
    		int keyIndex =  ALPHABET64.indexOf(keyLetter);
    		
    		int encryptedIndex = (textIndex + keyIndex) % RANGE; // this is simply doing the vigenere cipher but the % RANGE makes it so that it stays within the mod 64
    		
    		encryptedText.append(ALPHABET64.charAt(	encryptedIndex));
    	}
    	
    	return encryptedText.toString();
    	
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
    	// check if the encrypted text or key are within the allowed 64 set
    	if(!isStringInBounds(encryptedText) || !isStringInBounds(key)) {
    		System.out.println("The selected string is not in bounds, Try again.");
    	}
    	
    	StringBuilder decryptedText = new StringBuilder(); // StringBuilder to make the decryptedText without having to make multiple instances of String class
    	for(int i = 0; i <encryptedText.length();i++) { // loop to go through every letter of the encrypted text
    	
    		char keyLetter = key.charAt(i%key.length());
    		int keyIndex= ALPHABET64.indexOf(keyLetter); // gets the index of the decrypted text and turns it into the position of mod 64
    		
    		char eText =encryptedText.charAt(i);
    		int eIndex = ALPHABET64.indexOf(eText); // gets the index of the key and turns it into the position of mod 64
    	
    	int decryptedIndex = (eIndex - keyIndex +RANGE)%RANGE;
    	decryptedText.append(ALPHABET64.charAt(decryptedIndex));
    	}
    	return decryptedText.toString();
    }


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    

    public static String playfairEncryption(String plainText, String key) {
    	if(!isStringInBounds(plainText) || !isStringInBounds(key)) {
    		System.out.println("The selected string is not in bounds, Try again.");
    		return null;
    	}
    	//convert to uppercase should fix a problem in JUnit
    	plainText=plainText.toUpperCase();
    	key=key.toUpperCase();
    	
    	//making the matrix
			//make matrix 8x8 
    		char [][] pfMatrix = new char[8][8];
    		//
    		StringBuilder matrix = new StringBuilder();
    		//adds the key into a string which will be moved into a matrix
    		for(int i =0; i < key.length();i++) {
    			char keyLetter = key.charAt(i);
    			//checking if the letter is already in the string
    			if(matrix.indexOf(String.valueOf(keyLetter)) ==-1) {
    				matrix.append(keyLetter);
    			}
    		}
        	
    		// adds the rest of the mod 64 to the string
    		for(int i =0; i<ALPHABET64.length();i++) {
    			char alphabetLetter = ALPHABET64.charAt(i);
    			
    			if(matrix.indexOf(String.valueOf(alphabetLetter))==-1) {
    				matrix.append(alphabetLetter);
    			}
    		}
    		
    
    		
    		
    		//entering the letters into the matrix
    		int index = 0;
    		for(int row = 0; row<8; row++) {
    			for(int col = 0; col < 8 ; col++) {
    				pfMatrix[row][col] = matrix.charAt(index);
    				index++;
    			}
    		}
    		
        
    		//prepare digrpahs 
    		
    		StringBuilder groups = new StringBuilder();
    		//making the string which will hold the final encrypted string
    		StringBuilder encryptedText = new StringBuilder();
    		
    		//transferring the plain text into a string to then be able to manipulate it later
    		for(int i = 0; i<plainText.length(); i++) {
    			groups.append(plainText.charAt(i));
    		}
    		
    		
    		
    		for(int i = 0; i<groups.length();i+=2) { //grouping into pairs
    			char first = groups.charAt(i);
    			char second; //= groups.charAt(i+1);
    			
    			if(i+1 <plainText.length()) {
    				second =plainText.charAt(i+1);
    			}else {
    				second ='X';
    			}
    			
    			//if one leftover at end append x to make final pair
        		if(groups.length() %2 != 0) {
        			groups.append('X');
        		}
    			/*if(first==second) {
    				second='X';
    				i--;
    			}
    			*/
    			
    		//encryption
    			
    			//finding position 
    			int row1 =0, row2 = 0 ,col1=0 ,col2=0;
    			//checking the matrix to see the location of  "first" and "second" 
    			for(int row =0;row<8;row++) {
    				for(int col =0 ;col<8;col++) {
    					
    					if(pfMatrix[row][col]== first) {
    						row1 = row;
    						col1 = col;
    					}
    					if(pfMatrix[row][col] == second) {
    						row2= row;
    						col2 = col;		
    					}
    				}
    			}
    			//rules 
    			//same row rule
    			if(row1==row2) {
    				encryptedText.append(pfMatrix[row1][(col1+1)%8]);// moves right (and wraps within rows)
    				encryptedText.append(pfMatrix[row2][(col2+1)%8]);//moves right (and wraps within rows)
    			}
    			//same column rule
    			else if(col1 == col2) {
    				encryptedText.append(pfMatrix[(row1+1)%8][col1]);//moves down a row (and wraps within the column)
    				encryptedText.append(pfMatrix[(row2+1)%8][col2]);//moves down a row( and wraps within the column)
    			}
    			//rectangle rule
    			else {
    				encryptedText.append(pfMatrix[row1][col2]);//flips the col1 with col2
    				encryptedText.append(pfMatrix[row2][col1]);//flips the col2 with col1
    			}
    			
    			
    			
    			
    		}
    		
    		
    		return encryptedText.toString();
    		
    		
        }
    	
    	
    

    // playfair Decryption
    public static String playfairDecryption(String encryptedText, String key) {
    	if(!isStringInBounds(encryptedText) || !isStringInBounds(key)) {
    		System.out.println("The selected string is not in bounds, Try again.");
    		return null;
    	}
    	//making the matrix
			//make matrix 8x8 
    		char [][] pfMatrix = new char[8][8];
    		//
    		StringBuilder matrix = new StringBuilder();
    		//adds the key into a string which will be moved into a matrix
    		for(int i =0; i < key.length();i++) {
    			char keyLetter = key.charAt(i);
    			//checking if the letter is already in the string
    			if(matrix.indexOf(String.valueOf(keyLetter)) ==-1) {
    				matrix.append(keyLetter);
    			}
    		}
        	
    		// adds the rest of the mod 64 to the string
    		for(int i =0; i<ALPHABET64.length();i++) {
    			char alphabetLetter = ALPHABET64.charAt(i);
    			
    			if(matrix.indexOf(String.valueOf(alphabetLetter))==-1) {
    				matrix.append(alphabetLetter);
    			}
    		}
    		
    
    		
    		
    		//entering the letters into the matrix
    		int index = 0;
    		for(int row = 0; row<8; row++) {
    			for(int col = 0; col < 8 ; col++) {
    				pfMatrix[row][col] = matrix.charAt(index);
    				index++;
    			}
    		}
    		
        
    		//prepare digrpahs 
    		
    		StringBuilder groups = new StringBuilder();
    		//making the string which will hold the final encrypted string
    		StringBuilder decryptedText = new StringBuilder();
    		
    		//transferring the plain text into a string to then be able to manipulate it later
    		for(int i = 0; i<encryptedText.length(); i++) {
    			groups.append(encryptedText.charAt(i));
    		}
    		//if one leftover at end append x to make final pair
    		if(groups.length() %2 != 0) {
    			groups.append('X');
    		}
    		
    		
    		for(int i = 0; i<groups.length();i+=2) { //grouping into pairs
    			char first = groups.charAt(i);
    			char second = groups.charAt(i+1);
    			
    		//decryption
    			
    			//finding position 
    			int row1 =0, row2 = 0 ,col1=0 ,col2=0;
    			//checking the matrix to see the location of  "first" and "second" 
    			for(int row =0;row<8;row++) {
    				for(int col =0 ;col<8;col++) {
    					
    					if(pfMatrix[row][col]== first) {
    						row1 = row;
    						col1 = col;
    					}
    					if(pfMatrix[row][col] == second) {
    						row2= row;
    						col2 = col;		
    					}
    				}
    			}
    			//rules 
    			if(row1==row2) {
    				decryptedText.append(pfMatrix[row1][(col1-1+8)%8]);// moves left (and wraps within rows)
    				decryptedText.append(pfMatrix[row2][(col2-1+8)%8]);//moves left (and wraps within rows)
    			}else if(col1 == col2) {
    				decryptedText.append(pfMatrix[(row1-1+8)%8][col1]);//moves up a row (and wraps within the column)
    				decryptedText.append(pfMatrix[(row2-1+8)%8][col2]);//moves up a row( and wraps within the column)
    			}else {
    				decryptedText.append(pfMatrix[row1][col2]);//flips the col1 with col2
    				decryptedText.append(pfMatrix[row2][col1]);//flips the col2 with col1
    			}
    			
    			
    			for(int x =0;x<decryptedText.length();x++ ) {
    				if(decryptedText.charAt(i) =='X'&& decryptedText.charAt(i-1)== decryptedText.charAt(i+1)) {
    					decryptedText.deleteCharAt(i);
    					i--;
    				}
    				
    			}
    			
    			if(decryptedText.charAt(decryptedText.length()-1)=='X') {
    				decryptedText.deleteCharAt(decryptedText.length()-1);
    			}
    			
    			
    		}
    		
    		
    		return decryptedText.toString();
    		
    		
        } 
    	
    	
    

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
 
    public static String caesarEncryption(String plainText, int key) {
    	  // check if the string or key are within the allowed 64 set
    	if(!isStringInBounds(plainText)) {
    	System.out.println("The selected string is not in bounds, try again.");
    	return null;
    	}
    	
    	StringBuilder encryptedText = new StringBuilder();
    	
    	for(int i = 0; i <plainText.length(); i++) {
    		char textLetter = plainText.charAt(i); //gets the character from the plain text and singles it out 
    		int textIndex = ALPHABET64.indexOf(textLetter); // this then finds the character from plain text and finds it in mod 64 and turns it into a number
    	
    		/*
    		if(textIndex== -1) {
    				return null;
    		}
    	*/
    		
    		int encryptedIndex = (textIndex + key ) % ALPHABET64.length(); // this is simply doing the vigenere cipher but the % RANGE makes it so that it stays within the mod 64
    		
    		encryptedText.append(ALPHABET64.charAt(	encryptedIndex));
    	}
    	
    	return encryptedText.toString();
    	
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {

  	  // check if the string or key are within the allowed 64 set
  	if(!isStringInBounds(encryptedText)) {
  	System.out.println("The selected string is not in bounds, try again.");
  	return null;
  	}
  	
  	StringBuilder decryptedText = new StringBuilder();
  	
  	for(int i = 0; i <encryptedText.length(); i++) {
  		char textLetter = encryptedText.charAt(i); //gets the character from the plain text and singles it out 
  		int textIndex = ALPHABET64.indexOf(textLetter); // this then finds the character from plain text and finds it in mod 64 and turns it into a number
  		
  	/*	if(textIndex== -1) {
			return null;
	}
  		*/
  		
  		int decryptedIndex = (textIndex - key+ALPHABET64.length()) % ALPHABET64.length(); // this is simply doing the vigenere cipher but the % RANGE makes it so that it stays within the mod 64
  		
  		decryptedText.append(ALPHABET64.charAt(	decryptedIndex));
  	}
  	
  	return decryptedText.toString();
  	
  
    }    

}
