package oauth9.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import static oauth9.utils.Dto.equal;

public class SecurityUtils {

    public static final String ATTR_CSRF = "_csrf";
    public static final String ANONYMOUS_USER = "anonymousUser";
    
    public static SecurityContext securityContext() {
        return SecurityContextHolder.getContext();
    }

    public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null
                && authentication.isAuthenticated()
                && !equal(authentication.getPrincipal(), ANONYMOUS_USER);
    }

    public static CsrfToken csrfTokenOrNullFrom(HttpServletRequest request) {
        Object token = request.getAttribute(ATTR_CSRF);
        return (token instanceof CsrfToken) ? (CsrfToken) token : null;
    }

    public static String htmlOfCsrfHiddenInput(HttpServletRequest request) {
        CsrfToken token = csrfTokenOrNullFrom(request);
        if (token == null) {
            return "\n";
        }
        return "<input"
                + " type='hidden'"
                + " name='" + token.getParameterName() + "'"
                + " value='" + token.getToken() + "'"
                + "/>\n";
    }

}
