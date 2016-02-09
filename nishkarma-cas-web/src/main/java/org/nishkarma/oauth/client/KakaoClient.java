package org.nishkarma.oauth.client;

import org.nishkarma.oauth.builder.api.KakaoApi20;
import org.nishkarma.oauth.profile.KakaoAttributesDefinition;
import org.nishkarma.oauth.profile.KakaoProfile;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.client.exception.OAuthCredentialsException;
import org.pac4j.oauth.profile.JsonHelper;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;

import com.fasterxml.jackson.databind.JsonNode;

public class KakaoClient extends BaseOAuth20Client<KakaoProfile> {

	public KakaoClient() {
	}

	public KakaoClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected KakaoClient newClient() {
		final KakaoClient newClient = new KakaoClient();
		return newClient;
	}

	protected void internalInit() {
		super.internalInit();
		super.setTokenAsHeader(true);
		
		this.service = new KakaoProxyOAuth20ServiceImpl(new KakaoApi20(), new OAuthConfig(this.key, this.secret,
                this.callbackUrl,
                SignatureType.Header,
                null, null),
                this.connectTimeout, this.readTimeout, this.proxyHost,
                this.proxyPort, false, true);
		
	}


	@Override
	protected boolean hasBeenCancelled(WebContext context) {
		final String error = context
				.getRequestParameter(OAuthCredentialsException.ERROR);
		final String errorReason = context
				.getRequestParameter(OAuthCredentialsException.ERROR_REASON);
		// user has denied permissions
		if ("access_denied".equals(error) && "user_denied".equals(errorReason)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected String getProfileUrl(final Token accessToken) {
		return "https://kapi.kakao.com/v1/user/me";
	}

	@Override
	protected KakaoProfile extractUserProfile(String body) {
		logger.debug("kakao response = " + body);

		KakaoProfile profile = new KakaoProfile();

		JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            JsonNode propertiesJson = (JsonNode)JsonHelper.get(json, "properties");
            profile.addAttribute(KakaoAttributesDefinition.NICKNAME, JsonHelper.get(propertiesJson, KakaoAttributesDefinition.NICKNAME));
        }

		return profile;
	}

	protected boolean requiresStateParameter() {
		return false;
	}

}
