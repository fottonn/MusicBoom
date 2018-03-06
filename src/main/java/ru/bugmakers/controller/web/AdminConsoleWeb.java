package ru.bugmakers.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb extends MbController {

    @PostMapping(value = "/getartistlist")
    public ResponseEntity<MbResponseToWeb> getArtistInfo(@RequestBody ArtistListRequestWeb artistListRequestWeb) {
        ArtistInfoFromAdminConsoleResponseWeb artistInfoFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistInfoFromAdminConsoleResponseWeb);
    }

    @PostMapping(value = "/artists/artist.edit")
    public ResponseEntity<MbResponseToWeb> editArtist(@RequestBody ArtistEditRqWeb artistBlockRequest) {
        ArtistEditingResponseWeb artistEditingResponseWeb = null;
        return ResponseEntity.ok(artistEditingResponseWeb);
    }

    @PostMapping(value = "/artists/artist.block")
    public ResponseEntity<MbResponseToWeb> blockArtist(@RequestBody ArtistBlockRequestWeb artistBlockRequestWeb) {
        ArtistBlockResponseWeb artistBlockResponseWeb = null;
        return ResponseEntity.ok(artistBlockResponseWeb);
    }

    @PostMapping(value = "/artists/artist.delete")
    public ResponseEntity<MbResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequestWeb artistDeleteRequestWeb) {
        ArtistDeleteResponseWeb artistDeleteResponseWeb = null;
        return ResponseEntity.ok(artistDeleteResponseWeb);
    }

    @PostMapping(value = "/artists/sendmessage")
    public ResponseEntity<MbResponseToWeb> sendMessage(@RequestBody SendMessageRequestWeb sendMessagerequestWeb) {
        SendMessageResponseMobile sendMessageResponseMobile = null;
        return ResponseEntity.ok(sendMessageResponseMobile);
    }

    @PostMapping(value = "/artists/artist.stat")
    public ResponseEntity<MbResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequestWeb artistStatisticRequestWeb) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }

    @PostMapping(value = "/artists/artist.stat.period")
    public ResponseEntity<MbResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequestWeb artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }


}
