package org.nishkarma;

import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

import org.nishkarma.cas.utils.CIDRUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpRangeTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Ignore
	public void testIpV4() throws UnknownHostException {

		// https://en.wikipedia.org/wiki/IPv4_subnetting_reference
		// /32 - 255.255.255.255 	
		// /24 - 255.255.255.0
		CIDRUtils utils = new CIDRUtils("192.168.1.77", 24);

		logger.debug("--testIpV4--");
		
		logger.debug("getNetworkAddress: " + utils.getNetworkAddress());
		logger.debug("getBroadcastAddress: " + utils.getBroadcastAddress());
		logger.debug("192.168.1.68 is in range? :" + utils.isInRange( "192.168.1.68" ));		

/*		
		logger.debug("192.168.1.254 is in range? :" + utils.isInRange( "192.168.1.254" ));		
		logger.debug("192.168.1.1 is in range? :" + utils.isInRange( "192.168.1.1" ));		
		logger.debug("192.168.1.254 is in range? :" + utils.isInRange( "192.168.1.254" ));		
		logger.debug("175.223.23.96 is in range? :" + utils.isInRange( "175.223.23.96" ));		
*/		
	}

	@Test
	public void testIpV6() throws UnknownHostException {

		logger.debug("CIDRUtils.isIpV6(0:0:0:0:0:ffff:c0a8:133)=" + CIDRUtils.isIpV6("0:0:0:0:0:ffff:c0a8:133"));
		
		CIDRUtils utils = new CIDRUtils("0:0:0:0:0:ffff:c0a8:133", 64); //24

		logger.debug("--testIpV6--");
		
		logger.debug("getNetworkAddress: " + utils.getNetworkAddress());
		logger.debug("getBroadcastAddress: " + utils.getBroadcastAddress());
		logger.debug("192.168.1.51 is in range? :" + utils.isInRange( "192.168.1.51" ));		
		logger.debug("192.168.1.1 is in range? :" + utils.isInRange( "192.168.1.1" ));		
		logger.debug("192.168.1.254 is in range? :" + utils.isInRange( "192.168.1.254" ));		
		
	}
}
