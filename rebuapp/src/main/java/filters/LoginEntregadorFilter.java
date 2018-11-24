package filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Entregador;

public class LoginEntregadorFilter implements Filter {

	private List<String> excludedUrls;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String path = ((HttpServletRequest) request).getServletPath();
		if (!excludedUrls.contains(path)) {
			
			Entregador user = null;
			HttpSession sess = ((HttpServletRequest) request).getSession(false);

			if (sess != null) {
				user = (Entregador) sess.getAttribute("entregadorLogado");
			}

			if (user == null) {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/login-entregador.xhtml");
			} else {
				chain.doFilter(request, response);
			}
			
		}
		else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		String excludePattern = arg0.getInitParameter("excludedUrls");
		excludedUrls = Arrays.asList(excludePattern.split(","));
	}
}
