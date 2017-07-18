package oauth9.client.endpoints;

import java.io.Serializable;
import static java.util.Collections.emptyList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static oauth9.utils.SecurityUtils.isAuthenticated;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoEndpoint {

    public static class UserInfo implements Serializable {
        public String username = "";
        public List<String> permissions = emptyList();
    }

    @GetMapping("/user-info")
    public UserInfo getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isAuthenticated(authentication)) {
            //          throw new AccessDeniedException("permissions missing.");
            throw new InsufficientAuthenticationException("authentication is needed");
        }
        UserInfo user = new UserInfo();
        if (authentication != null && authentication.isAuthenticated()) {
            user.username = authentication.getName();
            user.permissions = authentication.getAuthorities().stream()
                    .map(e -> e.getAuthority())
                    .collect(toList());
        }
        return user;
    }
}
