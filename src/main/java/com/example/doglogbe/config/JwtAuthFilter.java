package com.example.doglogbe.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOError;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            System.out.println("Authorization Header 원본: [" + authorizationHeader + "]");
            String token = authorizationHeader.substring(7).trim();
            System.out.println("받은 JWT 토큰: [" + token + "]");

            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsername(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                // 토큰이 유효하지 않을 때 401 응답
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        } else {
            // 토큰이 없을 때 401 응답 (특히 보호된 경로일 경우)
            // 다만, 공개 API라면 그냥 넘어가게 하려면 이 부분 제거 가능
            String path = request.getRequestURI();
            if (path.startsWith("/admin")) { // 관리자 API 등 인증 필요한 경로에 대해 엄격 처리
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing or invalid");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }


}
