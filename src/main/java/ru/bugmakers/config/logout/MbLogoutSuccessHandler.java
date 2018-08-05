package ru.bugmakers.config.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ru.bugmakers.config.jwt.TokenAuthentication;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.localpers.service.WhiteTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ivan
 */
public class MbLogoutSuccessHandler implements LogoutSuccessHandler {

    private WhiteTokenService whiteTokenService;

    @Autowired
    public void setWhiteTokenService(WhiteTokenService whiteTokenService) {
        this.whiteTokenService = whiteTokenService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        if (authentication instanceof TokenAuthentication) {
            whiteTokenService.deleteWhiteToken(((TokenAuthentication) authentication).getToken());
        }

        ObjectMapper mapper = new ObjectMapper();
        MbResponse mbResponse = MbResponse.success();
        String rs = mapper.writeValueAsString(mbResponse);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(rs);
        response.flushBuffer();
    }
}
