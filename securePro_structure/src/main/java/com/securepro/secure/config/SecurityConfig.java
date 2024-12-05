package com.securepro.secure.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import javax.sql.DataSource;

//@Configuration
public class SecurityConfig {

//    private final DataSource dataSource;
//
//    public SecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .antMatchers("/api/auth/**").permitAll()
//                        .antMatchers("/api/admin/**").hasRole("ADMIN")
//                        .antMatchers("/api/user/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout().permitAll();
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT login, password, active FROM user WHERE login = ?")
//                .authoritiesByUsernameQuery("SELECT u.login, r.name FROM user u INNER JOIN role r ON u.id = r.user_id WHERE u.login = ?")
//                .passwordEncoder(passwordEncoder());
//    }
}