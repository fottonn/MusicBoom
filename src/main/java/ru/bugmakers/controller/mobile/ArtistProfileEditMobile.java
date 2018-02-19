package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.MultipartFileDto;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistEditingResponseMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistProfileEditServiceMobile;

import java.io.IOException;
import java.util.List;

/**
 * Профиль юзера музыкантаа
 * Created by Ayrat on 21.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/editing")
public class ArtistProfileEditMobile extends MbController {
    //TODO переписать Get на POST.
    private ArtistProfileEditServiceMobile artistProfileEditServiceMobile;

    @Autowired
    public void setArtistProfileEditServiceMobile(ArtistProfileEditServiceMobile artistProfileEditServiceMobile) {
        this.artistProfileEditServiceMobile = artistProfileEditServiceMobile;
    }

    @PostMapping(value = "/artist.personal")
    public ResponseEntity<MbResponseToMobile> artistEditing(@RequestBody ArtistEditRequestMobile artistEditRequestMobile) {
        try {
            UserDTO userDTO = artistEditRequestMobile.getUserDTO();
            Boolean isSuccess = artistProfileEditServiceMobile.artistProfileEdit(userDTO);
            return isSuccess.equals(Boolean.TRUE) ? ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS)) :
                    ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.ERROR));
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(RsStatus.ERROR));
        }
    }

    @GetMapping(value = "/avatar.change")
    public ResponseEntity<MbResponseToMobile> changeArtistAvatar(@AuthenticationPrincipal UserPrincipal user,
                                                                 @RequestParam("image") MultipartFile file) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistAvatarChange(id, file);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        } catch (IOException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(MbException.create(MbError.CME01), RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/phone.change")
    public ResponseEntity<MbResponseToMobile> changeArtistPhone(@AuthenticationPrincipal UserPrincipal user,
                                                                @RequestParam("hash_phone_number") String phoneNumber) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistPhoneChange(id, phoneNumber);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/password.change")
    public ResponseEntity<MbResponseToMobile> changeArtistPassword(@AuthenticationPrincipal UserPrincipal user,
                                                                   @RequestParam("old_hash_password") String newPassword,
                                                                   @RequestParam("new_hash_password") String oldPassword) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistPasswordChange(id, oldPassword, newPassword);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/creativity.change")
    public ResponseEntity<MbResponseToMobile> changeArtistCreativity(@AuthenticationPrincipal UserPrincipal user,
                                                                     @RequestParam("creativity") String creativity) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistCreativityChange(id, creativity);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/instrument.change")
    public ResponseEntity<MbResponseToMobile> changeArtistInstrument(@AuthenticationPrincipal UserPrincipal user,
                                                                     @RequestParam("instrument") String instrument) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistInstrumentChange(id, instrument);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/genre.change")
    public ResponseEntity<MbResponseToMobile> changeArtistGenre(@AuthenticationPrincipal UserPrincipal user,
                                                                @RequestParam("genre") String genre) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistGenreChange(id, genre);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }

    @GetMapping(value = "/setOrderable.change")
    public ResponseEntity<MbResponseToMobile> changeArtistSetOrderable(@AuthenticationPrincipal UserPrincipal user,
                                                                       @RequestParam("set_orderable") Boolean setOrderable) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistSetOrderableChange(id, setOrderable);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));

    }

    @GetMapping(value = "/artist.deletePhotos")
    public ResponseEntity<MbResponseToMobile> artistDeletePhotos(@AuthenticationPrincipal UserPrincipal user,
                                                                 @RequestParam("photos_id") List<String> photosId) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistDeletePhotos(id, photosId);
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));

    }

    @PostMapping(value = "/artist.uploadPhotos")
    public ResponseEntity<MbResponseToMobile> artistUploadPhotos(@AuthenticationPrincipal UserPrincipal user,
                                                                 @ModelAttribute("upload_files") MultipartFileDto uploadFiles) {
        try {
            String id = user.getUser().getId().toString();
            artistProfileEditServiceMobile.artistUploadPhotos(id, uploadFiles);
        } catch (IOException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(MbException.create(MbError.CME01), RsStatus.ERROR));
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        }
        return ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS));
    }
}

