package com.neeraj.cipherator.api.impl;

import org.junit.Assert;
import org.junit.Test;

import com.neeraj.cipherator.api.Cipherator;
import com.neeraj.cipherator.enums.EncryptionAlgorithm;

public class AESTest {
	
	@Test
	public void testEncrypt() {
		String secretKey = "helloThere";
		String data = "I am neeraj.";
		Cipherator cipherator = new AES();
		String encryptedText = cipherator.encrypt(EncryptionAlgorithm.AES, secretKey, data);
		Assert.assertTrue(!data.equals(encryptedText));
		String decryptedText = cipherator.decrypt(EncryptionAlgorithm.AES, secretKey, encryptedText);
		Assert.assertTrue(data.equals(decryptedText));
	}
	
}
