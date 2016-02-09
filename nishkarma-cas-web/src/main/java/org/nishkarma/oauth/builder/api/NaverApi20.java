package org.nishkarma.oauth.builder.api;

import org.nishkarma.oauth.extractors.NaverTokenExtractor20Impl;
import org.scribe.builder.api.StateApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

public class NaverApi20 extends StateApi20 {

	// "https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트 아이디}&response_type=code&redirect_uri={개발자 센터에 등록한 콜백 URL(URL 인코딩)}&state={상태 토큰}"
	private static final String AUTHORIZATION_URL = "https://nid.naver.com/oauth2.0/authorize?client_id=%s&response_type=code&redirect_uri=%s&state=%s";

	public String getAccessTokenEndpoint() {
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	public String getAuthorizationUrl(OAuthConfig config, String state) {
		
		String url = String.format(AUTHORIZATION_URL, config.getApiKey(),
				OAuthEncoder.encode(config.getCallback()),  OAuthEncoder.encode(state));
		
		return url;
	}

	 /**
	   * Returns the access token extractor.
	   * 
	   * @return access token extractor
	   */
	  public AccessTokenExtractor getAccessTokenExtractor()
	  {
	    return new NaverTokenExtractor20Impl();
	  }
		
	  /**
	   * Returns the verb for the access token endpoint (defaults to GET)
	   * 
	   * @return access token endpoint verb
	   */
	  public Verb getAccessTokenVerb()
	  {
	    return Verb.GET;
	  }

	
}
