package core.filter;

import jado.controller.Result;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

import core.util.EncryptRSA;
import core.util.HttpRequestWithModifiableParameters;

@WebFilter(urlPatterns = { "/user/login", "/user/edit", "/user" })
public class RsaSetPublicKeyFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RsaSetPublicKeyFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String method = request.getMethod();

		if (method != null && !"".equals(method)) {
			HttpSession session = request.getSession();

			// encrypt
			if ("GET".equals(method.toUpperCase())) {
				logger.debug("encrypt");
				if (!encryptPrepareProcess(session, request).isSuccess()) {
					HttpRequestWithModifiableParameters param = new HttpRequestWithModifiableParameters((HttpServletRequest) req);
					param.setParameter("title", "Encrypted Fail");
					param.setParameter("message", "암호화 하는 과정에서 오류가 발생하였습니다. 페이지 새로고침후 재시도 해주시기 바랍니다.");
					request = (HttpServletRequest) param;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
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
