package com.dbtask.bookseller.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager users(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/bookseller/main",
                                        "/books/",
                                        "/user/register")
                                .permitAll()

                                .requestMatchers("/books/add",
                                        "/bookseller/administration",
                                        "/user/add-staff",
                                        "/books/supply-page",
                                        "/books/supply",
                                        "/books/set-price-page",
                                        "/books/set-price")
                                .hasRole("ADMIN")

                                .requestMatchers("/books/to-cart/**",
                                        "/books/cart",
                                        "/orders/create",
                                        "/orders/",
                                        "/user/info")
                                .hasAnyRole("CLIENT", "STAFF", "ADMIN")
                                .requestMatchers(
                                        "/orders/staff",
                                        "/orders/assemble/*",
                                        "/orders/assemble-stop/*",
                                        "/orders/staff-assemble",
                                        "/orders/assemble-item/*",
                                        "/orders/find-form",
                                        "/orders/find",
                                        "/orders/set-status/**"
                                        )
                                .hasAnyRole("STAFF", "ADMIN")
                                //.requestMatchers("")
                        //.requestMatchers("/books/*", "/bookseller/*").permitAll()
                        //.requestMatchers("/books/add", "/books/addForm", "/bookseller/administration").hasRole("ADMIN")
                );
        http.formLogin().permitAll()
                .and().logout().permitAll()
                .and().formLogin().defaultSuccessUrl("/books/")
                .and().logout().logoutSuccessUrl("/bookseller/main");
        http.csrf().disable();
                /*http.formLogin((form) -> form
                        .loginPage("/login_page")
                        .defaultSuccessUrl("/test")
                        .permitAll()
                )*/
        return http.build();
    }
}
