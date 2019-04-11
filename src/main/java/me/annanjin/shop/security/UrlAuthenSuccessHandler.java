package me.annanjin.shop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrlAuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String targetUrl = "";
        if (roles.contains("ROLE_ADMIN")) {
            targetUrl = "/admin/dashboard";
            request.getSession().setAttribute("CKFinder_UserRole", "ROLE_ADMIN");
        } else if (roles.contains("ROLE_USER")) {
            targetUrl = "/";
            request.getSession().setAttribute("CKFinder_UserRole", "ROLE_USER");
        }
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
        request.getSession().setAttribute("isLoggedIn", "true");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
