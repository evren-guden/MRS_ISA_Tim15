package rs.travel.bookingWithEase.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rs.travel.bookingWithEase.model.User;

@Component
public class TokenUtils {

	@Value("somesecret")
	public String SECRET;

	@Value("Authorization")
	private String AUTH_HEADER;

	@Value("300")
	private int EXPIRES_IN;

	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

	// Function for generating new JWT token

	Date now = new Date();
	Date validity = new Date(now.getTime() + EXPIRES_IN * 10000);

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SIGNATURE_ALGORITHM, SECRET)
				.compact();
	}

	// Functions for validating JWT token data

	public Boolean validateToken(String token, UserDetails userDetails) {
		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);

		return (username != null && username.equals(userDetails.getUsername()));
	}

	// Functions for getting data from token

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
			System.out.println("Claims try");
		} catch (Exception e) {
			e.printStackTrace();
			claims = null;
			System.out.println("Claims catch");
		}
		return claims;
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
			System.out.println("try token " + username);
		} catch (Exception e) {
			username = null;
			System.out.println("catch token");
		}
		return username;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	// Functions for getting JWT token out of HTTP request

	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);
		System.out.println("Token " + authHeader);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}

}
