package core.filter;

import jado.controller.Result;
import jado.controller.ResultValue;

import java.io.IOException;
import java.security.PrivateKey;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.util.DecryptRSA;
import core.util.HttpRequestWithModifiableParameters;

public class RsaDecryptFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RsaDecryptFilter.class);

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

				HttpRequestWithModifiableParameters param = new HttpRequestWithModifiableParameters(request);
				if (!(decryptedUserId.isSuccess() && decryptedUserPassword.isSuccess())) {
					param.setParameter("header", "Decrypted Fail");
					param.setParameter("message", "복호화 하는 과정에서 오류가 발생하였습니다. 페이지 새로고침후 재시도 해주시기 바랍니다.");
				}
				param.setParameter("j_username", (String) decryptedUserId.getValue("decryptedString"));
				param.setParameter("j_password", (String) decryptedUserPassword.getValue("decryptedString"));

				request = (HttpServletRequest) param;
			}
		}
		chain.doFilter(request, response);
		return;
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
}
