package br.com.fiap.residuos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;


    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


//PARA TESTES DE TODOS OS ENDPOINTS SEM NECESSIDADE DE REALIZAR LOGIN TIRE O COMENTARIO DO SEGUINTE TRECHO:

                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Permitir todas as requisições
                )
                .build();



//PARA VOLTAR AO PADRÃO DA APLICAÇÃO COM AUTENTICAÇÃO DEIXE O SEGUINTE TRECHO DE CÓDIGO SEM COMENTARIO:

//                .authorizeHttpRequests(authorize -> authorize
//
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/usuarios").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios").hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated())
//                .addFilterBefore(
//                        verificarToken,
//                        UsernamePasswordAuthenticationFilter.class
//                )
//                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
