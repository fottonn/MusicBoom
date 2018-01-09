package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.PerformanceStartValidationRequestMobile;
import ru.bugmakers.dto.request.mobile.UpdateMapRequestArtistOrListenerMobile;
import ru.bugmakers.dto.response.mobile.*;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistDesktopFunctionalMobile extends MbController {

    @PostMapping(value = "updateMap")
    public ResponseEntity<MbResponseToMobile> updateMap(@RequestBody UpdateMapRequestArtistOrListenerMobile updateMapRequestArtistOrListenerMobile) {
        UpdateMapResponseMobile updateMapResponseMobile = null;
        return ResponseEntity.ok(updateMapResponseMobile);
    }

    @PostMapping(value = "performance.start")
    public ResponseEntity<MbResponseToMobile> startPerformance(@RequestBody PerformanceStartValidationRequestMobile performanceStartValidationRequestMobile) {
        StartPerformanceResponseMobile startPerformanceResponseMobile = null;
        return ResponseEntity.ok(startPerformanceResponseMobile);
    }

    @PostMapping(value = "performance.validation")
    public ResponseEntity<MbResponseToMobile> validationPerformance(@RequestBody PerformanceStartValidationRequestMobile performanceStartValidationRequestMobile) {
        ValidatePerformanceResponseMobile validatePerformanceResponseMobile = null;
        return ResponseEntity.ok(validatePerformanceResponseMobile);
    }

    @GetMapping(value = "performance.end")
    public ResponseEntity<MbResponseToMobile> endPerformane(@RequestParam("id") String id, @RequestParam("session_id") String sessionId) {
        EndPerformanceResponseMobile endPerformanceResponseMobile = null;
        return ResponseEntity.ok(endPerformanceResponseMobile);
    }
}
