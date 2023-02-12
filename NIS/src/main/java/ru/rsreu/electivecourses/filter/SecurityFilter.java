package ru.rsreu.electivecourses.filter;



import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.utils.AppUtils;
import ru.rsreu.electivecourses.utils.AuthUtils;
import ru.rsreu.electivecourses.utils.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String action = request.getParameter("command");

        User user = AppUtils.getLoginUser(request.getSession());

        if (action==null) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (user != null) {
            wrapRequest = new UserRoleRequestWrapper(request, user, user.getType());
        }

        if (AuthUtils.isExistsPage(request)) {

            if (user == null) {
                response.sendRedirect("nis?command=gotoAuthorization");
                return;
            }

            boolean hasPermission = AuthUtils.hasPermission(wrapRequest);

            if (!hasPermission) {
                response.sendRedirect("nis?command=errorPage");
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }

}