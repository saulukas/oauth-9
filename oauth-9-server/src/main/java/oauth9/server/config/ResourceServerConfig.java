package oauth9.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String URL_USER_INFO = "/me";
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher(URL_USER_INFO).authorizeRequests().anyRequest().authenticated();
    }

}
