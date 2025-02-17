package com.travelapp.travelapp.config;

import com.travelapp.travelapp.constants.Roles;
import com.travelapp.travelapp.filter.CsrfCookieFilter;
import com.travelapp.travelapp.filter.JWTValidatorFilter;
import com.travelapp.travelapp.repository.JWTRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.securityexceptionhandling.CustomAccessDeniedHandler;
import com.travelapp.travelapp.securityexceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.travelapp.travelapp.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    private UserRepository userRepository;
    private JWTRepository jwtRepository;
    private JWTService jwtService;

    public SecurityConfig(UserRepository userRepository,
                          JWTRepository jwtRepository,
                          JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtRepository = jwtRepository;
        this.jwtService = jwtService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        }));
        // Set CookieCsrfTokenRepository.withHttpOnlyFalse() when UI is built in JS or other JS based framework
        http.csrf(csrfConfig -> csrfConfig
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/api/v1/users/login")
                        .ignoringRequestMatchers("/api/v1/users/register")
                        .ignoringRequestMatchers("/api/v1/practice/file"))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTValidatorFilter(jwtRepository), BasicAuthenticationFilter.class)
                // requiresSecure() (HTTPS) doesn't work on local network, so we will use requireInsecure() (HTTP)
                // during the development phase
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/practice/file").permitAll()

                        .requestMatchers("/api/v1/users/login").permitAll() // Works
                        .requestMatchers("/api/v1/users/register").permitAll() // Works
                        .requestMatchers("/api/v1/users/profile-pictures/*").hasAnyRole(Roles.ALL_ROLES) // Works
                        .requestMatchers("/api/v1/users/user-info/*").hasAnyRole(Roles.ALL_ROLES) // Works
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users").hasAnyRole(Roles.ALL_ROLES)

                        .requestMatchers(HttpMethod.GET, "/api/v1/places/*").hasAnyRole(Roles.ALL_ROLES) // Works
                        .requestMatchers(HttpMethod.POST, "/api/v1/places/*").hasAnyRole(Roles.TOUR_GUIDE_ADMIN_OWNER) // Works


                        // /api/v1/users/{userId}/pictures is located in PictureController not in UserController
                        .requestMatchers("/api/v1/users/{userId}/pictures").permitAll() // Works
                        .requestMatchers("/api/v1/cities/pictures").permitAll() // Works
                        .requestMatchers("/api/v1/communes/pictures").permitAll() // Works
                        .requestMatchers("/api/v1/villages/pictures").permitAll() // Works
                        .requestMatchers("/api/v1/place-names/pictures").permitAll() // Works
                        .requestMatchers(HttpMethod.GET, "/api/v1/pictures/**").permitAll() // Works
                        .requestMatchers(HttpMethod.POST, "/api/v1/pictures/**").hasAnyRole(Roles.ALL_ROLES) // Works
                        .requestMatchers(HttpMethod.PUT, "/api/v1/pictures/**").hasAnyRole(Roles.ALL_ROLES)
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/pictures/**").hasAnyRole(Roles.ALL_ROLES) // Works

                        // /api/v1/users/collages/* is located in UserController not in CollageController
                        // to avoid repeating '/collage' in every rest path since it's the only
                        // endpoint which contains '/users' in it
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/collages/*").hasAnyRole(Roles.ALL_ROLES) // Works
                        .requestMatchers("/api/v1/collages/**").hasAnyRole(Roles.ALL_ROLES) // Works
                );

        http.formLogin(formLogin -> formLogin.disable());
        http.logout(logout -> {
            logout.logoutSuccessHandler((succes, response, authentication) -> SecurityContextHolder.clearContext())
                    .logoutUrl("/api/v1/users/logout")
                    .addLogoutHandler(new LogoutService(jwtService));
        });
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        CustomUsernamePwdAuthenticationProvider authenticationProvider =
                new CustomUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

}
