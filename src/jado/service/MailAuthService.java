package jado.service;

import jado.dao.MailAuthDao;
import jado.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailAuthService {
	
	@Autowired private MailAuthDao mailAuthDao;
	@Autowired private UserDao userDao;

	public boolean isAlreadyVerified(String userEmail) {
		return mailAuthDao.isAlreadyVerified(userEmail);
	}

	public boolean verify(String userEmail, String uuid) {
		return mailAuthDao.verify(userEmail, uuid);
	}

	public void updateMailAuthStatus() {
		userDao.updateMailAuthStatus();
	}
}
