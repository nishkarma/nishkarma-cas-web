package org.nishkarma;

import org.jasig.cas.authentication.handler.DefaultPasswordEncoder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncoderTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testDefaultPasswordEncoder() {
		DefaultPasswordEncoder defaultPasswordEncoder = new DefaultPasswordEncoder("MD5");
		defaultPasswordEncoder.setCharacterEncoding("UTF-8");
		
		String password = "";
		String enPassword = "";
		
		password = "1234";
		enPassword = defaultPasswordEncoder.encode(password);
		logger.debug("password-"+password+",--enPassword="+enPassword);

		password = "12345";
		enPassword = defaultPasswordEncoder.encode(password);
		logger.debug("password-"+password+",--enPassword="+enPassword);
		
	}
}
