package me.annanjin.shop;

import me.annanjin.shop.security.CustomPersistentTokenBasedRememberMeServices;
import me.annanjin.shop.security.UrlAuthenSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@SpringBootApplication
public class ShopApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "CustomUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier(value = "HibernateRepository")
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UrlAuthenSuccessHandler urlAuthenSuccessHandler;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        return new CustomPersistentTokenBasedRememberMeServices("remember-me", userDetailsService, persistentTokenRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access_denied");

        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");

        http.authorizeRequests().and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=1").successHandler(urlAuthenSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .and().rememberMe().key("remember-me").rememberMeServices(this.persistentTokenBasedRememberMeServices()).tokenValiditySeconds(604800)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
