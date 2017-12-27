package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.PerformanceStartValidationRequestMobile;
import ru.bugmakers.dto.response.mobile.*;
import ru.bugmakers.dto.request.mobile.UpdateMapRequestArtistOrListenerMobile;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistDesktopFunctionalMobile extends CommonController {
    @RequestMapping(method = RequestMethod.POST, value = "upadateMap")
    public ResponseEntity<ResponseToMobile> updateMap(@RequestBody UpdateMapRequestArtistOrListenerMobile updateMapRequestArtistOrListenerMobile) {
        UpdateMapResponseMobile updateMapResponseMobile = null;

        return ResponseEntity.ok().headers(responseHeaders).body(updateMapResponseMobile);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.start")
    public ResponseEntity<ResponseToMobile> startPerformance(@RequestBody PerformanceStartValidationRequestMobile performanceStartValidationRequestMobile) {
        StartPerformanceResponseMobile startPerformanceResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(startPerformanceResponseMobile);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.validation")
    public ResponseEntity<ResponseToMobile> validationPerformance(@RequestBody PerformanceStartValidationRequestMobile performanceStartValidationRequestMobile) {
        ValidatePerformanceResponseMobile validatePerformanceResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(validatePerformanceResponseMobile);
    }
    @RequestMapping(method = RequestMethod.GET, value = "performance.end")
    public ResponseEntity<ResponseToMobile> endPerformane(@RequestParam("id") String id, @RequestParam("session_id") String sessionId) {
        EndPerformanceResponseMobile endPerformanceResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(endPerformanceResponseMobile);
    }
}
