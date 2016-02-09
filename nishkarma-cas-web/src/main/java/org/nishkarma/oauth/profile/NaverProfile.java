package org.nishkarma.oauth.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.OAuth20Profile;

public class NaverProfile extends OAuth20Profile {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659692596211677736L;

	@Override
	protected AttributesDefinition getAttributesDefinition() {
		return new NaverAttributesDefinition();
	}
	
    public String getNickname() {
        return (String) getAttribute(NaverAttributesDefinition.NICKNAME);
    }
    
    public String getName() {
        return (String) getAttribute(NaverAttributesDefinition.NAME);
    }
    
    public String getEmail() {
        return (String) getAttribute(NaverAttributesDefinition.EMAIL);
    }
	
}
