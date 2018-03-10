package ru.bugmakers.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bugmakers.controller.MbController;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.request.web.*;
import ru.bugmakers.dto.response.web.*;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;
import ru.bugmakers.service.UserService;

/**
 * Created by Ayrat on 06.12.2017.
 */
@RestController
@RequestMapping("/webapi/admin")
public class AdminConsoleWeb extends MbController {

    private UserService userService;
    private UserDTO2UserEnricher userDTO2UserEnricher;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDTO2UserEnricher(UserDTO2UserEnricher userDTO2UserEnricher) {
        this.userDTO2UserEnricher = userDTO2UserEnricher;
    }

    @PostMapping(value = "/artist.list")
    public ResponseEntity<MbResponseToWeb> getArtistList(@RequestBody ArtistListWebRq rq) {
        ArtistListWebRs rs;
        try {
            int page = Integer.parseInt(rq.getPage()) - 1;
            int size = Integer.parseInt(rq.getSize());
            Page<UserDTO> artistsPage = userService.findAllUsersByUserType(UserType.ARTIST, PageRequest.of(page, size, Sort.by("id")));
            rs = new ArtistListWebRs(RsStatus.SUCCESS);
            rs.setArtists(artistsPage.getContent());
            rs.setPage(artistsPage.getNumber() + 1);
            rs.setPageSize(artistsPage.getSize());
            rs.setArtistCountInPage(artistsPage.getNumberOfElements());
            rs.setTotalPages(artistsPage.getTotalPages());
            rs.setTotalArtists(artistsPage.getTotalElements());
        } catch (Exception e) {
            rs = new ArtistListWebRs(RsStatus.ERROR);
        }
        return ResponseEntity.ok(rs);
    }

    @PostMapping(value = "/artist.edit")
    public ResponseEntity<MbResponseToWeb> editArtist(@RequestBody ArtistEditWebRq rq) {
        try {
            User editedArtist = userService.findUserById(Long.valueOf(rq.getArtist().getId()));
            userDTO2UserEnricher.enrich(rq.getArtist(), editedArtist);
            userService.updateUser(editedArtist);
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/artist.block")
    public ResponseEntity<MbResponseToWeb> blockArtist(@RequestBody ArtistBlockWebRq rq) {

        try {
            User blockedArtist = userService.findUserById(Long.valueOf(rq.getArtistId()));
            blockedArtist.setEnabled(!rq.getBlock());
            userService.updateUser(blockedArtist);
        } catch (Exception e) {
            return ResponseEntity.ok(new MbResponseToWeb(RsStatus.ERROR));
        }
        return ResponseEntity.ok(new MbResponseToWeb(RsStatus.SUCCESS));
    }

    @PostMapping(value = "/artist.delete")
    public ResponseEntity<MbResponseToWeb> deleteArtist(@RequestBody ArtistDeleteRequestWeb artistDeleteRequestWeb) {
        ArtistDeleteResponseWeb artistDeleteResponseWeb = null;
        return ResponseEntity.ok(artistDeleteResponseWeb);
    }

    @PostMapping(value = "/message.send")
    public ResponseEntity<MbResponseToWeb> sendMessage(@RequestBody SendMessageRequestWeb sendMessagerequestWeb) {
        SendMessageResponseMobile sendMessageResponseMobile = null;
        return ResponseEntity.ok(sendMessageResponseMobile);
    }

    @PostMapping(value = "/artist.stat")
    public ResponseEntity<MbResponseToWeb> getArtistStatistic(@RequestBody ArtistStatisticRequestWeb artistStatisticRequestWeb) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }

    @PostMapping(value = "/artist.stat.period")
    public ResponseEntity<MbResponseToWeb> getArtistStatisticWithPeriod(@RequestBody ArtistStatisticWithPeriodRequestWeb artistStatisticRequest) {
        ArtistStatisticFromAdminConsoleResponseWeb artistStatisticFromAdminConsoleResponseWeb = null;
        return ResponseEntity.ok(artistStatisticFromAdminConsoleResponseWeb);
    }


}
