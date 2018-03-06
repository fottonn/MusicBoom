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
import ru.bugmakers.dto.response.web.MbResponseToWeb;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
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
    public ResponseEntity<MbResponseToWeb> artistProfileEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestBody ArtistEditRqWeb rq) {
        try {
            UserDTO userDTO = rq.getUserDTO();
            User user = userPrincipal.getUser();
            artistProfileEditService.artistProfileEdit(userDTO, user);
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/avatar.change")
    public ResponseEntity<MbResponseToWeb> changeArtistAvatar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                              MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistAvatarChange(userPrincipal.getUser(), MultipartUtils.findAvatarPart(rq));
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/phone.change")
    public ResponseEntity<MbResponseToWeb> changePhoneNumber(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistPhoneChange(userPrincipal.getUser(), rq.getPhoneNumber());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/password.change")
    public ResponseEntity<MbResponseToWeb> changePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                          @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistPasswordChange(userPrincipal.getUser(), rq.getOldPassword(), rq.getNewPassword());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/photos.delete")
    public ResponseEntity<MbResponseToWeb> deletePhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        @RequestBody ArtistEditRqWeb rq) {
        try {
            artistProfileEditService.artistDeletePhotos(userPrincipal.getUser(), rq.getPhotoIds());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/photos.upload")
    public ResponseEntity<MbResponseToWeb> uploadPhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistUploadPhotos(userPrincipal.getUser(), MultipartUtils.findImageParts(rq));
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToWeb(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }
}
