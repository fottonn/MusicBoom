package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.PerformanceStartValidationRequest;
import ru.bugmakers.dto.response.mobile.*;
import ru.bugmakers.dto.request.mobile.UpdateMapRequestArtistOrListener;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistDesktopFunctionalMobile {
    @RequestMapping(method = RequestMethod.POST, value = "upadateMap")
    public ResponseEntity<ResponseToMobile> updateMap(@RequestBody UpdateMapRequestArtistOrListener updateMapRequestArtistOrListener) {
        HttpHeaders responseHeaders = new HttpHeaders();
        UpdateMapResponseMobile updateMapResponseMobile = null;

        return new ResponseEntity<ResponseToMobile>(updateMapResponseMobile, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.start")
    public ResponseEntity<ResponseToMobile> startPerformance(@RequestBody PerformanceStartValidationRequest performanceStartValidationRequest) {
        StartPerformanceResponseMobile startPerformanceResponseMobile = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(startPerformanceResponseMobile, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.validation")
    public ResponseEntity<ResponseToMobile> validationPerformance(@RequestBody PerformanceStartValidationRequest performanceStartValidationRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        ValidatePerformanceResponseMobile validatePerformanceResponseMobile = null;
        return new ResponseEntity<ResponseToMobile>(validatePerformanceResponseMobile, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "performance.end")
    public ResponseEntity<ResponseToMobile> endPerformane(@RequestParam("id") String id, @RequestParam("session_id") String sessionId) {
        EndPerformanceResponseMobile endPerformanceResponseMobile = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(endPerformanceResponseMobile, responseHeaders, HttpStatus.OK);
    }
}
