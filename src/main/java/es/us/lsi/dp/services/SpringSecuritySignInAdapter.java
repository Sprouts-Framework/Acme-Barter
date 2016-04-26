package es.us.lsi.dp.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

@Service
@Transactional
public class SpringSecuritySignInAdapter implements SignInAdapter {

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
		return null;
	}

}
