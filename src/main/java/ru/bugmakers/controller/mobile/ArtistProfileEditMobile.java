package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.mobile.ArtistEditRqMobile;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
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
    public ResponseEntity<MbResponseToMobile> artistEditing(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                            @RequestBody ArtistEditRqMobile rq) {
        try {
            UserDTO userDTO = rq.getUserDTO();
            User user = userPrincipal.getUser();
            artistProfileEditService.artistProfileEdit(userDTO, user);
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/avatar.change")
    public ResponseEntity<MbResponseToMobile> changeArtistAvatar(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                 MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistAvatarChange(userPrincipal.getUser(), MultipartUtils.findAvatarPart(rq));
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/phone.change")
    public ResponseEntity<MbResponseToMobile> changeArtistPhone(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistPhoneChange(userPrincipal.getUser(), rq.getPhoneNumber());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/password.change")
    public ResponseEntity<MbResponseToMobile> changeArtistPassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                   @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistPasswordChange(userPrincipal.getUser(), rq.getOldPassword(), rq.getNewPassword());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/creativity.change")
    public ResponseEntity<MbResponseToMobile> changeArtistCreativity(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                     @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistCreativityChange(userPrincipal.getUser(), rq.getCreativity());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/instrument.change")
    public ResponseEntity<MbResponseToMobile> changeArtistInstrument(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                     @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistInstrumentChange(userPrincipal.getUser(), rq.getInstrument());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/genre.change")
    public ResponseEntity<MbResponseToMobile> changeArtistGenre(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistGenreChange(userPrincipal.getUser(), rq.getGenre());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/orderable.change")
    public ResponseEntity<MbResponseToMobile> changeArtistSetOrderable(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                       @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistSetOrderableChange(userPrincipal.getUser(), rq.isOrderable());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));

    }

    @GetMapping(value = "/artist.deletePhotos")
    public ResponseEntity<MbResponseToMobile> artistDeletePhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                 @RequestBody ArtistEditRqMobile rq) {
        try {
            artistProfileEditService.artistDeletePhotos(userPrincipal.getUser(), rq.getPhotoIds());
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));

    }

    @PostMapping(value = "/artist.uploadPhotos")
    public ResponseEntity<MbResponseToMobile> artistUploadPhotos(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                 MultipartHttpServletRequest rq) {
        try {
            artistProfileEditService.artistUploadPhotos(userPrincipal.getUser(), MultipartUtils.findImageParts(rq));
        } catch (MbException e) {
            return ResponseEntity.ok(new MbResponseToMobile(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToMobile(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToMobile(RsStatus.SUCCESS));

    }
}

