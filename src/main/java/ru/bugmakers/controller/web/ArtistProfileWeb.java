package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.request.web.ArtistProfileEditingRequest;
import ru.bugmakers.dto.request.web.PhotosDeleteRequest;
import ru.bugmakers.dto.request.web.PhotosUploadRequest;
import ru.bugmakers.dto.response.web.ResponseToWeb;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistProfileWeb {
    @RequestMapping(method = RequestMethod.POST, value = "editing/artist.personal")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody ArtistProfileEditingRequest artistProfileEditing) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/avatar.change")
    public ResponseEntity<ResponseToWeb> changeArtistAvatar(@RequestParam("session_id") String sessionId,
                                                            @RequestParam("id") String id,
                                                            @RequestParam("image") MultipartFile file) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/phone.change")
    public ResponseEntity<ResponseToWeb> changePhoneNumber(@RequestParam("session_id") String sessionId,
                                                           @RequestParam("id") String id,
                                                           @RequestParam("hash_phone_number") String phoneNumber) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/password.change")
    public ResponseEntity<ResponseToWeb> changePassword(@RequestParam("session_id") String sessionId,
                                                        @RequestParam("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/video.add")
    public ResponseEntity<ResponseToWeb> videoAdd(@RequestParam("session_id") String sessionId,
                                                  @RequestParam("id") String id,
                                                  @RequestParam("link") String videoLink) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.delete")
    public ResponseEntity<ResponseToWeb> deletePhotos(@RequestBody PhotosDeleteRequest photosDeleteRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.upload")
    public ResponseEntity<ResponseToWeb> deletePhotos(@RequestBody PhotosUploadRequest photosUploadRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(null, responseHeaders, HttpStatus.OK);
    }
}
