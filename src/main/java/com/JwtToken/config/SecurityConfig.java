    package com.JwtToken.config;

    import com.JwtToken.security.CustomUserDetailsService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {

        @Autowired
        private CustomUserDetailsService userDetailsService;
        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }


        //following properties are also called security filter chain cause we define who can access and who can't access specific services/endpoints
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeHttpRequests((requests) -> requests
                            .requestMatchers(HttpMethod.GET, "/api/auth/signup").permitAll()   // Permit access to URLs starting with "/api/"
                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                            .anyRequest().authenticated()   // Require authentication for other URLs
                    )
                    .httpBasic();
            return http.build();
            //antMatcher().permitAll  is not working here
        }

    //password Encoder is used for password increption during signup/Registration
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());
   }



//        @Bean
//        protected UserDetailsService userDetailsService() {
//            UserDetails user = User.builder().username("pankaj").password(passwordEncoder()
//                    .encode("password")).roles("USER").build();
//
//            UserDetails admin = User.builder().username("admin").password(passwordEncoder()
//                    .encode("admin")).roles("ADMIN").build();
//
//            // Other multiple roles can be define here.
//
//            return new InMemoryUserDetailsManager(admin, user);
//        }
    }


//

