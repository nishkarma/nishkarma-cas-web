package org.nishkarma.oauth.profile;

import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

public class KakaoAttributesDefinition extends OAuthAttributesDefinition {

    public static final String NICKNAME = "nickname";

    public KakaoAttributesDefinition() {
        addAttribute(NICKNAME, Converters.stringConverter);
    }
    
}
