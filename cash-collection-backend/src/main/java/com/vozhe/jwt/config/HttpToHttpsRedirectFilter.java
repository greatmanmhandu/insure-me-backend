package com.vozhe.jwt.config;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class HttpToHttpsRedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!httpRequest.isSecure()) {
            String redirectUrl = "https://" + httpRequest.getServerName() + httpRequest.getRequestURI();
            httpResponse.sendRedirect(redirectUrl);
        } else {
            chain.doFilter(request, response);
        }
    }

    // Other methods of the Filter interface (init, destroy) can be implemented as needed
}