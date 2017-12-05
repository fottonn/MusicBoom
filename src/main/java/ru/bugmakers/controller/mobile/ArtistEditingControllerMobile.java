package ru.bugmakers.controller.mobile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.request.mobile.ArtistEditRequest;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 21.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/editing")
public class ArtistEditingControllerMobile {
    @RequestMapping(method = RequestMethod.POST, value = "/artist.personal")
    public ResponseEntity<ResponseToMobile> artistEditi(@RequestBody ArtistEditRequest artistEditRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/avatar.change")
    public ResponseEntity<ResponseToMobile> changeArtistAvatar(@RequestParam("session_id") String sessionId,
                                                               @RequestParam("id") String id,
                                                               @RequestParam("image") MultipartFile file) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/phone.change")
    public ResponseEntity<ResponseToMobile> changeArtistPhone(@RequestParam("session_id") String sessionId,
                                                              @RequestParam("id") String id,
                                                              @RequestParam("hash_phone_number") String phoneNumber) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/password.change")
    public ResponseEntity<ResponseToMobile> changeArtistPassword(@RequestParam("session_id") String sessionId,
                                                                 @RequestParam("id") String id,
                                                                 @RequestParam("hash_password") String password) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/creativity.change")
    public ResponseEntity<ResponseToMobile> changeArtistCreativity(@RequestParam("session_id") String sessionId,
                                                                   @RequestParam("id") String id,
                                                                   @RequestParam("hash_password") String creativity) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/instrument.change")
    public ResponseEntity<ResponseToMobile> changeArtistInstrument(@RequestParam("session_id") String sessionId,
                                                                   @RequestParam("id") String id,
                                                                   @RequestParam("hash_password") String instrument) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/genre.change")
    public ResponseEntity<ResponseToMobile> changeArtistGenre(@RequestParam("session_id") String sessionId,
                                                              @RequestParam("id") String id,
                                                              @RequestParam("hash_password") String genre) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/setOrderable.change")
    public ResponseEntity<ResponseToMobile> changeArtistSetOrderable (@RequestParam("session_id") String sessionId,
                                                              @RequestParam("id") String id,
                                                              @RequestParam("hash_password") Boolean setOrderable) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/artist.deletePhotos")
    public ResponseEntity<ResponseToMobile> artistDeletePhotos (@RequestParam("session_id") String sessionId,
                                                              @RequestParam("id") String id,
                                                              @RequestParam("photo_id") String photoId) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }

    //TODO Узнать как принимать массив фоток
    @RequestMapping(method = RequestMethod.POST, value = "/artist.uploadPhotos")
    public ResponseEntity<ResponseToMobile> artistUploadPhotos (@RequestParam("session_id") String sessionId,
                                                               @RequestParam("id") String id,
                                                               @RequestParam("image") MultipartFile file) {
         HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToMobile>(null, responseHeaders, HttpStatus.OK);
    }


}
