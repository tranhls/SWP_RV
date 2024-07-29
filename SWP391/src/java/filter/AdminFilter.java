// AdminFilter.java
package filter;

import entity.Account;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/accmanager", "/manager", "/accmanager.jsp", "/manager.jsp"})
public class AdminFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public AdminFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:doFilter()");
        }
        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            String message = "Please log in to access admin functions!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("Login.jsp").forward(request, response);
        } else if (acc.getIsAdmin() != 1) {
            String message = "You do not have permission to access this function!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("errormanage.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AdminFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AdminFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        // Xử lý lỗi
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
