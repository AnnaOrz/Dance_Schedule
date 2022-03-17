package annas.dance_schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT u.email, r.name FROM users u INNER JOIN users_roles ur ON u.id = ur.users_id INNER JOIN role r on ur.roles_id = r.id WHERE u.email=?")
                .usersByUsernameQuery("select email, password, enabled from users where email=?");


    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/dance/**").hasAnyRole("USER", "ADMIN", "TRAINER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().permitAll()
                .defaultSuccessUrl("/schedule", true)
                .and().logout().permitAll().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/denied");

    }
}
