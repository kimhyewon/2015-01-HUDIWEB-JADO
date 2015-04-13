package core.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class EncryptRSA {
	final int keysize = 1024;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private String publicKeyModulus;
	private String publicKeyExponent;

	public EncryptRSA() throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(keysize);

		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();

		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

		publicKeyModulus = publicSpec.getModulus().toString(16);
		publicKeyExponent = publicSpec.getPublicExponent().toString(16);
	}
	
	public void encrypt(HttpSession session, Model model){
		session.setAttribute("__rsaPrivateKey__", privateKey);
		model.addAttribute("publicKeyModulus", publicKeyModulus);
		model.addAttribute("publicKeyExponent", publicKeyExponent);
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
