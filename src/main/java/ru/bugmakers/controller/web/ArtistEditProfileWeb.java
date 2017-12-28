package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.ArtistEditWebRequestWeb;
import ru.bugmakers.dto.request.web.PhotosUploadRequestWeb;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/")
public class ArtistEditProfileWeb extends CommonController {

    @RequestMapping(method = RequestMethod.POST, value = "editing/artist.personal")
    public ResponseEntity<ResponseToWeb> artistProfileEdit(@RequestBody ArtistEditWebRequestWeb artistProfileEditing) {
        ArtistProfileEditReponseWeb artistProfileEditReponseWeb = null;
        return ResponseEntity.ok(artistProfileEditReponseWeb);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/avatar.change")
    public ResponseEntity<ResponseToWeb> changeArtistAvatar(@RequestParam("session_id") String sessionId,
                                                            @RequestParam("id") String id,
                                                            @RequestParam("image") MultipartFile file) {
        ArtistAvatarEditResponseWeb artistAvatarEditResponseWeb = null;
        return ResponseEntity.ok(artistAvatarEditResponseWeb);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/phone.change")
    public ResponseEntity<ResponseToWeb> changePhoneNumber(@RequestParam("session_id") String sessionId,
                                                           @RequestParam("id") String id,
                                                           @RequestParam("hash_phone_number") String phoneNumber) {
        ArtistPhoneEditResponseWeb artistPhoneEditResponseWeb = null;
        return ResponseEntity.ok(artistPhoneEditResponseWeb);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/password.change")
    public ResponseEntity<ResponseToWeb> changePassword(@RequestParam("session_id") String sessionId,
                                                        @RequestParam("id") String id) {
        ArtistPasswordEditResponseWeb artistPasswordEditResponseWeb = null;
        return ResponseEntity.ok(artistPasswordEditResponseWeb);
    }

    @RequestMapping(method = RequestMethod.GET, value = "editing/video.add")
    public ResponseEntity<ResponseToWeb> videoAdd(@RequestParam("session_id") String sessionId,
                                                  @RequestParam("id") String id,
                                                  @RequestParam("link") String videoLink) {
        ArtistVideoAddResponseWeb artistVideoAddResponseWeb = null;
        return ResponseEntity.ok(artistVideoAddResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.delete")
    public ResponseEntity<ResponseToWeb> deletePhotos(@RequestBody PhotosUploadRequestWeb photosDeleteRequest) {
        ArtistPhotosDeleteResponseWeb artistPhotosDeleteResponseWeb = null;
        return ResponseEntity.ok(artistPhotosDeleteResponseWeb);
    }

    @RequestMapping(method = RequestMethod.POST, value = "editing/photos.upload")
    public ResponseEntity<ResponseToWeb> uploadPhotos(@RequestBody PhotosUploadRequestWeb photosUploadRequestWeb) {
        ArtistPhotosUploadResponseWeb artistPhotosUploadResponseWeb = null;
        return ResponseEntity.ok(artistPhotosUploadResponseWeb);
    }
}
