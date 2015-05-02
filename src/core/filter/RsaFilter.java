package core.filter;

import jado.controller.Result;
import jado.controller.ResultValue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.util.DecryptRSA;
import core.util.EncryptRSA;

@WebFilter(urlPatterns = { "/user/login", "/user/edit", "/user" })
public class RsaFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RsaFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String method = request.getMethod();

		Result decryptedUserId = null;
		Result decryptedUserPassword = null;
		PrivateKey privateKey = null;
		String userId = null;
		String password = null;

		if (method != null && !"".equals(method)) {
			HttpSession session = request.getSession();
			// decrypt
			if ("POST".equals(method.toUpperCase())) {
				logger.debug("decrypt");
				userId = req.getParameter("idEncryption");
				password = req.getParameter("pwEncryption");

				privateKey = getPrivateKeyAndDestroyKey(session);
				decryptedUserId = decryptString(privateKey, userId);
				decryptedUserPassword = decryptString(privateKey, password);

				if (!(decryptedUserId.isSuccess() && decryptedUserPassword.isSuccess())) {
					request.setAttribute("errorMessage", "암호화 전송된 정보를 해독하는 과정에서 오류 발생, 페이지 새로고침후 재시도 바람");
				}
				logger.debug("id {}", decryptedUserId.getValue("decryptedString"));
				logger.debug("pw {}", decryptedUserPassword.getValue("decryptedString"));
				request.setAttribute("userId", decryptedUserId.getValue("decryptedString"));
				request.setAttribute("password", decryptedUserPassword.getValue("decryptedString"));
			}
			// encrypt
			else if ("GET".equals(method.toUpperCase())) {
				logger.debug("encrypt");
				if (!encryptPrepareProcess(session, request).isSuccess()) {
					request.setAttribute("errorMessage", "암호화 하는 과정에서 오류 발생, 페이지 새로고침후 재시도 바람");
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	private Result decryptString(PrivateKey privateKey, String decryptTargetString) {
		String decryptedString = null;
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			decryptedString = rsa.decryptRsa(decryptTargetString);
		} catch (Exception e) {
			return new Result(false, e.getMessage());
		}
		return new Result(true, new ResultValue("decryptedString", decryptedString));
	}

	private PrivateKey getPrivateKeyAndDestroyKey(HttpSession session) {
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		return privateKey;
	}

	private Result encryptPrepareProcess(HttpSession session, HttpServletRequest request) {
		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			request.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			request.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			session.removeAttribute("__rsaPrivateKey__");
			request.setAttribute("errorMessage", e.getMessage());
			return new Result(false, e.getMessage());
		}
		return new Result(true);
	}

}
