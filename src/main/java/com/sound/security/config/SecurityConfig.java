package com.sound.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired

	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(" select username,password, enabled from users where username=? ")
				// .authoritiesByUsernameQuery(
				// " select b.username, a.role from user_roles a, users b where
				// b.username=? and a.userid=b.userid ")
				.authoritiesByUsernameQuery(
						" SELECT a.username, e.menu_code AS authority FROM users a,user_roles b,roles c,role_menu d,menus e WHERE  a.username = ? AND a.userid = b.userid  AND b.role = c.role   AND c.roleid = d.role_id  AND d.menu_id = e.menu_id      ")
				.passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/druid/**", "/**/account/**").and().headers().frameOptions().sameOrigin().and()
				.authorizeRequests().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.tif")
				.permitAll()

				.antMatchers("/**/accountmaintain/index/**").hasAnyRole("USERMANAGEMENT")
				.antMatchers("/**/roles/index/**").hasAnyRole("ROLEMENUMANAGEMENT")	
				// 登录页面
				.antMatchers("/login","/login-error").permitAll()
				// 其它认证
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").failureUrl("/login-error").successHandler(successHandler)
				//异常处理--无权限提示
				.and().exceptionHandling().accessDeniedPage("/cs/403")
				//记住我
				.and().rememberMe().tokenValiditySeconds(3600).key("SoundWSS");

	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

}
