package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.dto.response.MbResponse;

import java.util.List;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistListWebRs extends MbResponse {

    private int page;
    private int pageSize;
    private int artistCountInPage;
    private int totalPages;
    private long totalArtists;
    private List<UserDTO> artists;

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
