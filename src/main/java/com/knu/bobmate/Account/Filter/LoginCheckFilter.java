package com.knu.bobmate.Account.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 로그인 확인을 위한 Filter입니다.
 * 로그인 확인이 진행되지 않을 URI의 경우 whitelist에 추가하면 됩니다.
 * 로그인 확인이 필요한 모든 요청의 경우 header에 token이라는 key로 토큰을 보내야 합니다.
 *
 * 로그인시 token 에 새로운 토큰을 저장하게 되며, 확인은 여기 필터에서 이루어집니다.
 * token 헤더를 확인하고, 맞으면 request 에 user의 id를 attribute 로 전송합니다.
 * 컨트롤러에서 사용시 @RequestAttribute int userId 와 같이 가져올수 있습니다.
 * 만약 whitelist 에 지정되지 않은 요청이 로그인 확인이 불가능할경우 403/Forbidden 를 응답하게 됩니다.
 * 이외 예외 발생시 403/Forbidden 을 응답합니다.
 */
@Slf4j
@Component
@Order(1)
@WebFilter(urlPatterns = "/**")
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/account/login", "/restaurants", "/restaurants/[0-9]+", "/restaurants/reviews/[0-9]+","/restaurants/reviews"};

    /**
     * 로그인,로그아웃 시 여기에 접근해 토큰을 최신화 합니다.
     */
    public static HashMap<String, Integer> token = new HashMap<>(){{put("testtoken", 1);}};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                if(!token.containsKey(httpRequest.getHeader("token"))) {
                    log.error("Login Filter Blocked");
                    httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
                    return;
                } else {
                    log.error("Login Filter Passed");
                    request.setAttribute("userId", token.get(httpRequest.getHeader("token")));
                }
            }
            chain.doFilter(request, response);
        } catch(Exception e) {
            log.error("Login Filter Error - " + e.getMessage());
            httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
        } finally {
            log.info("Login Filter From - " + requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        boolean match = false;
        for(int i = 0;i < whitelist.length;i++) {
            Pattern p = Pattern.compile(whitelist[i]);
            if(p.matcher(requestURI).matches()) {
                match = true;
                return false;
            }
        }
        return true;
    }
}
