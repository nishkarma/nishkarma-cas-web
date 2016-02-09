##Functions
	-. Authentication Handlers
       Database
       OAuth 1.0/2.0, OpenID
	-. Long Term Authentication
       Remember Me
	-. IP Protect     
	-. HA
  
##Customizing Method
	org.jasig.cas's cas-server-webapp 4.1.4 maven overlay
	reference : https://wiki.jasig.org/display/CASUM/Best+Practice+-+Setting+Up+CAS+Locally+using+the+Maven+WAR+Overlay+Method

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
	
	
