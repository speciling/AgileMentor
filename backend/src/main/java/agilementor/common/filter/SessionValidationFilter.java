package agilementor.common.filter;


import static org.springframework.util.ObjectUtils.isEmpty;

import agilementor.common.exception.ExceptionResponse;
import agilementor.common.exception.InvalidSessionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import org.springframework.util.PatternMatchUtils;

public class SessionValidationFilter implements Filter {

    private static final String[] whiteList = {"/api/auth/login", "/api/auth/login/code/google",
        "/api/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {

            if (isLoginCheckPath(requestURI)) {
                HttpSession session = request.getSession(false);
                if (isEmpty(session) || isEmpty(session.getAttribute("memberId"))) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    ExceptionResponse exceptionResponse = new ExceptionResponse(
                        new InvalidSessionException().getMessage());
                    response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));

                    return;
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        }

    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
