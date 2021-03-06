package de.thm.arsnova.entities;

import java.io.Serializable;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.scribe.up.profile.facebook.FacebookProfile;
import org.scribe.up.profile.google.Google2Profile;
import org.scribe.up.profile.twitter.TwitterProfile;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import de.thm.arsnova.services.UserSessionService;

public class User implements Serializable {
	public static final String GOOGLE = "google";
	public static final String TWITTER = "twitter";
	public static final String FACEBOOK = "facebook";
	public static final String THM = "thm";
	public static final String LDAP = "ldap";
	public static final String ANONYMOUS = "anonymous";

	private static final long serialVersionUID = 1L;
	private String username;
	private String type;
	private UserSessionService.Role role;

	public User(Google2Profile profile) {
		setUsername(profile.getEmail());
		setType(User.GOOGLE);
	}

	public User(TwitterProfile profile) {
		setUsername(profile.getScreenName());
		setType(User.TWITTER);
	}

	public User(FacebookProfile profile) {
		setUsername(profile.getLink());
		setType(User.FACEBOOK);
	}

	public User(AttributePrincipal principal) {
		setUsername(principal.getName());
		setType(User.THM);
	}

	public User(AnonymousAuthenticationToken token) {
		setUsername(User.ANONYMOUS);
		setType(User.ANONYMOUS);
	}

	public User(UsernamePasswordAuthenticationToken token) {
		setUsername(token.getName());
		setType(LDAP);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserSessionService.Role getRole() {
		return role;
	}

	public void setRole(UserSessionService.Role role) {
		this.role = role;
	}

	public boolean hasRole(UserSessionService.Role role) {
		return this.role == role;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		// See http://stackoverflow.com/a/113600
		final int theAnswer = 42;
		final int theOthers = 37;

		int result = theAnswer;
		result = theOthers * result + this.username.hashCode();
		return theOthers * result + this.type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !obj.getClass().equals(this.getClass())) {
			return false;
		}
		User other = (User) obj;
		return this.username.equals(other.username) && this.type.equals(other.type);
	}

}
