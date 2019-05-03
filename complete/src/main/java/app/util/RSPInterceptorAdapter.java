package app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RSPInterceptorAdapter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.info("Pre handle: {},  parameters: {}", request.getRequestURL().toString(), getParametersFromRequest(request));
        return true;
    }

    private String getParametersFromRequest(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder("| ");
        for (String parameterName : request.getParameterMap().keySet()) {
            builder.append(parameterName);
            builder.append(": ");
            builder.append(request.getParameter(parameterName));
            builder.append(" | ");
        }
        return builder.toString();
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info("Post handle: " + request.getRequestURL().toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        handler.hashCode();
        log.info("After completion:" + request.getRequestURL().toString());
    }
}
