package com.wallex.financial_platform.configs.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 🔹 Obtener el token del encabezado de la solicitud (Authorization: Bearer <TOKEN>)
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔹 Extraer el token JWT
        String token = authHeader.substring(7);

        // 🔹 Validar el token y extraer el email del usuario
        String email = jwtTokenProvider.getEmailFromToken(token);

        // 🔹 Si el email no es nulo y el usuario aún no está autenticado
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 🔹 Cargar los detalles del usuario desde la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // 🔹 Validar el token con los detalles del usuario
            if (jwtTokenProvider.validateToken(token)) {

                // 🔹 Crear la autenticación de Spring Security
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 🔹 Establecer la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 🔹 Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
