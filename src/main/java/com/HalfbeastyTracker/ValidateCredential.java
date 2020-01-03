package com.HalfbeastyTracker;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;

import java.util.Optional;

public class ValidateCredential {

    public ValidateCredential() {
    }

    public boolean isCredentialValid(String accessToken) {

        // Create the credential to be tested
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                accessToken
        );

        // Create new instance of TwitchIdentityProvider to access getAdditionalCredentialInformation()
        TwitchIdentityProvider twitchIdentityProvider = new TwitchIdentityProvider(null, null, null);

        // Check credential validity
        Optional<OAuth2Credential> credentialValidation = twitchIdentityProvider.getAdditionalCredentialInformation(credential);
        boolean isValid = !credentialValidation.equals(Optional.empty());

        return isValid;
    }
}
