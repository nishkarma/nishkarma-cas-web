package org.nishkarma.oauth.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.OAuth20Profile;

public class KakaoProfile extends OAuth20Profile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8263003717385612778L;

	/**
	 * 
	 */

	@Override
	protected AttributesDefinition getAttributesDefinition() {
		return new KakaoAttributesDefinition();
	}
	
    public String getNickname() {
        return (String) getAttribute(KakaoAttributesDefinition.NICKNAME);
    }
    
}
