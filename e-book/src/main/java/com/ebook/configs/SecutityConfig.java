package com.ebook.configs;

import com.ebook.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecutityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailService userDetailService;

    @Autowired
    public void setUserDetailsService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/vendor/**").hasAnyRole("VENDOR", "ADMIN")
                .antMatchers("/read_profile/**").hasAuthority("READ_PROFILE")
                /*.antMatchers("/api/registration/vendor/").hasRole("USER")*/
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("**/logout"))
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

}
