package oauth9.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static boolean hasParameter(HttpServletRequest request, String name) {
        return request.getParameter(name) != null;
    }
}
