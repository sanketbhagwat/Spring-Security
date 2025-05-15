package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/
        http.csrf(csrfConfig -> csrfConfig.disable());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error","/register").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService(DataSource dataSource){
    //     // UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$./6OI5OPH88kpsV8y3s56u.Oxz8YWgOhWxBFNHhD76Fz5FwnayOpu").authorities("read").build();
    //     // UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$./6OI5OPH88kpsV8y3s56u.Oxz8YWgOhWxBFNHhD76Fz5FwnayOpu").authorities("admin").build();
    //     // return new InMemoryUserDetailsManager(user, admin);

    //     return new JdbcUserDetailsManager(dataSource);
    // }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
 
    /** enhancement from 6.3 version of spring boot (haveIbeenpwned) */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
