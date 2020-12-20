package com.neeraj.cipherator.api.impl;

import org.junit.Assert;
import org.junit.Test;

public class CutomCipherTest {

	@Test
	public void testEncrypt() {
		String secretKey = "abc";
		String data = "neeraj";
		CustomCipher cipherator = new CustomCipher();
		String encryptedText = cipherator.encrypt(data, secretKey);
		Assert.assertTrue(!data.equals(encryptedText));
		String decryptedText = cipherator.decrypt(encryptedText, secretKey);
		Assert.assertTrue(data.equals(decryptedText));
	}
	
}
