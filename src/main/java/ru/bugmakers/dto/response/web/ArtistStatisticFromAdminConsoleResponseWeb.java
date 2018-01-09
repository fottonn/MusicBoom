package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.ArtistPerformanceStatistic;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.exceptions.MbException;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistStatisticFromAdminConsoleResponseWeb extends MbResponseToWeb {

    private ArtistPerformanceStatistic artistPerformanceStatistic;

    public ArtistStatisticFromAdminConsoleResponseWeb(MbException e, RsStatus status) {
        super(e, status);
    }

    public ArtistStatisticFromAdminConsoleResponseWeb(RsStatus status) {
        super(status);
    }

    public ArtistPerformanceStatistic getArtistPerformanceStatistic() {
        return artistPerformanceStatistic;
    }

    public void setArtistPerformanceStatistic(ArtistPerformanceStatistic artistPerformanceStatistic) {
        this.artistPerformanceStatistic = artistPerformanceStatistic;
    }
}
