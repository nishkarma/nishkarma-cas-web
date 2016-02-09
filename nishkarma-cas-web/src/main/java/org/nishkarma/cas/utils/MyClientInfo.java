package org.nishkarma.cas.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nishkarma.cas.web.flow.MyAuthenticationViaFormAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClientInfo {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String alternateLocation = "X-Forwarded-For";

	/** IP Address of the server (local). */
	private final String serverIpAddress;

	/** IP Address of the client (Remote) */
	private final String clientIpAddress;

	/** User-Agent */
	private final String clientUserAgent;

	private final boolean publicWorkstation;
	
	private boolean protectPublicWorkstation = false;

	public MyClientInfo(final HttpServletRequest request) {

		this(
				request.getLocalAddr(),
				request.getHeader(alternateLocation) != null ? request
						.getHeader(alternateLocation) : request.getRemoteAddr(),
				request.getHeader("User-Agent"),
				StringUtils
						.isNotBlank(request
								.getParameter(MyAuthenticationViaFormAction.PUBLIC_WORKSTATION_ATTRIBUTE)));
		
		logger.debug("---request.getRequestURI()={}", request.getRequestURI());
	}

	public MyClientInfo(final String serverIpAddress,
			final String clientIpAddress, final String clientUserAgent,
			final boolean publicWorkstation) {

		logger.debug("---publicWorkstation={}", publicWorkstation);
		
		this.serverIpAddress = serverIpAddress == null ? "unknown"
				: serverIpAddress;
		this.clientIpAddress = clientIpAddress == null ? "unknown"
				: clientIpAddress;
		this.clientUserAgent = clientUserAgent == null ? "unknown"
				: clientUserAgent;
		this.publicWorkstation = publicWorkstation;
	}

	public String getServerIpAddress() {
		return this.serverIpAddress;
	}

	public String getClientIpAddress() {
		return this.clientIpAddress;
	}

	public String getClientUserAgent() {
		return clientUserAgent;
	}

	public boolean isPublicWorkstation() {
		return publicWorkstation;
	}

	public boolean isProtectPublicWorkstation() {
		return protectPublicWorkstation;
	}
	
	public void setProtectPublicWorkstation(boolean protectPublicWorkstation) {
		this.protectPublicWorkstation = protectPublicWorkstation;
	}	
}
