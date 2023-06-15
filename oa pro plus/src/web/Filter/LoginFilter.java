package web.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //先转换
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取登录状态
        HttpSession session = request.getSession(false);
        //获取请求路径
        String servletPath = request.getServletPath();
        //这些界面不能被拦截
        if ("/login.jsp".equals(servletPath) || "/welcome".equals(servletPath)
                || "/user/login".equals(servletPath) || "/user/logout".equals(servletPath)
                || (session != null && session.getAttribute("username") != null)) {
            filterChain.doFilter(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {}
}
