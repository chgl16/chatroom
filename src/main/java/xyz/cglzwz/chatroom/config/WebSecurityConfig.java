package xyz.cglzwz.chatroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 本打算用来解决以下Spring Security加密版本异常问题，但是需要这里写密文，就得客户端加密了。麻烦
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     *
     * ######## 而且不知道为啥只是注入auth不调用也生效，还是得注释掉 ###########
     * @return
     */
//    @Deprecated
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
                .withUser("a").password(encoder.encode("123")).roles("USER")
                .and()
                .withUser("b").password(encoder.encode("123")).roles("USER");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 授权请求
                .authorizeRequests()
                // 不拦截 "/login"
                .antMatchers("/login").permitAll()
                .and()
                .formLogin()
                // 自定义登录页面
                .loginPage("/login").permitAll()
                .successForwardUrl("/index")
                .and()
                .logout().permitAll();
//                .and()
//                .csrf().disable();
    }
}
