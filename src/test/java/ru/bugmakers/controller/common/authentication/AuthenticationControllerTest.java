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

    @Test
    public void testArtistInfo() throws Exception {
        ResponseBody rs = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new rsDto("eyJhbGciOiJIUzUxMiJ9.eyJUT0tFTl9DUkVBVEVfREFURSI6MTUxNzg0NTUxNDA0MywiVVNFUk5BTUUiOiIrNzk2MDA1NzE2MDAiLCJleHAiOjE1MTgwMTgzMTR9.tjFSP-dvFxzh06w39tN1C6k9WvjWYsJN-5k21NKQKSFamYXY02TPxCJaGC7QsZNhZKGAe4M6yG-wX56A5eIVRQ"))
                .when().post("/webapi/artist/info?artistId=35").getBody();

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
