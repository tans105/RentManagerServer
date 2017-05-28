package com.rentalmanager.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.google.gson.Gson;
import com.rentalmanager.constants.Constants;
import com.rentalmanager.entity.dto.GenericResponseDTO;
import com.rentalmanager.service.JWTService;

/**
 * 
 * @author tanmay
 *
 */
public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		if (Constants.OPTIONS.equals(request.getMethod())) {
			chain.doFilter(req, res);
		} else {
			final String authHeader = request.getHeader(Constants.AUTHORIZATION);
			GenericResponseDTO genResponse = new GenericResponseDTO();
			Gson gson = new Gson();
			if (authHeader == null || !authHeader.startsWith(Constants.BEARER_SPACE)) {
				setHttpResponse(gson, genResponse, response, Constants.MISSING_AUTH_HEADER, Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());
				return;
			}

			final String token = authHeader.substring(7);

			try {
				final Claims claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token).getBody();
				if (null == claims.get(Constants.USER_ID)) {
					setHttpResponse(gson, genResponse, response, Constants.INVALID_TOKEN, Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());
					return;
				}
				JWTService service = new JWTService(claims.get(Constants.USER_ID).toString());
				if (!service.verifyJWT(token)) {
					setHttpResponse(gson, genResponse, response, Constants.TOKEN_EXPIRED, Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());
					return;
				}
				request.setAttribute("claims", claims);
				chain.doFilter(req, res);
			} catch (final SignatureException e) {
				setHttpResponse(gson, genResponse, response, Constants.INVALID_TOKEN, Boolean.FALSE, HttpStatus.UNAUTHORIZED.value());
				return;
			}
		}

	}

	public void setHttpResponse(Gson gson, GenericResponseDTO genResponse, HttpServletResponse response, String responseMsg, Boolean success, int status) {
		genResponse.setResponseMsg(responseMsg);
		genResponse.setSuccess(success);
		response.setStatus(status);
		try {
			response.getWriter().write(gson.toJson(genResponse));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
