package com.neeraj.cipherator.api;

public interface CipherApi {
	
	String encrypt(String dataToBeEncrypted, String key);
	String decrypt(String dataToBeDecrypted, String key);

}
