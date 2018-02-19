package ru.bugmakers.controller.common.registration;

import io.restassured.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.RegistrationRequest;

import static io.restassured.RestAssured.given;

/**
 * Created by Ivan
 */
public class RegistrationControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControllerTest.class);

    @Test
    public void testRegister() {
        RegistrationRequest rq = new RegistrationRequest();
        rq.setUser(new UserDTO()
                .withPhoneNumber("+79612345678")
                .withPassword("password")
        );
        ResponseBody rs = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(rq)
                .when().post("/registration?user_type=artist").getBody();

        LOGGER.debug(rs.prettyPrint());

    }
}
