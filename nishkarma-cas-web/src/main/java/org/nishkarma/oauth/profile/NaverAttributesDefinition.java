package org.nishkarma.oauth.profile;

import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

public class NaverAttributesDefinition extends OAuthAttributesDefinition {

    public static final String NICKNAME = "nickname";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    public NaverAttributesDefinition() {
        addAttribute(NICKNAME, Converters.stringConverter);
        addAttribute(NAME, Converters.stringConverter);
        addAttribute(EMAIL, Converters.stringConverter);
    }
    
}
