package annas.dance_schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select email, role from users where email=?")
                .usersByUsernameQuery("select email, password, enabled from users where email=?");


    }
/*    @Bean
    public PasswordEncoder getPasswordEncoder(){
      return  new BCryptPasswordEncoder();
    }*/

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/dance/**").hasAnyAuthority("USER" , "ADMIN" , "TRAINER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin();

    }


}
