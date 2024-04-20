package org.dbs.ledger.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import managers.jwt.impl.JwtAccessTokenManager;
import managers.jwt.models.JwtPayload;
import org.dbs.ledger.configuration.contexts.UserContext;
import org.dbs.ledger.util.CommonUtils;
import org.dbs.ledger.util.MessageConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtAccessTokenManager jwtAuthenticationManager;

    private final UserContext userContext;

    public JwtFilter(
            JwtAccessTokenManager jwtAuthenticationManager,
            UserContext userContext
    ) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.userContext = userContext;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader(MessageConstants.AUTHORIZATION);
            if (authorizationHeader != null && (CommonUtils.isBearerToken(authorizationHeader))) {
                String token = authorizationHeader.substring(MessageConstants.BEARER_SPACE.length());
                JwtPayload jwtPayload = jwtAuthenticationManager.verifyAndDecodeToken(token);
                setupUserContext(userContext, jwtPayload, token);
                List<SimpleGrantedAuthority> grantedAuthorities = List.of();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        jwtPayload.getSub(), null, grantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception ignored) {
            // Ignore Exception
        }
        filterChain.doFilter(request, response);
    }

    private void setupUserContext(UserContext userContext, JwtPayload jwtPayload, String token) {
        userContext.setToken(token);
        userContext.setUserId(jwtPayload.getSub());
    }

}
