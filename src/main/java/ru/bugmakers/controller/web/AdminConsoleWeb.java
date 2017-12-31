package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb extends CommonController {

    @PostMapping(value = "/getartistlist")
    public ResponseEntity<ResponseToWeb> getArtistInfo(@RequestBody ArtistListRequestWeb artistListRequestWeb) {
        ArtistInfoFromAdminConsoleResponseWeb artistInfoFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistInfoFromAdminConsoleResponseWeb);
    }

    @PostMapping(value = "/artists/artist.edit")
    public ResponseEntity<ResponseToWeb> editArtist(@RequestBody ArtistEditWebRequestWeb artistBlockRequest) {
        ArtistEditingResponseWeb artistEditingResponseWeb = null;
        return ResponseEntity.ok(artistEditingResponseWeb);
    }

    @PostMapping(value = "/artists/artist.block")
    public ResponseEntity<ResponseToWeb> blockArtist(@RequestBody ArtistBlockRequestWeb artistBlockRequestWeb) {
        ArtistBlockResponseWeb artistBlockResponseWeb = null;
        return ResponseEntity.ok(artistBlockResponseWeb);
    }

    @PostMapping(value = "/artists/artist.delete")
    public ResponseEntity<ResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequestWeb artistDeleteRequestWeb) {
        ArtistDeleteResponseWeb artistDeleteResponseWeb = null;
        return ResponseEntity.ok(artistDeleteResponseWeb);
    }

    @PostMapping(value = "/artists/sendmessage")
    public ResponseEntity<ResponseToWeb> sendMessage(@RequestBody SendMessageRequestWeb sendMessagerequestWeb) {
        SendMessageResponseMobile sendMessageResponseMobile = null;
        return ResponseEntity.ok(sendMessageResponseMobile);
    }

    @PostMapping(value = "/artists/artist.stat")
    public ResponseEntity<ResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequestWeb artistStatisticRequestWeb) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }

    @PostMapping(value = "/artists/artist.stat.period")
    public ResponseEntity<ResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequestWeb artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }


}
