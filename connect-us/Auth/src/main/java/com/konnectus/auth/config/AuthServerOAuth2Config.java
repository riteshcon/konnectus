package com.konnectus.auth.config;

/**
 * Server issuing access tokens to client after successfully authentication the resource owner and obtaining authorization.
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ServerSecurityConfig.class)
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
	static final String CLIEN_ID = "kon";
	static final String CLIENT_SECRET = "{noop}kon";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	private static final String RESOURCE_ID = "resource_id";
	
	 @Resource(name = "userService")
	    private UserDetailsService userDetailsService;

	/*
	 * a configurer that defines the client details service. Client details can be
	 * initialized, or you can just refer to an existing store.
	 * what is client id and secret
	 * 
	 * Memory as we want to store the client details in memory (others can be jdbc i.e. configurer.jdbc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
	 */
/*	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer.inMemory().withClient(CLIEN_ID).secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.resourceIds("konnectus")
				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}*/

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain)
				.authenticationManager(authenticationManager);
		 endpoints.userDetailsService(userDetailsService);
		/* endpoints.userDetailsService(userDetailsService);
		 endpoints.setClientDetailsService(clientDetailsService());*/
	}
	
	@Bean
	 public ClientDetailsService clientDetailsService() {
         return new ClientDetailsService() {
             @Override
             public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
                 BaseClientDetails details = new BaseClientDetails();
                 details.setClientId("kon");
                 details.setClientSecret("kon");
                 details.setAuthorizedGrantTypes(Arrays.asList(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT) );
                 details.setScope(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST));
                 details.setResourceIds(Arrays.asList(RESOURCE_ID));
                 Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                 authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
                 details.setAuthorities(authorities);
                 return details;
             }
         };
     }  //*/


}
