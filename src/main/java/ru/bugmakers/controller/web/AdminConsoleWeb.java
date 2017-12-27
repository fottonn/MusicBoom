package ru.bugmakers.controller.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.CommonController;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.*;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb extends CommonController{
    @RequestMapping(method = RequestMethod.POST, value = "/getartistlist")
    public ResponseEntity<ResponseToWeb> getArtistInfo(@RequestBody ArtistListRequestWeb artistListRequestWeb) {
        ArtistInfoFromAdminConsoleResponseWeb artistInfoFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistInfoFromAdminConsoleResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.edit")
    public ResponseEntity<ResponseToWeb> editArtist(@RequestBody ArtistEditWebRequestWeb artistBlockRequest) {
        ArtistEditingResponseWeb artistEditingResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistEditingResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.block")
    public ResponseEntity<ResponseToWeb> blockArtist(@RequestBody ArtistBlockRequestWeb artistBlockRequestWeb) {
        ArtistBlockResponseWeb artistBlockResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistBlockResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.delete")
    public ResponseEntity<ResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequestWeb artistDeleteRequestWeb) {
        ArtistDeleteResponseWeb artistDeleteResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistDeleteResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/sendmessage")
    public ResponseEntity<ResponseToWeb> sendMessage(@RequestBody SendMessageRequestWeb sendMessagerequestWeb) {
        SendMessageResponseMobile sendMessageResponseMobile = null;
        return ResponseEntity.ok().headers(responseHeaders).body(sendMessageResponseMobile);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat")
    public ResponseEntity<ResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequestWeb artistStatisticRequestWeb) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistStatisticFromAdminConsoleResponseWeb);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/artists/artist.stat.period")
    public ResponseEntity<ResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequestWeb artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok().headers(responseHeaders).body(artistStatisticFromAdminConsoleResponseWeb);
    }






}
