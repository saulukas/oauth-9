package oauth9.server.pages.home;

import javax.servlet.http.HttpServletRequest;
import static oauth9.server.config.WebSecurityConfig.URL_LOGIN_PAGE;
import org.springframework.web.bind.annotation.RestController;
import oauth9.utils.Dto;
import oauth9.utils.mustache.MustacheView;
import oauth9.utils.HtmlRequest;
import static oauth9.utils.SecurityUtils.htmlOfCsrfHiddenInput;
import static oauth9.utils.SecurityUtils.isAuthenticated;
import static oauth9.utils.SecurityUtils.securityContext;
import oauth9.utils.mustache.MustacheElement;
import org.springframework.security.core.Authentication;

@RestController
public class HomePage extends MustacheView {

    LoginForm loginForm = new LoginForm();
    
    @HtmlRequest(URL_LOGIN_PAGE)
    String renderHtml(HttpServletRequest request) {
        return render(modelFor(request));
    }

    static class Model extends Dto {
        String htmlOfCsrfHiddenInput;
        boolean isAuthenticated;
        String username;
        String authorities;
        MustacheElement loginFormRimas;
        MustacheElement loginFormOna;
    }

    Model modelFor(HttpServletRequest request) {
        Authentication authentication = securityContext().getAuthentication();

        Model model = new Model();
        model.htmlOfCsrfHiddenInput = htmlOfCsrfHiddenInput(request);
        model.isAuthenticated = isAuthenticated(authentication);
        if (model.isAuthenticated) {
            model.authorities = authentication.getAuthorities().toString();
            model.username = authentication.getName();
        }
        model.loginFormRimas = loginForm.render(request, "rimas", "pimas");
        model.loginFormOna = loginForm.render(request, "ona", "pona");
        return model;
    }

}
