package oauth9.client.config;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableOAuth2Client
public class OAuthClientConfig extends WebSecurityConfigurerAdapter {

    private static final String OAUTH2_CLIENT_FILTER_URL = "/login";

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated();
        //http.anonymous().disable();
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(
                OAUTH2_CLIENT_FILTER_URL));
        http.logout().logoutUrl("/logout");
        http.addFilterBefore(oauthFilter(), BasicAuthenticationFilter.class);
    }

    private Filter oauthFilter() {
        UserInfoTokenServices userInfoService = new UserInfoTokenServices(
                "http://localhost:8080/me", authorizationResource().getClientId());
        userInfoService.setRestTemplate(new OAuth2RestTemplate(
                authorizationResource(), oauth2ClientContext));

        OAuth2RestTemplate accessTokenService = new OAuth2RestTemplate(
                authorizationResource(), oauth2ClientContext);

        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
                OAUTH2_CLIENT_FILTER_URL);
        filter.setRestTemplate(accessTokenService);
        filter.setTokenServices(userInfoService);
        return filter;
    }

    private AuthorizationCodeResourceDetails authorizationResource() {
        AuthorizationCodeResourceDetails authConfig = new AuthorizationCodeResourceDetails();
        authConfig.setClientId("acme");
        authConfig.setClientSecret("acmesecret");
        authConfig.setUserAuthorizationUri("http://localhost:8080/oauth/authorize");
        authConfig.setAccessTokenUri("http://localhost:8080/oauth/token");
        authConfig.setAuthenticationScheme(AuthenticationScheme.query);
        return authConfig;
    }

}
