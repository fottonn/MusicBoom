package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.ArtistEditWebRequestWeb;
import ru.bugmakers.dto.request.web.PhotosUploadRequestWeb;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/editing")
public class ArtistEditProfileWeb extends MbController {

    @PostMapping(value = "/personal")
    public ResponseEntity<MbResponseToWeb> artistProfileEdit(@RequestBody ArtistEditWebRequestWeb artistProfileEditing) {
        ArtistProfileEditReponseWeb artistProfileEditReponseWeb = null;
        return ResponseEntity.ok(artistProfileEditReponseWeb);
    }

    @GetMapping(value = "/avatar.change")
    public ResponseEntity<MbResponseToWeb> changeArtistAvatar(@RequestParam("session_id") String sessionId,
                                                              @RequestParam("id") String id,
                                                              @RequestParam("image") MultipartFile file) {
        ArtistAvatarEditResponseWeb artistAvatarEditResponseWeb = null;
        return ResponseEntity.ok(artistAvatarEditResponseWeb);
    }

    @GetMapping(value = "/phone.change")
    public ResponseEntity<MbResponseToWeb> changePhoneNumber(@RequestParam("session_id") String sessionId,
                                                             @RequestParam("id") String id,
                                                             @RequestParam("hash_phone_number") String phoneNumber) {
        ArtistPhoneEditResponseWeb artistPhoneEditResponseWeb = null;
        return ResponseEntity.ok(artistPhoneEditResponseWeb);
    }

    @GetMapping(value = "/password.change")
    public ResponseEntity<MbResponseToWeb> changePassword(@RequestParam("session_id") String sessionId,
                                                          @RequestParam("id") String id) {
        ArtistPasswordEditResponseWeb artistPasswordEditResponseWeb = null;
        return ResponseEntity.ok(artistPasswordEditResponseWeb);
    }

    @GetMapping(value = "/video.add")
    public ResponseEntity<MbResponseToWeb> videoAdd(@RequestParam("session_id") String sessionId,
                                                    @RequestParam("id") String id,
                                                    @RequestParam("link") String videoLink) {
        ArtistVideoAddResponseWeb artistVideoAddResponseWeb = null;
        return ResponseEntity.ok(artistVideoAddResponseWeb);
    }

    @PostMapping(value = "/photos.delete")
    public ResponseEntity<MbResponseToWeb> deletePhotos(@RequestBody PhotosUploadRequestWeb photosDeleteRequest) {
        ArtistPhotosDeleteResponseWeb artistPhotosDeleteResponseWeb = null;
        return ResponseEntity.ok(artistPhotosDeleteResponseWeb);
    }

    @PostMapping(value = "/photos.upload")
    public ResponseEntity<MbResponseToWeb> uploadPhotos(@RequestBody PhotosUploadRequestWeb photosUploadRequestWeb) {
        ArtistPhotosUploadResponseWeb artistPhotosUploadResponseWeb = null;
        return ResponseEntity.ok(artistPhotosUploadResponseWeb);
    }
}
