package ru.bugmakers.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ru.bugmakers.dto.common.AuthDTO;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbAuthenticationException;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.validator.JsonLoginPasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * Created by Ivan
 */
public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private JsonLoginPasswordValidator jsonLoginPasswordValidator;

    @Autowired
    public void setJsonLoginPasswordValidator(JsonLoginPasswordValidator jsonLoginPasswordValidator) {
        this.jsonLoginPasswordValidator = jsonLoginPasswordValidator;
    }

    public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**");
        setAuthenticationManager(authenticationManager);
        //если аутентификация успешная, устанавливаем контекст и форвардим к сервлету
        setAuthenticationSuccessHandler(((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String pathInfo = request.getPathInfo() != null ? request.getPathInfo() : "";
            request.getRequestDispatcher(request.getServletPath() + pathInfo).forward(request, response);
        }));
        //если аутентификация неуспешная, то редиректим на страницу логина
        setAuthenticationFailureHandler(((request, response, exception) -> {
            ObjectMapper mapper = new ObjectMapper();
            MbException mbException = ((MbAuthenticationException) exception).getMbException();
            MbResponse mbResponse = new MbResponse(mbException, RsStatus.ERROR);
            String rs = mapper.writeValueAsString(mbResponse);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(rs);
            response.flushBuffer();
        }));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper mapper = new ObjectMapper();

        Authentication authentication;
        try {
            BufferedReader reader = request.getReader();
            AuthDTO authRq = mapper.readValue(reader, AuthDTO.class);
            reader.close();
            jsonLoginPasswordValidator.validate(authRq);
            String login = authRq.getLogin();
            String password = authRq.getPassword();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, password);
            authentication = getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } catch (MbException e) {
            throw new MbAuthenticationException(e);
        } catch (DisabledException e) {
            throw new MbAuthenticationException(MbException.create(MbError.AUE06));
        } catch (BadCredentialsException e) {
            throw new MbAuthenticationException(MbException.create(MbError.AUE05));
        } catch (Exception e) {
            throw new MbAuthenticationException(MbException.create(MbError.AUE07));
        }

        return authentication;

    }
}
