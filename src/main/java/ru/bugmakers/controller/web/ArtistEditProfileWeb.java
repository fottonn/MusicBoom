package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.web.ArtistEditRqWeb;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.service.ArtistProfileEditService;
import ru.bugmakers.utils.MultipartUtils;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/webapi/artist/editing")
public class ArtistEditProfileWeb extends MbController {

    private ArtistProfileEditService artistProfileEditService;

    @Autowired
    public void setArtistProfileEditService(ArtistProfileEditService artistProfileEditService) {
        this.artistProfileEditService = artistProfileEditService;
    }

    @PostMapping(value = "/personal")
    public ResponseEntity<MbResponse> artistProfileEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        @RequestBody ArtistEditRqWeb rq) {
        try {
            UserDTO userDTO = rq.getUserDTO();
            User user = userPrincipal.getUser();
            artistProfileEditService.artistProfileEdit(userDTO, user);
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/avatar.change")
    public ResponseEntity<MbResponse> changeArtistAvatar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistAvatarChange(userPrincipal.getUser(), MultipartUtils.findAvatarPart(rq));
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/phone.change")
    public ResponseEntity<MbResponse> changePhoneNumber(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistPhoneChange(userPrincipal.getUser(), rq.getPhoneNumber());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/password.change")
    public ResponseEntity<MbResponse> changePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                     @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistPasswordChange(userPrincipal.getUser(), rq.getOldPassword(), rq.getNewPassword());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/photos.delete")
    public ResponseEntity<MbResponse> deletePhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                   @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistDeletePhotos(userPrincipal.getUser(), rq.getPhotoIds());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/photos.upload")
    public ResponseEntity<MbResponse> uploadPhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                   MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistUploadPhotos(userPrincipal.getUser(), MultipartUtils.findImageParts(rq));
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }
}
