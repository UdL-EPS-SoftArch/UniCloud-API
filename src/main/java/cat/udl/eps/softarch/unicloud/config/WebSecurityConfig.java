package cat.udl.eps.softarch.unicloud.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Value("${allowed-origins}")
        String[] allowedOrigins;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/identity").authenticated()
                    .antMatchers(HttpMethod.POST, "/users").anonymous()
                    .antMatchers(HttpMethod.POST, "/users/*").denyAll()
                    .antMatchers(HttpMethod.POST, "/subjects").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/subjects/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/subjects/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/subjects/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/universities").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/universities/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/universities/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/universities/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/degrees").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/degrees/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/degrees/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/degrees/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/ratings").hasRole("STUDENT")
                    .antMatchers(HttpMethod.PATCH, "/ratings/*").hasRole("STUDENT")
                    .antMatchers(HttpMethod.DELETE, "/ratings/*").hasAnyRole("ADMIN", "STUDENT")
                    .antMatchers(HttpMethod.POST, "/resources").hasRole("STUDENT")
                    .antMatchers(HttpMethod.PUT, "/resources/*").hasRole("STUDENT")
                    .antMatchers(HttpMethod.PATCH, "/resources/*").hasRole("STUDENT")
                    .antMatchers(HttpMethod.DELETE, "/resources/*").hasRole("STUDENT")
                    .antMatchers(HttpMethod.POST, "/**/*").authenticated()
                    .antMatchers(HttpMethod.PUT, "/**/*").authenticated()
                    .antMatchers(HttpMethod.PATCH, "/**/*").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/**/*").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic().realmName("demo")
                    .and()
                    .cors()
                    .and()
                    .csrf().disable();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(Arrays.asList(allowedOrigins));
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
            corsConfiguration.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return source;
        }

        @Bean
        public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
            return new SecurityEvaluationContextExtension();
        }
    }


