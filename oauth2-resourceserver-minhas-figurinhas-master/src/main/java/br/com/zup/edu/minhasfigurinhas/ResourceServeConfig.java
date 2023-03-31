package br.com.zup.edu.minhasfigurinhas;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServeConfig extends WebSecurityConfigurerAdapter {
    private static final String READ = "SCOPE_minhas-figurinhas:read";
    private static final String WRITE = "SCOPE_minhas-figurinhas:write";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
            .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .rememberMe().disable()
                .httpBasic().disable()
                .requestCache().disable()
                .headers().frameOptions().deny()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/albuns**").hasAuthority(READ)
                .antMatchers(HttpMethod.GET, "/api/albuns/{id}**").hasAuthority(READ)
                .antMatchers(HttpMethod.POST, "/api/albuns/**").hasAuthority(WRITE)
                .antMatchers(HttpMethod.POST, "/api/albuns/{albumId}/figurinhas**").hasAuthority(WRITE)

                .anyRequest()
                .hasAuthority(WRITE)
            .and()
                .oauth2ResourceServer()
                .jwt();
    }
}
