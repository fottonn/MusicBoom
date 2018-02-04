package ru.bugmakers.controller.common.authentication;

import io.restassured.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import ru.bugmakers.dto.common.AuthDTO;

import static io.restassured.RestAssured.given;

/**
 * Created by Ivan
 */
public class AuthenticationControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationControllerTest.class);

    @Test
    public void testAuthenticate() throws Exception {
        AuthDTO rq = new AuthDTO();
        rq.setLogin("+79600571600");
        rq.setPassword("password");
        ResponseBody rs = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(rq)
                .when().post("/authentication").getBody();

        LOGGER.debug(rs.prettyPrint());
    }
}
