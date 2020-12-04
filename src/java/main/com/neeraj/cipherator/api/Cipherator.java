package com.neeraj.cipherator.api;

import com.neeraj.cipherator.enums.EncryptionAlgorithm;

/**
 * 
 * @author neeraj.kumar
 *
 */
public interface Cipherator {
	
	String encrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data);
	
	String decrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data);

}
