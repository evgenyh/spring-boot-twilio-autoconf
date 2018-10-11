package net.honeyflower.spring.boot.twilio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.twilio.http.TwilioRestClient;

import net.honeyflower.spring.boot.twilio.verify.VerifyTokenBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TwilioAutoconfigurationTest.TestApplication.class)
public class TwilioAutoconfigurationTest {

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
		assertThat(verifyTokenBuilder.generateToken("+12345678900")).isEqualTo("eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZV9udW1iZXIiOiIrMTIzNDU2Nzg5MDAiLCJhcHBfaWQiOiIxMjM0NSIsImlhdCI6MTUzOTI2MDg1Nn0.azBCHBjWgAe9EQ9Q49ICbOqbpkM0997Z5JxoVWcUD24");
	}

	@EnableAutoConfiguration
	public static class TestApplication {

	}

}
