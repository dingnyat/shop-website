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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@SpringBootApplication
public class ShopApplication extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

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
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        return new CustomPersistentTokenBasedRememberMeServices("remember-me", userDetailsService, persistentTokenRepository);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equalsIgnoreCase("current-locale")) {
                            String localeCode = cookie.getValue();
                            if (localeCode != null && !localeCode.isEmpty()) {
                                return new Locale(localeCode);
                            }
                        }
                    }
                }
                return Locale.ENGLISH;
            }
        };
        localeResolver.setCookieMaxAge(60 * 60 * 24);
        localeResolver.setCookieName("current-locale");
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
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
        http.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN");

        http.authorizeRequests().and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=1").successHandler(urlAuthenSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .and().rememberMe().key("remember-me").rememberMeServices(this.persistentTokenBasedRememberMeServices()).tokenValiditySeconds(604800)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
