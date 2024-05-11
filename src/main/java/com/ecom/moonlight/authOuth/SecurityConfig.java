package com.ecom.moonlight.authOuth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private static final String[] WHITE_LIST={"otp/*","email/*"};
    private final AuthenticationProvider authenticationProvider;
    private  final JwtAuthFilter jwtAuthFilter;

    

    
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
  
         http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( req ->
                req.requestMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                )
            .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
            ;
            return http.build();

        } 
    
    
    
  
   

}
