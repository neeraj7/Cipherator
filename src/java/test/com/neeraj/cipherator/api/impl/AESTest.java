package com.neeraj.cipherator.api.impl;

import org.junit.Assert;
import org.junit.Test;

public class AESTest {
	
	@Test
	public void testEncrypt() {
		String secretKey = "helloThere";
		String data = "I am neeraj.";
		AES cipherator = new AES();
		String encryptedText = cipherator.encrypt(data, secretKey);
		Assert.assertTrue(!data.equals(encryptedText));
		String decryptedText = cipherator.decrypt(encryptedText, secretKey);
		Assert.assertTrue(data.equals(decryptedText));
	}
	
}
