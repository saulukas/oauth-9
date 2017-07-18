package oauth9.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Test client for use with social application (as an OAuth2 auth server). Remember to
 * access this app via its IP address (not "localhost"), otherwise the auth server will
 * steal your cookie.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class OAuth9ClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(OAuth9ClientApplication.class).run(args);
	}

}
