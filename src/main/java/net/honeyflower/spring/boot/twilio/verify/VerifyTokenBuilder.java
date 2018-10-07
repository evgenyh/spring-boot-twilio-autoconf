package net.honeyflower.spring.boot.twilio.verify;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * generates token for twilio phone  verify flow
 * @see https://www.twilio.com/docs/verify/tutorials/android-sdk-integration-custom-backend
 * @author Eugene
 *
 */
public class VerifyTokenBuilder {
	
	private final String APP_ID;
	private final byte[] AUTHY_API_KEY;
	private final JwtBuilder builder;
	
	
	public VerifyTokenBuilder(String appId, String twilioAuthyKey) {
		APP_ID = appId;
		AUTHY_API_KEY = twilioAuthyKey.getBytes();
		builder = Jwts.builder();
	}



	/**
	 * phone number should be in e164 format
	 * @param e164PhoneNumber
	 * @return
	 */
	public String generateToken(String phone) {
		return builder
				.claim("phone_number", phone)
				.claim("app_id", APP_ID)
				.signWith(SignatureAlgorithm.HS256, AUTHY_API_KEY)
				.compact();
	}
}
