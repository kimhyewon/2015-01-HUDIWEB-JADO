package core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DecryptRSA {
	Cipher cipher;

	public DecryptRSA(PrivateKey privateKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
	}

	public String decryptRsa(String securedValue) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

		byte[] encryptedBytes = hexToByteArray(securedValue);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		
		String decryptedValue = new String(decryptedBytes, "utf-8");
		return decryptedValue;
	}

	private byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

}
