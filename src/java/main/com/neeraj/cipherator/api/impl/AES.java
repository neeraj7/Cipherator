package com.neeraj.cipherator.api.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.neeraj.cipherator.api.Cipherator;
import com.neeraj.cipherator.enums.EncryptionAlgorithm;

public class AES implements Cipherator {
	
	private SecretKeySpec secretKey;
	private byte[] key;
	 
	public void setKey(String key) {
		MessageDigest sha = null;
		try {
			this.key = key.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			this.key = sha.digest(this.key);
			this.key = Arrays.copyOf(this.key, 16);
			this.secretKey = new SecretKeySpec(this.key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String encrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data) {
		
		try {
			setKey(key);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encryption: " + e.toString());
		}
		return null;		
	}

	@Override
	public String decrypt(EncryptionAlgorithm encryptionAlgorithm, String key, String data) {
		
		try {
			setKey(key);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
		} catch (Exception e) {
			System.out.println("Error while encryption: " + e.toString());
		}
		return null;		
	}

}
