package com.simpletask.simplemeal.security;

import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenUtils {

	@Autowired
	private UserRepository userRepo;
	@Value("simple-meal-app")
	private String APP_NAME;

	@Value("${jwt.secret}")
	public String SECRET;
	@Value("6000000") // 30 min
	private int EXPIRES_IN;
	
//	@Value("1800000")
//	private int REFRESH_EXPIRES_IN;

	@Value("Authorization")
	private String AUTH_HEADER;
	
	private static final String AUDIENCE_WEB = "web";

	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	

	public String generateToken(String username) {
		User u = userRepo.findUserByEmail(username);
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setSubject(username)
				.setAudience(generateAudience())
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate(EXPIRES_IN))
				.claim("id", u.getId())
				.claim("role", u.getRole().getName())
				.signWith(getSignInKey(), SIGNATURE_ALGORITHM).compact();
	}
	
//	public String generateRefreshToken(String username) {
//		return Jwts.builder()
//				.setIssuer(APP_NAME)
//				.setSubject(username)
//				.setAudience(generateAudience())
//				.setIssuedAt(new Date())
//				.setExpiration(generateExpirationDate(REFRESH_EXPIRES_IN))
//				.signWith(getSignInKey(), SIGNATURE_ALGORITHM).compact();
//	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private String generateAudience() {
		
		return AUDIENCE_WEB;
	}

	private Date generateExpirationDate(int expires) {
		return new Date(new Date().getTime() + expires);
	}

	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);

		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	public String getUsernameFromToken(String token) {
		String username;
		
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			username = null;
		}
		
		return username;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}


	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);

		return (username != null
			&& username.equals(userDetails.getUsername()));
	}
	

//	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
//		return (lastPasswordReset != null && created.before(lastPasswordReset));
//	}
	

	public int getExpiredIn() {
		return EXPIRES_IN;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
}