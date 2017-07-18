package oauth9.server.endpoints;

import java.security.Principal;
import java.util.List;
import oauth9.utils.Dto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static oauth9.server.config.ResourceServerConfig.URL_USER_INFO;

@RestController
public class UserEndpoint {

    public static class UserInfo extends Dto {
        public String username;
        public List<String> authorities = emptyList();
    }

    @RequestMapping({"/user", URL_USER_INFO})
    public UserInfo user(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo user = new UserInfo();
        user.username = authentication.getName();
        user.authorities = authentication.getAuthorities().stream()
                .map(e -> e.getAuthority())
                .collect(toList());
        return user;
    }

    private void printUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication=" + authentication);
        if (authentication != null) {
            System.out.println("user=" + authentication.getName());
            System.out.println("authenticated?=" + authentication.isAuthenticated());
            System.out.println("authorities=" + authentication.getAuthorities());
        }
    }

}
