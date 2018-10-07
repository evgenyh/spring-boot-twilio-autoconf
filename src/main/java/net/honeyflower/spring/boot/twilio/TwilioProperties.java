package net.honeyflower.spring.boot.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("twilio")
public class TwilioProperties {

    private String accountSID;
    private String authToken;
    private String password;
    private String appId;
    private String apiKey;

}
