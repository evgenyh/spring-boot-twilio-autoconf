package net.honeyflower.spring.boot.twilio;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;

import net.honeyflower.spring.boot.twilio.verify.VerifyTokenBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TwilioProperties.class)
public class TwilioAutoconfiguration {

    private final TwilioProperties properties;

    @Autowired
    public TwilioAutoconfiguration(TwilioProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "twilio", value = {"accountSID", "authToken"})
    @ConditionalOnClass(TwilioRestClient.class)
    @ConditionalOnMissingBean(TwilioRestClient.class)
    public TwilioRestClient twilioRestClient() {
        if (properties.getPassword() != null) {
        	Twilio.init(properties.getAccountSID(), properties.getPassword(), properties.getAccountSID());
            return Twilio.getRestClient();
        } else {
        	Twilio.init(properties.getAccountSID(), properties.getAuthToken());
            return Twilio.getRestClient();
        }
    }
    
    @Bean
    @ConditionalOnProperty(prefix = "twilio", value = {"appId", "authyApiKey"})
    public VerifyTokenBuilder verifyTokenBuilder() {
        return new VerifyTokenBuilder(properties.getAppId(), properties.getAuthyApiKey());
    }
}