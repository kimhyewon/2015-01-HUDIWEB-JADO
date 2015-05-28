package core.util;

import jado.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthorityManager {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthorityManagerDao authorityManagerDao;
	
	public void setUserAuthority(User user, String authorityTobeChanged, HttpSession session) {
		updateRoleOnDb(user, authorityTobeChanged);
		updateAuthorityOnContext(authorityTobeChanged);
		updateAuthorityOnSession(session, authorityTobeChanged);
	}

	private void updateAuthorityOnSession(HttpSession session, String authorityTobeChanged) {
		session.setAttribute("userAuthority", authorityTobeChanged);		
	}

	private void updateAuthorityOnContext(String authorityTobeChanged) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = modifyAuthoritiesToUseAsNewAuthMaterial(authorityTobeChanged, auth);
		Authentication newAuth = createNewAuth(auth, authorities);
		setAuthentication(newAuth);
	}

	private List<GrantedAuthority> modifyAuthoritiesToUseAsNewAuthMaterial(String authorityTobeChanged, Authentication auth) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
		authorities.removeAll(authorities);
		authorities.add(new SimpleGrantedAuthority(authorityTobeChanged));
		return authorities;
	}

	private Authentication createNewAuth(Authentication auth, List<GrantedAuthority> authorities) {
		return new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
	}

	private void setAuthentication(Authentication newAuth) {
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

	private void updateRoleOnDb(User user, String authorityTobeChanged) {
		authorityManagerDao.updateUserRole(user, authorityTobeChanged);
	}
}
