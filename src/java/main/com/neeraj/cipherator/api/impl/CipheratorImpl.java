package com.neeraj.cipherator.api.impl;

import com.neeraj.cipherator.api.Cipherator;
import com.neeraj.cipherator.enums.EncryptionAlgorithm;

public class CipheratorImpl implements Cipherator {

	@Override
	public String encrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data) {
		
		if (EncryptionAlgorithm.AES.equals(encryptionAlgorithm)) {
			AES aes = new AES();
			return aes.encrypt(data, key);
		} else if (EncryptionAlgorithm.CUSTOM.equals(encryptionAlgorithm)) {
			CustomCipher cc = new CustomCipher();
			return cc.encrypt(data, key);
		}
		else {
			throw new RuntimeException("Method not supported.");
		}
	}

	@Override
	public String decrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data) {
		if (EncryptionAlgorithm.AES.equals(encryptionAlgorithm)) {
			AES aes = new AES();
			return aes.decrypt(data, key);
		} else if (EncryptionAlgorithm.CUSTOM.equals(encryptionAlgorithm)) {
			CustomCipher cc = new CustomCipher();
			return cc.decrypt(data, key);
		}
		else {
			throw new RuntimeException("Method not supported.");
		}
	}

}
