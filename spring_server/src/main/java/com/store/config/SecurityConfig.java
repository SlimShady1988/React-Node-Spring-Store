package com.store.config;

import com.store.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//@EnableGlobalMethodSecurity // Лише для PRE AUTHORIZE CONFIGURATION
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*################################################################################################################*/
    /*#########################           JWT AUTHORIZATION CONFIGURATION                       ###################№##*/

    private final JwtConfigurer jwtConfigurer;

    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().apply(jwtConfigurer);
    }



    /*################################################################################################################*/
    /*#########################           START CONFIGURATION                                   ######################*/
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }
    /*################################################################################################################*/
    /*#########################           "USER DETAILS SERVICE OVERRIDE” CONFIGURATION         ######################*/
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }
//        @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles(Role.ADMIN.name())
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles(Role.USER.name())
//                        .build()
//        );
//    }

    /*################################################################################################################*/
    /*#########################           "httpBasic” CONFIGURATION BY ROLES                    ######################*/
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/api/**").hasRole(Role.USER.name())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Role.ADMIN.name())
//                .anyRequest().authenticated()
//                .and().httpBasic();
//    }
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles(Role.ADMIN.name())
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles(Role.USER.name())
//                        .build()
//        );
//    }

    /*################################################################################################################*/
    /*#########################           "httpBasic" CONFIGURATION BY AUTHORITIES              ######################*/
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Role.USER.name())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Role.ADMIN.name())
//                .anyRequest().authenticated()
//                .and().httpBasic();
//    }
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities(Role.ADMIN.getAuthorities())
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .authorities(Role.USER.getAuthorities())
//                        .build()
//        );
//    }

    /*################################################################################################################*/
    /*#########################           Pre Authorize CONFIGURATION                           ###################№##*/
// Додаємо бін @EnableGlobalMethodSecurity до конфіг файлу, далі можна вказувати над екшенами контроллера
// анотацією @PreAuthorize('hasAuthority('user:read')') - наприклад для читання,
// які права доступу потрібні, точніше які дозволи мають мати користувачі для доступу до вказаних шляхів

    /*################################################################################################################*/
    /*#########################           LOGIN FORM method CONFIGURATION                       ###################№##*/
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/user/login")                 //Лише якщо ви надаєте по цьому шляху форму для логіна,
////                якщо ні, то використовуємо без цього методу, а викликаємо стандартну форму
//                .permitAll()
//                .defaultSuccessUrl("/device")
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/device");
//
//    }
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities(Role.ADMIN.getAuthorities())
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .authorities(Role.USER.getAuthorities())
//                        .build()
//        );
//    }

    /*################################################################################################################*/
    /*#########################           LOGIN FORM from DB CONFIGURATION                      ###################№##*/
//    private final UserDetailsService userDetailsService;
//    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/user/login")                 //Лише якщо ви надаєте по цьому шляху форму для логіна,
////                якщо ні, то використовуємо без цього методу, а викликаємо стандартну форму
//                .permitAll()
//                .defaultSuccessUrl("/device")
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/device");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//
//        return daoAuthenticationProvider;
//    }
}
