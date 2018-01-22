package ru.bugmakers.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.mobile.ArtistEditRequestMobile;
import ru.bugmakers.dto.request.mobile.UploadPhotosRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistEditingResponseMobile;
import ru.bugmakers.dto.response.mobile.ArtistRegistrationResponse;
import ru.bugmakers.dto.response.mobile.MbResponseToMobile;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.service.ArtistProfileEditServiceMobile;

/**
 * Профиль юзера музыкантаа
 * Created by Ayrat on 21.11.2017.
 */
@RestController
@RequestMapping("/mapi/artist/editing")
public class ArtistProfileEditMobile extends MbController {

    private ArtistProfileEditServiceMobile artistProfileEditServiceMobile;

    @Autowired
    public void setArtistProfileEditServiceMobile(ArtistProfileEditServiceMobile artistProfileEditServiceMobile) {
        this.artistProfileEditServiceMobile = artistProfileEditServiceMobile;
    }

    @PostMapping(value = "/artist.personal")
    public ResponseEntity<MbResponseToMobile> artistEditing(@RequestBody ArtistEditRequestMobile artistEditRequestMobile) {
        try {
            Boolean isSuccess = artistProfileEditServiceMobile.artistProfileEdit(artistEditRequestMobile);
            return isSuccess.equals(Boolean.TRUE) ? ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.SUCCESS)) :
                    ResponseEntity.ok(new ArtistEditingResponseMobile(RsStatus.ERROR));
        } catch (MbException e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(e, RsStatus.ERROR));
        } catch (Exception e) {
            return ResponseEntity.ok(new ArtistRegistrationResponse(RsStatus.ERROR));
        }
    }

        @GetMapping(value = "/avatar.change")
        public ResponseEntity<MbResponseToMobile> changeArtistAvatar (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("image") MultipartFile file){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/phone.change")
        public ResponseEntity<MbResponseToMobile> changeArtistPhone (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_phone_number") String phoneNumber){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/password.change")
        public ResponseEntity<MbResponseToMobile> changeArtistPassword (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_password") String password){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/creativity.change")
        public ResponseEntity<MbResponseToMobile> changeArtistCreativity (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_password") String creativity){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/instrument.change")
        public ResponseEntity<MbResponseToMobile> changeArtistInstrument (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_password") String instrument){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/genre.change")
        public ResponseEntity<MbResponseToMobile> changeArtistGenre (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_password") String genre){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/setOrderable.change")
        public ResponseEntity<MbResponseToMobile> changeArtistSetOrderable (@RequestParam("session_id") String
        sessionId,
                @RequestParam("id") String id,
                @RequestParam("hash_password") Boolean setOrderable){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        @GetMapping(value = "/artist.deletePhotos")
        public ResponseEntity<MbResponseToMobile> artistDeletePhotos (@RequestParam("session_id") String sessionId,
                @RequestParam("id") String id,
                @RequestParam("photo_id") String photoId){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }

        //TODO Узнать как принимать массив фоток
        @PostMapping(value = "/artist.uploadPhotos")
        public ResponseEntity<MbResponseToMobile> artistUploadPhotos (@RequestBody UploadPhotosRequestMobile
        uploadPhotosRequestMobile){

            ArtistEditingResponseMobile artistEditingResponseMobile = null;
            return ResponseEntity.ok(artistEditingResponseMobile);
        }
    }
