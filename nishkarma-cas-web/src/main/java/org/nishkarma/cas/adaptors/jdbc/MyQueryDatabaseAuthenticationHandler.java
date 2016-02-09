package org.nishkarma.cas.adaptors.jdbc;

import java.security.GeneralSecurityException;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.nishkarma.cas.utils.CIDRUtils;
import org.nishkarma.cas.utils.MyClientInfo;
import org.nishkarma.cas.utils.MyClientInfoHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import net.sf.uadetector.ReadableDeviceCategory;
import net.sf.uadetector.ReadableDeviceCategory.Category;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class MyQueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	@NotNull
	private String sql;

	@NotNull
	private String updateSql;

	@NotNull
	private String updateNotProtectedIPSql;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
			throws GeneralSecurityException, PreventedException {

		final String username = credential.getUsername();
		MyClientInfo myClientInfo = MyClientInfoHolder.getClientInfo();

		String loginIp = "", ipProtectYn = "", userAgent = "";

		loginIp = myClientInfo.getClientIpAddress();
		ipProtectYn = myClientInfo.isPublicWorkstation() ? "Y" : "N";
		userAgent = myClientInfo.getClientUserAgent();

		logger.debug(
				"---MyQueryDatabaseAuthenticationHandler -- authenticateUsernamePasswordInternal-username={},loginIp={},ipProtectYn={},userAgent={}",
				username, loginIp, ipProtectYn, userAgent);

		try {
			final User user = getJdbcTemplate().queryForObject(this.sql, new Object[] { username },
					new UserRowMapper());
			/*
			 * logger.debug("---user.getUserName()=" + user.getUserName());
			 * logger.debug("---user.getPassword()=" + user.getPassword());
			 * logger.debug("---user.getLastLoginIp()=" +
			 * user.getLastLoginIp()); logger.debug("---user.isIpProtect()=" +
			 * user.isIpProtect());
			 */
			// TODO password encoder support
			final String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword());

			if (!user.getPassword().equals(encryptedPassword)) {
				throw new FailedLoginException("Password does not match value on record.");
			}

			boolean userUpdated = false;

			if (user.isIpProtect()) {

				if (isIpRange(userAgent, loginIp, user.getLastLoginIp()) == false) {

					logger.debug("---InvalidLogin IP!!!");

					myClientInfo.setProtectPublicWorkstation(true);

					updateNotProtectedUser(username, loginIp);
					userUpdated = true;
				}
			}

			if (userUpdated == false) {
				updateUser(username, loginIp, ipProtectYn);
			}

		} catch (final IncorrectResultSizeDataAccessException e) {
			if (e.getActualSize() == 0) {
				throw new AccountNotFoundException(username + " not found with SQL query");
			} else {
				throw new FailedLoginException("Multiple records found for " + username);
			}
		} catch (final DataAccessException e) {
			throw new PreventedException("SQL exception while executing query for " + username, e);
		}

		logger.debug("---MyQueryDatabaseAuthenticationHandler- username={} OK!", username);

		return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
	}

	private void updateUser(String username, String loginIp, String ipProtectYn) {

		try {
			getJdbcTemplate().update(this.updateSql, new Object[] { loginIp, ipProtectYn, username });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void updateNotProtectedUser(String username, String loginIp) {

		try {
			getJdbcTemplate().update(this.updateNotProtectedIPSql, new Object[] { loginIp, username });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private boolean isIpRange(String userAgent, String loginIp, String dbLoginIp) {

		boolean isInRange = false;

		try {
			if (dbLoginIp == null)
				return true;
			if (isMobile(userAgent)) {
				return true;
			}

			// Single End-user LAN : IPv4 - /24, IPv6 - /64
			// Single end-points and loopback : IPv4 - /32, IPv6 - /128

			// https://en.wikipedia.org/wiki/IPv4_subnetting_reference
			// https://en.wikipedia.org/wiki/IPv6_subnetting_reference

			// C-Class
			int subnetMask = 24;
			if (CIDRUtils.isIpV6(loginIp))
				subnetMask = 64;

			CIDRUtils cIDRUtils = new CIDRUtils(loginIp, subnetMask);
			isInRange = cIDRUtils.isInRange(dbLoginIp);
		} catch (Exception e) {
			logger.error(e.getMessage());
			isInRange = true;
		}

		return isInRange;
	}

	private boolean isMobile(String userAgent) {
		boolean rtnIsMobile = false;

		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

		ReadableUserAgent agent = parser.parse(userAgent);
		ReadableDeviceCategory readableDeviceCategory = agent.getDeviceCategory();

		/*
		 * logger.debug("----------agent.getName():{}", agent.getName());
		 * logger.debug("----------agent.getTypeName():{}",
		 * agent.getTypeName());
		 * logger.debug("----------agent.getOperatingSystem().getName():{}",
		 * agent .getOperatingSystem().getName()); logger.debug(
		 * "----------agent.getOperatingSystem().getFamilyName():{}" ,
		 * agent.getOperatingSystem().getFamilyName());
		 * 
		 * 
		 * logger.debug("----------readableDeviceCategory.getName():{}",
		 * readableDeviceCategory.getName()); logger.debug(
		 * "----------readableDeviceCategory.getCategory().getName():{}",
		 * readableDeviceCategory.getCategory().getName());
		 */

		if (readableDeviceCategory.getCategory() == Category.PERSONAL_COMPUTER) {
			//
			rtnIsMobile = false;
		} else {
			rtnIsMobile = true;
		}

		return rtnIsMobile;
	}

	/**
	 * @param sql
	 *            The sql to set.
	 */
	public void setSql(final String sql) {
		this.sql = sql;
	}

	public void setUpdateSql(final String updateSql) {
		this.updateSql = updateSql;
	}

	public void setUpdateNotProtectedIPSql(final String updateNotProtectedIPSql) {
		this.updateNotProtectedIPSql = updateNotProtectedIPSql;
	}

}
