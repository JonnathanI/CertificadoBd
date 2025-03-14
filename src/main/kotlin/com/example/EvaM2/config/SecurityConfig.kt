package com.example.EvaM2.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
@Configuration
class SecurityConfig {
    @Autowired
    private val jwtFilter: JwtFilter? = null

    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf { csrf -> csrf.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authRequest ->
                authRequest
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/api/certificado/**").permitAll()
                    .requestMatchers("/student/student-details/**").permitAll()
                    .requestMatchers("/course/**").hasAnyRole("admin","stud","train")
                    .requestMatchers("/certificate/**").hasAnyRole("admin","train")
                    .requestMatchers("/student/**").hasAnyRole("admin")
                    .requestMatchers("/trainer/**").hasAnyRole("admin")
                   // .requestMatchers("/api/certificado/** ").hasAnyRole("admin")
                    .requestMatchers("/import/**").hasAnyRole("admin")


                    .anyRequest().denyAll()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()


    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager? {
        return configuration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}