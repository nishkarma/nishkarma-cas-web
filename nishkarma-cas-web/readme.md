#Nishkarma-cas-web
This is a Single Sign-On Server with Jasig CAS(https://jasig.github.io/cas/4.1.x/index.html).

It is customized with org.jasig.cas's cas-server-webapp 4.1.4 maven overlay method.

reference : https://wiki.jasig.org/display/CASUM/Best+Practice+-+Setting+Up+CAS+Locally+using+the+Maven+WAR+Overlay+Method

##Functions
	-. Authentication Handlers
       Database
       OAuth 1.0/2.0, OpenID
	-. Long Term Authentication
       Remember Me
	-. IP Protect     
	-. HA

###Steps:

1) In the command line
```
git clone https://github.com/nishkarma/nishkarma-cas-web.git
```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven projects
```

3) Initialize DB
```
This sample is based on PostgreSQL. You can customize it in following files.

1. customize the scripts in /docs/db-script.sql and run the scripts.
2. customize the /src/main/resources/datasource-config.xml.
3. customize the query statements in /src/main/webapp/WEB-INF/deployerConfigContext.xml.
```

4) Preparing the Self-Sign SSL certificate key.
```
This sample need three domains.
cas server  : cas.example.org
cas client1 : local.example.org
cas client2 : local.example-other.org

At first insert the following line in hosts file.
127.0.0.1 cas.example.org local.example.org local.example-other.org

The SSO connections should be https connections. So it needs SSL certificate key.
Make SSL certificate keys. 
How to make keys are described in /docs/test-ssl/self-sign.txt(java keytool method) or /openssl/makeCerts(openssl method).
```
 
5) Running
```
There's tomcat server.xml sample in /docs/tomcat/server.xml and HA configuration whth Apache http server in /docs/HA.

run tomcat server, 
and connect browser to http://cas.example.org:8443/cas/login with ID/PASSWD as described by insert users statements in /docs/db-script.sql.
The default password is 1234.

If you want the logout connect to http://cas.example.org:8443/cas/logout.

You can test the Social login like facebook and google+...
```

##Configuration Reference
	http://jasig.github.io/cas/4.1.x/index.html
	https://wiki.jasig.org/display/CASUM/Home
 	
##OAuth
	reference : https://github.com/leleuj/pac4j

	
	FaceBook
	
		https://developers.facebook.com/apps/
		Add a New App
		
		STEP 1:
		In Settings - > Basic -> Contact Email .(Give your/any email)
		
		STEP 2: in 'Status and Review' Tab : change
		Do you want to make this app and all its live features available to the general public?
		to 'Yes'.
		
		Graph API Explorer
		https://developers.facebook.com/tools/explorer/145634995501895/	

	Twitter
		https://apps.twitter.com
		Create New App
		
	Google+
		https://developers.google.com/identity/protocols/OAuth2

		pricing
			https://console.developers.google.com/project/886180990011/apiui/apiview/plus/quotas?authuser=0
		
	Kakao
		https://developers.kakao.com/docs/restapi#%EC%82%AC%EC%9A%A9%EC%9E%90-%EA%B4%80%EB%A6%AC
		
	Naver
		https://nid.naver.com/devcenter/docs.nhn?menu=Web
			
				
##JDBC - ROLE attribute
	HttpServletRequest isUserInRole( java.lang.String role )
	https://wiki.jasig.org/pages/viewpage.action?pageId=47874068
	
	org.jasig.services.persondir.support.jdbc.MultiRowJdbcPersonAttributeDao
	   org.jasig.cas.authentication.principal.UsernamePasswordCredentialsToPrincipalResolver
	
	source
	https://github.com/Jasig/person-directory			
			     
##IP Protection

	http://cs.daum.net/faq/59/8035.html?faqId=29554
	https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
	https://en.wikipedia.org/wiki/Subnetwork
	https://en.wikipedia.org/wiki/IPv6_subnetting_reference
	
	User-Agent Detector
	http://uadetector.sourceforge.net/usage.html

##HA
	http://jasig.github.io/cas/4.1.x/installation/Ehcache-Ticket-Registry.html

	Ehcache Replication Guide
	http://ehcache.org/generated/2.10.0/html/ehc-all/#page/Ehcache_Documentation_Set%2Fto-title_ehcache_replication_guide.html%23
	
	
