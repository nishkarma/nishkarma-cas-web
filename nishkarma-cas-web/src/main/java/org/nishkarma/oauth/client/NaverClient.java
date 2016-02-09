package org.nishkarma.oauth.client;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.nishkarma.oauth.builder.api.NaverApi20;
import org.nishkarma.oauth.profile.NaverAttributesDefinition;
import org.nishkarma.oauth.profile.NaverProfile;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.client.exception.OAuthCredentialsException;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.StateOAuth20ServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NaverClient extends BaseOAuth20Client<NaverProfile> {

	public NaverClient() {
	}

	public NaverClient(final String key, final String secret) {
		setKey(key);
		setSecret(secret);
	}

	@Override
	protected NaverClient newClient() {
		final NaverClient newClient = new NaverClient();
		return newClient;
	}

	protected void internalInit() {
		super.internalInit();
		super.setTokenAsHeader(true);
		this.service = new StateOAuth20ServiceImpl(new NaverApi20(),
				new OAuthConfig(this.key, this.secret, this.callbackUrl,
						SignatureType.Header, null, null), this.connectTimeout,
				this.readTimeout, this.proxyHost, this.proxyPort);

	}

	protected boolean requiresStateParameter() {
		return true;
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
		return "https://apis.naver.com/nidlogin/nid/getUserProfile.xml";
	}

	@Override
	protected NaverProfile extractUserProfile(String body) {
		logger.debug("naver response = " + body);

		NaverProfile profile = new NaverProfile();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(
					new ByteArrayInputStream(body.getBytes("UTF-8"))));
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("response");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					profile.setId(eElement.getElementsByTagName("id").item(0)
							.getTextContent());

					profile.addAttribute(NaverAttributesDefinition.EMAIL,
							eElement.getElementsByTagName("email").item(0)
									.getTextContent());

					profile.addAttribute(NaverAttributesDefinition.NAME,
							eElement.getElementsByTagName("name").item(0)
									.getTextContent());

					profile.addAttribute(NaverAttributesDefinition.NICKNAME,
							eElement.getElementsByTagName("nickname").item(0)
									.getTextContent());

					logger.debug("age : {}",
							eElement.getElementsByTagName("age").item(0)
									.getTextContent());
					logger.debug("gender : {}",
							eElement.getElementsByTagName("gender").item(0)
									.getTextContent());
					logger.debug("birthday : {}", eElement
							.getElementsByTagName("birthday").item(0)
							.getTextContent());
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return profile;
	}

/*	@Override
	protected NaverProfile retrieveUserProfileFromToken(final Token accessToken) {
		logger.debug("retrieveUserProfileFromToken--1.accessToken : {}",
				accessToken.getToken());

		final String body = sendRequestForData(accessToken,
				getProfileUrl(accessToken));
		if (body == null) {
			throw new HttpCommunicationException(
					"Not data found for accessToken : " + accessToken);
		}
		final NaverProfile profile = extractUserProfile(body);
		addAccessTokenToProfile(profile, accessToken);
		return profile;
	}*/
	/*
	 * @Override protected String sendRequestForData(final Token accessToken,
	 * final String dataUrl) {
	 * logger.debug("sendRequestForData--1.accessToken : {} / dataUrl : {}",
	 * accessToken.getToken(), dataUrl); final long t0 =
	 * System.currentTimeMillis(); final ProxyOAuthRequest request =
	 * createProxyRequest(dataUrl); this.service.signRequest(accessToken,
	 * request); // Let the client to decide if the token should be in header if
	 * (this.isTokenAsHeader()) {
	 * 
	 * logger.debug("----addHeader - Authorization :{}", "Bearer " +
	 * accessToken.getToken());
	 * 
	 * request.addHeader("Authorization", "Bearer " + accessToken.getToken());
	 * 
	 * }
	 * 
	 * final Response response = request.send(); final int code =
	 * response.getCode(); final String body = response.getBody();
	 * logger.debug("----body : {} ", body);
	 * 
	 * final long t1 = System.currentTimeMillis();
	 * logger.debug("Request took : " + (t1 - t0) + " ms for : " + dataUrl);
	 * logger.debug("response code : {} / response body : {}", code, body); if
	 * (code != 200) { logger.error("Failed to get data, code : " + code +
	 * " / body : " + body); throw new HttpCommunicationException(code, body); }
	 * return body; }
	 */
}
