package core.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class EncryptRSA {
	final int keysize = 1024;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private String publicKeyModulus;
	private String publicKeyExponent;

	public EncryptRSA() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(keysize);

		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();

		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(
				publicKey, RSAPublicKeySpec.class);

		publicKeyModulus = publicSpec.getModulus().toString(16);
		publicKeyExponent = publicSpec.getPublicExponent().toString(16);
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public String getPublicKeyModulus() {
		return publicKeyModulus;
	}

	public String getPublicKeyExponent() {
		return publicKeyExponent;
	}

}
