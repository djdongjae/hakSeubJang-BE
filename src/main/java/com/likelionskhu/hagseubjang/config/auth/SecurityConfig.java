package com.likelionskhu.hagseubjang.config.auth;

import com.likelionskhu.hagseubjang.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/css/**",
                            "/images/**",
                            "/js/**",
                            "/lecture/list",
                            "/lecture/detail"
                    )
                    .permitAll()
                    .antMatchers(
                            "/lecture/wish",
                            "/lecture/load_save",
                            "/review/**",
                            "/user/**"
                    )
                    .hasRole(Role.USER.name())
                    .anyRequest()
                    .permitAll()
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                    .and()
                    .defaultSuccessUrl("/lecture/list")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/user/login")
        ;
    }

}
