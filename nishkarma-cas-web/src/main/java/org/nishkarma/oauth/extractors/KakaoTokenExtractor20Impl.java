package org.nishkarma.oauth.extractors;

import org.pac4j.oauth.profile.JsonHelper;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class KakaoTokenExtractor20Impl implements AccessTokenExtractor {
	
	private Logger logger = LoggerFactory.getLogger(KakaoTokenExtractor20Impl.class);

	private static final String EMPTY_SECRET = "";

	/**
	 * {@inheritDoc}
	 */
	public Token extract(String response) {
		
		logger.debug("1---response="+response);
		
		final JsonNode json = JsonHelper.getFirstNode(response);
		String token = (String) JsonHelper.get(json, "access_token");
		
		logger.debug("2---access_token="+JsonHelper.get(json, "access_token"));
		logger.debug("3---token="+token);
		
		return new Token(token, EMPTY_SECRET, response);
		
	}
}
