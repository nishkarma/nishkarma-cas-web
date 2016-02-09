package org.nishkarma.oauth.builder.api;

import org.nishkarma.oauth.extractors.KakaoTokenExtractor20Impl;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

public class KakaoApi20 extends DefaultApi20 {

	// /oauth/authorize?client_id={app_key}&redirect_uri={redirect_uri}&response_type=code
	private static final String AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

	public String getAccessTokenEndpoint() {
		return "https://kauth.kakao.com/oauth/token";
	}

	public String getAuthorizationUrl(OAuthConfig config) {

		String url = String.format(AUTHORIZATION_URL, config.getApiKey(),
				OAuthEncoder.encode(config.getCallback()));

		return url;
	}

	/*
	 * Returns the verb for the access token endpoint (defaults to GET)
	 * 
	 * @return access token endpoint verb
	 */
	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

	@Override
	public AccessTokenExtractor getAccessTokenExtractor() {
		return new KakaoTokenExtractor20Impl();
	}
}
