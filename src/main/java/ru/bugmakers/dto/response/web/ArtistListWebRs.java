package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistListWebRs extends MbResponseToWeb {

    private int page;
    private int pageSize;
    private int artistCountInPage;
    private int totalPages;
    private long totalArtists;
    private List<UserDTO> artists;

    public ArtistListWebRs(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistListWebRs(RsStatus status) {
        super(status);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getArtistCountInPage() {
        return artistCountInPage;
    }

    public void setArtistCountInPage(int artistCountInPage) {
        this.artistCountInPage = artistCountInPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalArtists() {
        return totalArtists;
    }

    public void setTotalArtists(long totalArtists) {
        this.totalArtists = totalArtists;
    }

    public List<UserDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<UserDTO> artists) {
        this.artists = artists;
    }
}
