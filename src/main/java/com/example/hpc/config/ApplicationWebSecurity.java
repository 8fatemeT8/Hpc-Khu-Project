package com.example.hpc.config;

//import com.example.hpc.config.jwt.ExceptionHandlerFilter;

import com.example.hpc.config.jwt.JwtAuthenticationEntryPoint;
import com.example.hpc.config.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationWebSecurity extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private UserDetailsService jwtUserDetailsService;

    private JwtRequestFilter jwtRequestFilter;

    //	private ExceptionHandlerFilter exceptionHandlerFilter;

    @Value("${google.recaptcha.site.key}")
    String googleRecaptchaSiteKey;
    @Value("${google.recaptcha.secret.key}")
    String googleRecaptchaSecretKey;

    public ApplicationWebSecurity(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailsService jwtUserDetailsService,
                                  JwtRequestFilter jwtRequestFilter /*,ExceptionHandlerFilter exceptionHandlerFilter*/) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
//		this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure UserAuthentication so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers(
                        "/api/account/login",
                        "/api/person/account",
                        "/api/account/verify",
                        "/assets/scss/**",
                        "/assets/fonts/**",
                        "/assets/images/**",
                        "/api/account/forget-password",
                "/api/user/recaptcha",
                "/api/account/v2/login"
                ).permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//				.addFilterBefore(exceptionHandlerFilter, JwtRequestFilter.class);
        ;
    }

    @Bean
    public String googleRecaptchaSiteKey() {
        return this.googleRecaptchaSiteKey;
    }

    @Bean
    public String googleRecaptchaSecretKey() {
        return this.googleRecaptchaSecretKey;
    }

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
}
