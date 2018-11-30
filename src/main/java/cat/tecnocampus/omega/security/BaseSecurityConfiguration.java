package cat.tecnocampus.omega.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/allUsers").hasRole("ADMIN")
                .antMatchers("/profile/users/*").permitAll()
                .antMatchers("/user").authenticated()
                .antMatchers("/users/{username}").authenticated()
                .antMatchers("/exercise/**").authenticated()
                .antMatchers("/challenge/all").permitAll()
                .antMatchers("/challenge/**").authenticated()
                .antMatchers("/tutorial/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .tokenValiditySeconds(241920)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/Errors.html");
        http
                .csrf().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .cors();
    }
}
