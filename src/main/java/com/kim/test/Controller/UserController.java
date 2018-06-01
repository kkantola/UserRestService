package com.kim.test.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goalkeeper.hcb.security.util.JwtTokenUtil;
import com.kim.test.model.LoginRequest;

@RestController 
@RequestMapping("api")
public class UserController {
	

    	@Autowired
    	AuthenticationManager authenticationManager;

        @Autowired
        JwtTokenUtil tokenProvider;
        
		@RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json")   
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
			System.out.println("===========> login endpoint-========");
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("hcb-token", jwt);

	        return ResponseEntity.ok()
	                    .headers(headers).build();
	    }
		
		/**
		 * This endpoint should be public
		 * @return
		 */
		@RequestMapping(value = "/health", method = RequestMethod.GET,  produces = "application/json")   
		public String   health(){
			System.out.println("========= health endpoint ====== =====");
			return "{\"api\":\"TestApp\",\"status\":\"UP\"}";
		}
		
		/**
		 * This endpoint should be private
		 * @return
		 */
		@RequestMapping(value = "/privateEndpoint", method = RequestMethod.GET,  produces = "application/json")   
		public String   privateEndpoint(){
			System.out.println("========= health endpoint ====== =====");
			return "{\"api\":\"TestApp\",\"status\":\"UP\"}";
		}
}
