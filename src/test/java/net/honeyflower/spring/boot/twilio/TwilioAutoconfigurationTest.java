package net.honeyflower.spring.boot.twilio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.twilio.http.TwilioRestClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import net.honeyflower.spring.boot.twilio.verify.VerifyTokenBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TwilioAutoconfigurationTest.TestApplication.class)
public class TwilioAutoconfigurationTest {
	
	@Value("${twilio.apiKey}")
	private String signKey;

	@Autowired
	private TwilioRestClient twilioRestClient;
	
	@Autowired
	private VerifyTokenBuilder verifyTokenBuilder;

	@Test
	public void shouldInstantiateTwilioClientWithSettings() throws Exception {
		assertThat(twilioRestClient).isNotNull();
		assertThat(twilioRestClient.getAccountSid()).isEqualTo("testSID");
	}
	
	@Test
	public void shouldInstantiateVerifyTokenBuilderWithSettings() throws Exception {
		assertThat(verifyTokenBuilder).isNotNull();
		String token = verifyTokenBuilder.generateToken("+12345678900");
		assertThat(token).isNotNull();
		System.out.println(token);
		
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(signKey);
		
		assertTrue(parser.isSigned(token));
		
		Claims claims = parser.parseClaimsJws(token).getBody();
		
		Jwt jwt = parser.parse(token);
		assertThat(jwt).isNotNull();
		
	}

	@EnableAutoConfiguration
	public static class TestApplication {

	}

}
