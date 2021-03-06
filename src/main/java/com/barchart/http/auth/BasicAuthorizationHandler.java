package com.barchart.http.auth;

import com.barchart.http.util.Base64;

/**
 * Implements Basic HTTP authorization handling.
 * 
 * @author jeremy
 * 
 */
public class BasicAuthorizationHandler implements AuthorizationHandler {

	private final Authenticator authenticator;

	public BasicAuthorizationHandler(final Authenticator authenticator_) {
		authenticator = authenticator_;
	}

	@Override
	public String getMethod() {
		return "Basic";
	}

	@Override
	public String authorize(final String data) {

		try {

			final String[] userpass =
					new String(Base64.decode(data)).split(":");

			if (authenticator.authenticate(userpass[0], userpass[1])) {
				return userpass[0];
			}

		} catch (final Exception e) {
		}

		return null;

	}

	public interface Authenticator {

		public boolean authenticate(String username, String password);

	}

}
