package lk.ijse.webPos.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.webPos.config.Configure;

import java.io.IOException;

/**
 * @author : savindaJ
 * @date : 1/8/2024
 * @since : 0.1.0
 **/
@WebFilter(urlPatterns = "/*")
public class DefaultFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Configure.getInstance().getSession();
        System.out.println("Init Filter !");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setContentType("application/json");
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed Filter !");
    }
}
