package com.example.wantedchallenge.infrastructure.security;

import com.example.wantedchallenge.domain.user.User;
import com.example.wantedchallenge.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    // 인증을 건너뛰어야 하는 경로 목록을 정의합니다.
    private static final Set<String> SKIP_PATHS = Set.of(
            "GET /api/v1/articles/{articleId}",
            "GET /api/v1/comments/{articleId}/comment"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestUri = request.getRequestURI();
            String requestMethod = request.getMethod();

            // 요청 URI가 인증을 건너뛰어야 하는 경로 목록에 있는지 확인합니다.
            boolean skip = SKIP_PATHS.stream().anyMatch(path -> {
                String[] parts = path.split(" ", 2);
                if (parts.length < 2) return false;
                String method = parts[0];
                String uriTemplate = parts[1];
                return method.equalsIgnoreCase(requestMethod) && new UriTemplate(uriTemplate).matches(requestUri);
            });

            if (skip) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = parseBearerToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtProvider.validate(token);
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            User user = userRepository.findByUserId(userId);
            String role = user.getRole().getAuthority();

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    // request 객체로부터 token 꺼내오는 작업
    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}