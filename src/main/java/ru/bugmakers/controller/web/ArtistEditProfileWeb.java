package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.request.web.ArtistEditWebRequest;
import ru.bugmakers.dto.request.web.PhotosUploadRequest;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistEditProfileWeb {
    @RequestMapping(method = RequestMethod.POST, value = "editing/artist.personal")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody ArtistEditWebRequest artistProfileEditing) {
        ArtistProfileEditReponseWeb artistProfileEditReponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistProfileEditReponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/avatar.change")
    public ResponseEntity<ResponseToWeb> changeArtistAvatar(@RequestParam("session_id") String sessionId,
                                                            @RequestParam("id") String id,
                                                            @RequestParam("image") MultipartFile file) {
        ArtistAvatarEditResponseWeb artistAvatarEditResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistAvatarEditResponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/phone.change")
    public ResponseEntity<ResponseToWeb> changePhoneNumber(@RequestParam("session_id") String sessionId,
                                                           @RequestParam("id") String id,
                                                           @RequestParam("hash_phone_number") String phoneNumber) {
        ArtistPhoneEditResponseWeb artistPhoneEditResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistPhoneEditResponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/password.change")
    public ResponseEntity<ResponseToWeb> changePassword(@RequestParam("session_id") String sessionId,
                                                        @RequestParam("id") String id) {
        ArtistPasswordEditResponseWeb artistPasswordEditResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistPasswordEditResponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/video.add")
    public ResponseEntity<ResponseToWeb> videoAdd(@RequestParam("session_id") String sessionId,
                                                  @RequestParam("id") String id,
                                                  @RequestParam("link") String videoLink) {
        ArtistVideoAddResponseWeb artistVideoAddResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistVideoAddResponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.delete")
    public ResponseEntity<ResponseToWeb> deletePhotos(@RequestBody PhotosUploadRequest photosDeleteRequest) {
        ArtistPhotosDeleteResponseWeb artistPhotosDeleteResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistPhotosDeleteResponseWeb, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.upload")
    public ResponseEntity<ResponseToWeb> uploadPhotos(@RequestBody PhotosUploadRequest photosUploadRequest) {
        ArtistPhotosUploadResponseWeb artistPhotosUploadResponseWeb = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseToWeb>(artistPhotosUploadResponseWeb, responseHeaders, HttpStatus.OK);
    }
}
