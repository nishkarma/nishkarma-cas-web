package org.nishkarma.cas.adaptors.jdbc;

public class User {

	private String userName;
	private String password;
	private String lastLoginIp;
	private boolean ipProtect;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public boolean isIpProtect() {
		return ipProtect;
	}
	public void setIpProtect(boolean ipProtect) {
		this.ipProtect = ipProtect;
	}

}
