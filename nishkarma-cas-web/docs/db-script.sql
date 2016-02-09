--users
CREATE TABLE USERS (
	USERNAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	ACTIVE_YN CHAR(1) NOT NULL,
	LAST_LOGIN_IP VARCHAR(50),
	IP_PROTECT_YN CHAR(1),
	LAST_NOT_PROTECTED_LOGIN_IP VARCHAR(50),
	LOGIN_DATE TIMESTAMP(6),
	LAST_NOT_PROTECTED_LOGIN_DATE TIMESTAMP(6)
);

ALTER TABLE USERS ADD CONSTRAINT PK_USERS
PRIMARY KEY (
	USERNAME
);

ALTER TABLE USERS ADD 
CHECK (USERNAME IS NOT NULL);

ALTER TABLE USERS ADD 
CHECK (PASSWORD IS NOT NULL);

ALTER TABLE USERS ADD 
CHECK (ACTIVE_YN IS NOT NULL);


--user roles
CREATE TABLE USER_ROLES (
	USERNAME VARCHAR(50) NOT NULL,
	ROLENAME VARCHAR(50) NOT NULL
);

ALTER TABLE USER_ROLES ADD CONSTRAINT PK_USER_ROLES
PRIMARY KEY (
	USERNAME, ROLENAME
);



--audit trail
CREATE TABLE COM_AUDIT_TRAIL
(
 AUD_USER      VARCHAR(100) NOT NULL,
 AUD_CLIENT_IP VARCHAR(15)   NOT NULL,
 AUD_SERVER_IP VARCHAR(15)   NOT NULL,
 AUD_RESOURCE  VARCHAR(100) NOT NULL,
 AUD_ACTION    VARCHAR(100) NOT NULL,
 APPLIC_CD     VARCHAR(5)   NOT NULL,
 AUD_DATE      TIMESTAMP     NOT NULL
);

/*
		ref : src/test/java/org/nishkarma/PasswordEncoderTests.java

		DefaultPasswordEncoder defaultPasswordEncoder = new DefaultPasswordEncoder("MD5");
		defaultPasswordEncoder.setCharacterEncoding("UTF-8");
		enPassword = defaultPasswordEncoder.encode("1234"); - result : 81dc9bdb52d04dc20036dbd8313ed055
*/

INSERT INTO users(username, password, active_yn) VALUES ('freelsj', '81dc9bdb52d04dc20036dbd8313ed055', 'Y');
INSERT INTO users(username, password, active_yn) VALUES ('adminuser', '81dc9bdb52d04dc20036dbd8313ed055', 'Y');
INSERT INTO users(username, password, active_yn) VALUES ('staffuser', '81dc9bdb52d04dc20036dbd8313ed055', 'Y');

INSERT INTO user_roles(username, rolename) VALUES ('freelsj', 'admin');
INSERT INTO user_roles(username, rolename) VALUES ('freelsj', 'staff');
INSERT INTO user_roles(username, rolename) VALUES ('adminuser', 'admin');
INSERT INTO user_roles(username, rolename) VALUES ('staffuser', 'staff');
