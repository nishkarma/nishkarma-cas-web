package org.nishkarma;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.nishkarma.cas.adaptors.jdbc.User;
import org.nishkarma.cas.adaptors.jdbc.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private ClassPathXmlApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:datasource-config.xml");
	}

	@Test
	public void test() {

		DataSource dataSource = (DataSource) applicationContext
				.getBean("dataSource");

		String querySQL = "select username as username, password as password, last_login_ip as last_login_ip, ip_protect_yn as ip_protect_yn from users where username=? and active_yn='Y'";

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String username = "freelsj";

			User user = jdbcTemplate.queryForObject(querySQL,
					new Object[] { username }, new UserRowMapper());

			logger.debug("---user.getUserName()=" + user.getUserName());
			logger.debug("---user.getPassword()=" + user.getPassword());
			logger.debug("---user.getLastLoginIp()="
					+ user.getLastLoginIp());
			logger.debug("---user.isIpProtect()=" + user.isIpProtect());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void updateTest() {

		DataSource dataSource = (DataSource) applicationContext
				.getBean("dataSource-lsj");

		String updateSQL = "update users set last_login_ip = ?,  ip_protect_yn = ? where username=? and active_yn='Y'";
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String username = "lsj";
			String ip_protect_yn = "Y";
			String last_login_ip = "127.0.0.1";

			int cnt = jdbcTemplate.update(updateSQL, new Object[] { last_login_ip, ip_protect_yn, username });

			logger.debug("---cnt=" + cnt);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
