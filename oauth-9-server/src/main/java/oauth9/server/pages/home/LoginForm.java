package oauth9.server.pages.home;

import javax.servlet.http.HttpServletRequest;
import oauth9.server.config.WebSecurityConfig;
import oauth9.utils.Dto;
import static oauth9.utils.SecurityUtils.htmlOfCsrfHiddenInput;
import oauth9.utils.mustache.MustacheElement;
import oauth9.utils.mustache.MustacheView;

public class LoginForm extends MustacheView {

    MustacheElement render(HttpServletRequest request, String user, String password) {
        Model model = new Model();
        model.urlFormLogin = WebSecurityConfig.URL_LOGIN_FORM_FILTER;
        model.htmlOfCsrfHiddenInput = htmlOfCsrfHiddenInput(request);
        model.username = user;
        model.usernameDomId = nextDomId();
        model.password = password;
        model.passwordDomId = nextDomId();
        return elementWith(model);
    }

    static class Model extends Dto {
        String urlFormLogin;
        String htmlOfCsrfHiddenInput;
        String username;
        String usernameDomId;
        String password;
        String passwordDomId;
    }
}
