package jado.service;

import jado.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveUserService {

	@Autowired private UserDao userDao;
	
	public void removeSeller(String userId) {
		userDao.removeSeller(userId);
	}

	public void removeCustomer(String userId) {
		userDao.removeCustomer(userId);
	}

}
