package net.honeyflower.spring.boot.twilio.verify;

import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

/**
 * generates token for twilio phone  verify flow
 * @see https://www.twilio.com/docs/verify/tutorials/android-sdk-integration-custom-backend
 * @author Eugene
 *
 */
public class VerifyTokenBuilder {
	
	private final String APP_ID;
	private final byte[] API_KEY;
	private final JwtBuilder builder;
	
	
	public VerifyTokenBuilder(String appId, String apiKey) {
		APP_ID = appId;
		API_KEY = TextCodec.BASE64.decode(apiKey);
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
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, API_KEY)
				.compact();
	}
}
