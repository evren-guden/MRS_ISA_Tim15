package rs.travel.bookingWithEase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import rs.travel.bookingWithEase.security.TokenUtils;
import rs.travel.bookingWithEase.security.auth.TokenAuthenticationFilter;
import rs.travel.bookingWithEase.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// Definisemo nacin autentifikacije
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	TokenUtils tokenUtils;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
            .antMatchers("/users/confirm").permitAll()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/rentacars/search").permitAll()
			.antMatchers("/branchs").permitAll()
			.antMatchers("/rentacars/{id}/vehicles").permitAll()
			.antMatchers("/vehicles/{id}/reservations").permitAll()
			.antMatchers("/airlines").permitAll()
			.antMatchers("/flights").permitAll()
			.antMatchers("/flights/search").permitAll()
			.antMatchers("/hotels").permitAll()
			.antMatchers("/hotels/rooms").permitAll()
			.antMatchers("/hotels/search").permitAll()
			.antMatchers(HttpMethod.GET, "/hotels/{id}").permitAll()
			.antMatchers("/hotels/{hotelId}/rooms").permitAll()
			.antMatchers(HttpMethod.GET, "/hotels/{hotelId}/specialOffers").permitAll()
			.antMatchers(HttpMethod.GET, "hotels/{hotelId}/roomReservations").permitAll()
			.antMatchers("/companies").permitAll()
			.antMatchers("/users").permitAll()
			.antMatchers("/users/{userId}/roomReservations").permitAll()
			.antMatchers("/users/{userId}/roomReservations/{rrId}").permitAll()
			//.antMatchers(HttpMethod.GET, "/rentacars").permitAll()
			//.antMatchers("**.html").permitAll()
			//.antMatchers("**.css").permitAll()
			//.antMatchers("**.js").permitAll()
			//.antMatchers("**.js.**").permitAll()
			.anyRequest().authenticated().and()
			//.formLogin()
			//.loginPage("/login.html").permitAll().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);
		
		http.csrf().disable();
	}
	
	// Generalna bezbednost aplikacije
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers(HttpMethod.POST, "/users/registration");
		web.ignoring().antMatchers(HttpMethod.GET, "/users/confirm-account");
		web.ignoring().antMatchers(HttpMethod.POST, "/users/confirm-account");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/images/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/rentacars");
		web.ignoring().antMatchers(HttpMethod.GET, "/rentacars/searchNameAddress");
		web.ignoring().antMatchers(HttpMethod.GET, "/branchs");
		web.ignoring().antMatchers(HttpMethod.GET, "/rentacars/{id}/vehicles");
		web.ignoring().antMatchers(HttpMethod.POST, "/rentacars/vehicles/search");
		web.ignoring().antMatchers(HttpMethod.GET, "/rentacars/{id}/specialOffers");
		web.ignoring().antMatchers(HttpMethod.GET, "/vehicles/{id}/reservations");
		web.ignoring().antMatchers(HttpMethod.GET, "/users/{roomId}/roomReservations");
		web.ignoring().antMatchers(HttpMethod.GET, "/airlines");
		web.ignoring().antMatchers(HttpMethod.POST, "/airlines/search");
		web.ignoring().antMatchers(HttpMethod.GET, "/flights");
		web.ignoring().antMatchers(HttpMethod.POST, "/flights/search");
		web.ignoring().antMatchers(HttpMethod.GET, "/hotels");
		web.ignoring().antMatchers(HttpMethod.GET, "/rentacars/search");
		web.ignoring().antMatchers(HttpMethod.GET, "/vehicles");
		web.ignoring().antMatchers(HttpMethod.GET, "/vehicleReservations");
		web.ignoring().antMatchers(HttpMethod.GET, "hotels/{hotelId}/quickRoomReservations");
		web.ignoring().antMatchers(HttpMethod.GET, "hotels/{hotelId}/quickRoomReservations/available");
		web.ignoring().antMatchers(HttpMethod.POST, "hotels/{hotelId}/quickRoomReservations/available");
		web.ignoring().antMatchers(HttpMethod.POST, "hotels/{hotelId}/quickRoomReservations");
		web.ignoring().antMatchers(HttpMethod.POST, "hotels/{hotelId}/quickRoomReservations/search");
		web.ignoring().antMatchers(HttpMethod.DELETE, "hotels/{hotelId}/quickRoomReservations/{qrrId}");
	}
}
