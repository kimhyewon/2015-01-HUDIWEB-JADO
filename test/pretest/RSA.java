package pretest;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import org.junit.Test;

public class RSA {
	String publicKeyModulus;
	String publicKeyExponent;
	
	@Test
	public void keyToHex() throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		 
		KeyPair keyPair = generator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		 
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		 
		publicKeyModulus = publicSpec.getModulus().toString(16);
		publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
	}

	@Test
	public void hexToKey() throws Exception {
		BigInteger modulus = new BigInteger(publicKeyModulus, 16);
		BigInteger exponent = new BigInteger(publicKeyExponent, 16);
		
		RSAPublicKeySpec pubks = new RSAPublicKeySpec(modulus, exponent);
		 
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(pubks);
	}
}
