package com.neeraj.cipherator.api.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.neeraj.cipherator.api.CipherApi;

public class CustomCipher implements CipherApi {

	private List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '.', ',', ';',
			':');
	Set<Integer> coprimes = new HashSet<>(Arrays.asList(2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 20, 22, 23, 25, 26, 28,
			29, 31, 32, 34, 35, 37, 40, 41, 43, 44, 46, 47, 49, 50, 52, 53, 55, 56));

	@Override
	public String encrypt(String dataToBeEncrypted, String key) {
		// First the shift the key chars to 5 using caesar cipher
		int n = chars.size();
		StringBuilder sb = new StringBuilder();
		for (char c : key.toCharArray()) {
			int index = chars.indexOf(c);
			int newIndex = (index + 5) % n;
			sb.append(chars.get(newIndex));
		}
		String shiftedKey = sb.toString();

		int[] keyIndexes = findCoprimeIndexesOfKey(shiftedKey);

		int i = 0, len = keyIndexes.length;
		StringBuilder encryptedText = new StringBuilder();
		for (char c : dataToBeEncrypted.toCharArray()) {
			if (chars.contains(c)) {
				if (i < len) {
					int dataIndex = chars.indexOf(c);
//					int keyIndex = chars.indexOf(shiftedKey.charAt(i));
					int keyIndex = keyIndexes[i];
					int newIndex = (keyIndex * dataIndex) % n;
					encryptedText.append(chars.get(newIndex));
					i++;
				} else {
					i = 0;
					int dataIndex = chars.indexOf(c);
//					int keyIndex = chars.indexOf(shiftedKey.charAt(i));
					int keyIndex = keyIndexes[i];
					int newIndex = (keyIndex * dataIndex) % n;
					encryptedText.append(chars.get(newIndex));
					i++;
				}
			} else {
				encryptedText.append(c);
			}
		}

		return encryptedText.toString();
	}

	/**
	 * Find the coprime indexes of each char of given key.
	 * 
	 * @param shiftedKey
	 * @return
	 */
	private int[] findCoprimeIndexesOfKey(String shiftedKey) {
		int keyIndexes[] = new int[shiftedKey.length()];
		// Find the coprime to the shifted keys
		for (int i = 0; i < keyIndexes.length; i++) {
			int keyIndex = chars.indexOf(shiftedKey.charAt(i));
			if (coprimes.contains(keyIndex)) {
				keyIndexes[i] = keyIndex;
			} else {
				keyIndexes[i] = coprimes.stream().filter(coprime -> keyIndex < coprime).findFirst().get();
			}
		}
		return keyIndexes;
	}

	@Override
	public String decrypt(String dataToBeDecrypted, String key) {
		int n = chars.size();
		// First the shift the key chars to 5 using caesar cipher
		StringBuilder sb = new StringBuilder();
		for (char c : key.toCharArray()) {
			int index = chars.indexOf(c);
			int newIndex = (index + 5) % n;
			sb.append(chars.get(newIndex));
		}
		String shiftedKey = sb.toString();
		int[] keyIndexes = findCoprimeIndexesOfKey(shiftedKey);

		int i = 0, len = keyIndexes.length;
		StringBuilder decryptedText = new StringBuilder();
		// write a method to find multiplicative inverse of key

		for (char c : dataToBeDecrypted.toCharArray()) {
			if (chars.contains(c)) {
				if (i < len) {
					int dataIndex = chars.indexOf(c);
					int keyIndex = multiplicativeInverse(keyIndexes[i]);
					int newIndex = (dataIndex * keyIndex) % n;
					decryptedText.append(chars.get(newIndex));
					i++;
				} else {
					i = 0;
					int dataIndex = chars.indexOf(c);
					int keyIndex = multiplicativeInverse(keyIndexes[i]);
					int newIndex = (keyIndex * dataIndex) % n;
					decryptedText.append(chars.get(newIndex));
					i++;
				}
			}
			else {
				decryptedText.append(c);
			}
		}

		return decryptedText.toString();
	}

	private int multiplicativeInverse(int keyIndex) {
		int n = chars.size();
		int m0 = n;
		int y = 0, x = 1;

		if (n == 1)
			return 0;

		while (keyIndex > 1) {
			// q is quotient
			int q = keyIndex / n;

			int t = n;

			// m is remainder now, process
			// same as Euclid's algo
			n = keyIndex % n;
			keyIndex = t;
			t = y;

			// Update x and y
			y = x - q * y;
			x = t;
		}

		// Make x positive
		if (x < 0)
			x += m0;

		return x;
	}
}
