package com.exam.config;

import com.exam.service.Implementation.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
        final String requestTokenHeader=request.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String email=null;
        String password=null;
        String jwtToken=null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken=requestTokenHeader.substring(7);

            try {
                email = this.jwtUtils.extractUsername(jwtToken);
            }
            catch(ExpiredJwtException e) {
                e.printStackTrace();
                System.out.println("jwt token has expired");
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("error");
            }


        }else{
            System.out.println("Invalid token,not start with bearer");
        }



        //validated

        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            final UserDetails userDetails=this.userDetailsService.loadUserByUsername(email);
            if(this.jwtUtils.validateToken(jwtToken,userDetails)){

                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        }else{
            System.out.println("Token is not valid");
        }

        filterChain.doFilter(request,response);

    }


}
