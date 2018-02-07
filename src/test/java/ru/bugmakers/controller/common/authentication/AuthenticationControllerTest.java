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
        rq.setLogin("+79612345678");
        rq.setPassword("password");
        ResponseBody rs = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(rq)
                .when().post("/authentication").getBody();

        LOGGER.debug(rs.prettyPrint());
    }

    @Test
    public void testArtistInfo() throws Exception {
        ResponseBody rs = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new rsDto("eyJhbGciOiJIUzUxMiJ9.eyJUT0tFTl9DUkVBVEVfREFURSI6MTUxNzk0MTE2Mzk3MiwiVVNFUk5BTUUiOiIrNzk2MTIzNDU2NzgiLCJVU0VSX0lEIjozNiwiZXhwIjoxNTE4MTEzOTYzfQ.0VAoKc1yOzJk8pu3B-KQilGaTnxilYEOP9OcvRvRAZB2-wzekbK4F79RsXVhdbfEL6vqWVE4PeOp-LH5z-tpMA"))
                .when().post("/webapi/artist/info?artistId=36").getBody();

        LOGGER.debug(rs.prettyPrint());
    }

    class rsDto {
        private String sessionId;

        public rsDto(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }

}
