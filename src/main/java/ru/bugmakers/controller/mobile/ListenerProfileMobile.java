package ru.bugmakers.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.mobile.ListenerProfileRequestMobile;
import ru.bugmakers.dto.response.mobile.ArtistEditingResponseMobile;
import ru.bugmakers.dto.response.mobile.ResponseToMobile;

/**
 * Created by Ayrat on 27.11.2017.
 */
@RestController
@RequestMapping("/mapi/listener/editing/")
public class ListenerProfileMobile extends CommonController {

    @PostMapping(value = "listener.editing")
    public ResponseEntity<ResponseToMobile> editProfile(@RequestBody ListenerProfileRequestMobile listenerProfileRequestMobile) {
        ArtistEditingResponseMobile listenerEditingResponse = null;
        return ResponseEntity.ok(listenerEditingResponse);
    }

    @GetMapping(value = "attachCard")
    public ResponseEntity<ResponseToMobile> getArtist(@RequestParam("session_id") String sessionId,
                                                      @RequestParam("id") String id,
                                                      @RequestParam("is_attached") Boolean isAttached) {
        //TODO Пока не понятно какой будет ответ, необходимо изучить API

        return ResponseEntity.ok(null);
    }
}
