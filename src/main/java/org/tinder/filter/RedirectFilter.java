package org.tinder.filter;

import org.tinder.utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectFilter implements Filter {
    private final String WHITE_LIST_URI = "(/login|/register|/templates/*|/favicon.ico)";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!request.getServletPath().matches(WHITE_LIST_URI) && CookieUtil.getValue(request) == null) {
            response.sendRedirect("/login");
        } else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
