package ru.bugmakers.controller.mobile;

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
import ru.bugmakers.dto.request.mobile.ArtistEditRqMobile;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.service.ArtistProfileEditService;
import ru.bugmakers.utils.MultipartUtils;

/**
 * Профиль юзера музыкантаа
 * Created by Ayrat on 21.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/editing")
public class ArtistProfileEditMobile extends MbController {

    private ArtistProfileEditService artistProfileEditService;

    @Autowired
    public void setArtistProfileEditService(ArtistProfileEditService artistProfileEditService) {
        this.artistProfileEditService = artistProfileEditService;
    }

    @PostMapping(value = "/personal")
    public ResponseEntity<MbResponse> artistEditing(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                    @RequestBody ArtistEditRqMobile rq) {
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

    @PostMapping(value = "/password.change")
    public ResponseEntity<MbResponse> changeArtistPassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                           @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistPasswordChange(userPrincipal.getUser(), rq.getOldPassword(), rq.getNewPassword());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/creativity.change")
    public ResponseEntity<MbResponse> changeArtistCreativity(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistCreativityChange(userPrincipal.getUser(), rq.getCreativity());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/instrument.change")
    public ResponseEntity<MbResponse> changeArtistInstrument(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistInstrumentChange(userPrincipal.getUser(), rq.getInstrument());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/genre.change")
    public ResponseEntity<MbResponse> changeArtistGenre(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                        @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistGenreChange(userPrincipal.getUser(), rq.getGenre());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());
    }

    @PostMapping(value = "/orderable.change")
    public ResponseEntity<MbResponse> changeArtistSetOrderable(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                               @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistSetOrderableChange(userPrincipal.getUser(), rq.isOrderable());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());

    }

    @PostMapping(value = "/photos.delete")
    public ResponseEntity<MbResponse> artistDeletePhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistDeletePhotos(userPrincipal.getUser(), rq.getPhotoIds());
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());

    }

    @PostMapping(value = "/photos.upload")
    public ResponseEntity<MbResponse> artistUploadPhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                         MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistUploadPhotos(userPrincipal.getUser(), MultipartUtils.findImageParts(rq));
        } catch (Exception e) {
            return ResponseEntity.ok(MbResponse.error(e));
        }
        return ResponseEntity.ok(MbResponse.success());

    }
}

