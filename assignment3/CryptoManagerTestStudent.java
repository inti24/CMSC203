import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

	@Test
    public void testCaesarEncryptionDecryption() {
        String text = "*&^%&*";
        int key = 3;
        String encrypted = CryptoManager.caesarEncryption(text, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);
        assertEquals(text, decrypted);
    }
	
	
	@Test
	public void testplayfairEncryptionDecryption() {
		String text = "D20(3#R#(@";
		String Key = "D20DL_201234";
		String encrypted = CryptoManager.playfairEncryption(text, Key);
		String decrypted = CryptoManager.playfairDecryption(encrypted,Key);
		assertEquals(text, decrypted);
	}
	
	
	@Test
	public void testvigenereEncryptionDecryption() {
		String text = "@!#ASDWQE$@%";
		String key = ")@(#*_ @$#FSDJFI";
		String encrypted = CryptoManager.vigenereEncryption(text, key);
		String decrypted = CryptoManager.vigenereDecryption(encrypted, key);
		assertEquals(text, decrypted);
	}
	
	   @Test
	    public void testIsStringInBoundsFalse() {
	        assertFalse(CryptoManager.isStringInBounds("welcome"));
	    }
	   
	   @Test
	   public void testIsStringInBoundsTrue() {
		   assertTrue(CryptoManager.isStringInBounds("WELCOME"));
	   }
	
}

