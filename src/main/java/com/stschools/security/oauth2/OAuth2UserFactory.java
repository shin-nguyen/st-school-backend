package com.stschools.security.oauth2;

import com.stschools.common.enums.AuthProvider;
import lombok.SneakyThrows;

import javax.naming.AuthenticationException;
import java.util.Map;

public class OAuth2UserFactory {

    @SneakyThrows
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new AuthenticationException();
        }
    }
}
