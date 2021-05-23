package br.com.igti.edugame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.authorizeRequests().antMatchers("/").permitAll()// Make H2-Console non-secured; for debug purposes
		.and().csrf().disable()
		// Allow pages to be loaded in frames from
		// the same origin; needed for H2-Console
		.headers().frameOptions().sameOrigin()
		.and().cors();
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
//        auth.inMemoryAuthentication()
//          .withUser("ana").password("{noop}123").roles("ALUNO")
//          .and().withUser("beto").password("{noop}123").roles("ALUNO")
//          .and().withUser("carol").password("{noop}123").roles("ALUNO")
//          .and().withUser("tio").password("{noop}123").roles("ALUNO","PROFESSOR")
//          .and()
//          .withUser("admin").password("{noop}password").roles("ALUNO", "PROFESSOR", "ADMIN")
//          ;
        
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
