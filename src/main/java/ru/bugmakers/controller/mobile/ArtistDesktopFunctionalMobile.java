package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.dto.request.mobile.PerformaneStartValidationDtoRequest;
import ru.bugmakers.dto.response.ResponseToMobile;
import ru.bugmakers.dto.request.mobile.UpdateMapDtoRequest;

/**
 * Created by Ayrat on 24.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/")
public class ArtistDesktopFunctionalMobile {
    @RequestMapping(method = RequestMethod.POST, value = "upadateMap")
    public ResponseEntity<ResponseToMobile> updateMap(@RequestBody UpdateMapDtoRequest updateMapDtoRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.start")
    public ResponseEntity<ResponseToMobile> startPerformane(@RequestBody PerformaneStartValidationDtoRequest performaneStartValidationDtoRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "performance.validation")
    public ResponseEntity<ResponseToMobile> validationPerformane(@RequestBody PerformaneStartValidationDtoRequest performaneStartValidationDtoRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "performance.end")
    public ResponseEntity<ResponseToMobile> endPerformane(@RequestParam("id") String id, @RequestParam("session_id") String sessionId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
}
