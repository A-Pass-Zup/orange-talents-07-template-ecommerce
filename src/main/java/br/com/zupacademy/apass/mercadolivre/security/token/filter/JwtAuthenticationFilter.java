package br.com.zupacademy.apass.mercadolivre.security.token.filter;

import br.com.zupacademy.apass.mercadolivre.security.service.UserService;
import br.com.zupacademy.apass.mercadolivre.security.token.JwtManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserService userService;

    private JwtManager jwtManager;

    public JwtAuthenticationFilter(UserService userService, JwtManager jwtManager) {
        this.userService = userService;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.retrieveToken(request);
        if (token != null && this.jwtManager.isValid(token)) {
            UserDetails user = this.userService.loadUserByUsername(this.jwtManager.getUsername(token));
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var hAuth = request.getHeader("Authorization");
        if(hAuth == null || hAuth.isEmpty() || !hAuth.startsWith("Bearer ")) {
            return null;
        }
        return hAuth.substring(7, hAuth.length());
    }

}
