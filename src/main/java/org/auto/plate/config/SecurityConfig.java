package org.auto.plate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ObjectMapper objectMapper = new ObjectMapper();
                String s = objectMapper.writeValueAsString(RespBean.ok("登录成功！！！",authentication.getPrincipal()));
                out.write(s);
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                RespBean error = RespBean.error("登录失败！！！");
                if (exception instanceof BadCredentialsException) {
                    error.setMsg("用户名或者密码输入错误，登录失败！！！");
                } else if (exception instanceof DisabledException) {
                    error.setMsg("账户被禁用，请联系管理员！！！");
                } else if (exception instanceof CredentialsExpiredException) {
                    error.setMsg("密码过期登录失败，请联系管理员！！！");
                } else if (exception instanceof AccountExpiredException) {
                    error.setMsg("账户过期，登录失败！！！");
                }
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ObjectMapper objectMapper = new ObjectMapper();
                String s = objectMapper.writeValueAsString(error);
                out.write(s);
                out.flush();
                out.close();
            }
        });
        loginFilter.setFilterProcessesUrl("/api/doLogin");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        return loginFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(userService+"userservice来了");
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("请求过来了");
        http.authorizeRequests().anyRequest()
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();
        http.addFilterAfter(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
