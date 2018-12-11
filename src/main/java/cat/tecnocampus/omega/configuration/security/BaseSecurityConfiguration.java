package cat.tecnocampus.omega.configuration.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/allUsers").hasRole("ADMIN")
                .antMatchers("/user").authenticated()
                .antMatchers("/users/{username}").authenticated()

                .antMatchers("/weather").permitAll()
                .antMatchers("/time").permitAll()

                .antMatchers("/exercise/**").authenticated()
                .antMatchers("/challenge/do/**").authenticated()
                .antMatchers("/tutorial/create/**").authenticated()
                .antMatchers("/forum/create/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/users/{username}")
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .tokenValiditySeconds(241920)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/Errors.html")
                .permitAll();
        http
                .csrf().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .cors();
    }
}
