package oauth9.server.endpoints;

import static oauth9.server.config.ResourceServerConfig.URL_USER_INFO;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoint {

	@RequestMapping({ "/user", URL_USER_INFO })
	public Map<String, String> user(Principal principal) {
        printUserInfo();
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
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
