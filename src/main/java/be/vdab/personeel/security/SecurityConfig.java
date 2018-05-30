package be.vdab.personeel.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String USERS_BY_USEREMAIL = "select email as username, paswoord as password, 1 as enabled from werknemers where email = ?";
	private static final String AUTHORITIES_BY_USEREMAIL = "select email as username, 'werknemer' as authority from werknemers where email = ?";
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
			.mvcMatchers("/images/**")
			.mvcMatchers("/css/**");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin();
		
	}
	
	@Bean
	JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
		
		JdbcDaoImpl impl = new JdbcDaoImpl();
		impl.setDataSource(dataSource);
		impl.setUsersByUsernameQuery(USERS_BY_USEREMAIL);
		impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USEREMAIL);
		return impl;
		
	}

}
