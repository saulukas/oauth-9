package oauth9.server.pages.home;

import static java.util.Arrays.asList;
import static oauth9.utils.Dto.equal;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class OAtuh9AuthenticationProvider implements AuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        if (equal(token.getName(), "rimas")  && equal(token.getCredentials(), "pimas")) {
            return new UsernamePasswordAuthenticationToken(
                    token.getName(),
                    token.getCredentials(),
                    asList(authority("broker"), authority("partner"))
                  );
        }
        if (equal(token.getName(), "ona")  && equal(token.getCredentials(), "pona")) {
            return new UsernamePasswordAuthenticationToken(
                    token.getName(),
                    token.getCredentials(),
                    asList(authority("sales"))
                  );
        }
        throw new BadCredentialsException("Invalid credentials.");
    }

    @Override
    public boolean supports(Class<?> klass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(klass);
    }

    static GrantedAuthority authority(String authority) {
        return new SimpleGrantedAuthority(authority);
    }
    
}
