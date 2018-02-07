package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.dto.request.mobile.MapPerformersRequest;
import ru.bugmakers.dto.response.mobile.MapPerformersResponseMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;

/**
 * Created by Ivan
 */
@RestController
@RequestMapping("/mapi")
public class MapControllerMobile {

    @PostMapping(value = "/map.performers")
    public ResponseEntity<MbResponseToMobile> getMapPerformers(@AuthenticationPrincipal UserPrincipal principal,
                                                               @RequestBody MapPerformersRequest mapPerformersRequest) {
        MapPerformersResponseMobile mapPerformersResponseMobile = null;
        return ResponseEntity.ok(mapPerformersResponseMobile);
    }


}
