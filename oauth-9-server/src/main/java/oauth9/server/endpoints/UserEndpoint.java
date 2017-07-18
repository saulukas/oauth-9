package oauth9.server.endpoints;

import java.security.Principal;
import java.util.List;
import oauth9.utils.Dto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyList;
import static oauth9.server.config.ResourceServerConfig.URL_USER_INFO;
import static oauth9.utils.StreamUtils.mapToList;

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
        user.authorities = mapToList(authentication.getAuthorities(), e -> e.getAuthority());
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
