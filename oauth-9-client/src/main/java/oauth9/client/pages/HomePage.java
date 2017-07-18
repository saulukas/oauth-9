package oauth9.client.pages;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {

	@RequestMapping("/")
	public String renderHtml(Principal user) {
		return "Hello " + user.getName() + "!" + printUserInfo();
	}

    private String printUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String info = " hasAuthentication=" + (authentication != null);
        if (authentication != null) {
            info += ", user=" + authentication.getName();
            info += ", authenticated?=" + authentication.isAuthenticated();
            info += ", authorities=" + authentication.getAuthorities();
        }
        return info;
    }
}
