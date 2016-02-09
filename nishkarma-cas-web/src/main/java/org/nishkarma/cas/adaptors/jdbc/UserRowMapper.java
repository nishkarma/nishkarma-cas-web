package org.nishkarma.cas.adaptors.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		user.setUserName(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setLastLoginIp(rs.getString("LAST_LOGIN_IP"));
		
		String ipProtectYn = rs.getString("IP_PROTECT_YN");
		
		if (ipProtectYn == null || "N".equals(ipProtectYn)) {
			user.setIpProtect(false);
		}
		else{
			user.setIpProtect(true);
		}

		return user;
	}

}
