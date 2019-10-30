package com.eastday.demo.config;

import com.eastday.demo.dao.IMenuDao;
import com.eastday.demo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private IMenuDao menuDao;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println(username);
            String password = "456";
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String passwordAfterEncoder = passwordEncoder.encode(password);
            List<Menu> permissions = menuDao.selectMenuByUserId(1);
            System.out.println(username);
            String[] permissionArr = new String[permissions.size()];
            int index = 0;
            for (Menu permission : permissions) {
                permissionArr[index] = permission.getMenuCode();
                index++;
            }
            return User.withUsername(username).password(passwordAfterEncoder).authorities(permissionArr).build();
        };
    }


    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeRequests().antMatchers("/user/**","/login/**","/getKaptcha").permitAll().
                and().formLogin().loginPage("/login").successForwardUrl("/login/success").
                and().authorizeRequests().anyRequest().permitAll().
                and().csrf().disable();

            }
        };
    }
}
