package com.collabera;

import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static File encrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			File file = new File(
					"/Users/rutpatel/Documents/Rut/Collabera_JuMP/Eclipse_Workspace/FileEnDecrypt_WebApp/WebContent/Files/Encrypt/EncryptedFile.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
			fileWriter.flush();
			fileWriter.close();

			return file;
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static File decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			File file = new File(
					"/Users/rutpatel/Documents/Rut/Collabera_JuMP/Eclipse_Workspace/FileEnDecrypt_WebApp/WebContent/Files/Decrypt/DecryptedFile.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))));
			fileWriter.flush();
			fileWriter.close();

			return file;
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}