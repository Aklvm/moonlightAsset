package com.ecom.moonlight.authOuth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private  final JwtService jwtService; 
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                if (request.getServletPath().contains("/api/v1/auth")) {
                    filterChain.doFilter(request, response);
                    return;
                  }
                  final String authHeader =request.getHeader("Authorization");
                  final String jwt ;
                  final String userId;
                  if(authHeader==null || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                  }
                  jwt=authHeader.substring(7);
                  userId=jwtService.extractUsername(jwt);
                  if(userId!=null &&  SecurityContextHolder.getContext().getAuthentication()==null){
                      UserDetails userdetails=userDetailsService.loadUserByUsername(userId);
                      if(jwtService.isValidToken(jwt,userdetails)){
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                  userdetails,
                                  null,
                                  userdetails.getAuthorities()
                                );
                                  authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

                      }
                  }
                  filterChain.doFilter(request, response);
    }

}
