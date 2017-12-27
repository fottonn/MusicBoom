package ru.bugmakers.dto.response.web;

import ru.bugmakers.dto.ArtistPerformanceStatistic;
import ru.bugmakers.errors.Errors;

/**
 * Created by Ayrat on 25.12.2017.
 */
public class ArtistStatisticFromAdminConsoleResponseWeb extends CommonResponseToWeb {
    public ArtistStatisticFromAdminConsoleResponseWeb(Errors errors, String successMessage) {
        super(errors, successMessage);
    }
    private ArtistPerformanceStatistic artistPerformanceStatistic;

    public ArtistPerformanceStatistic getArtistPerformanceStatistic() {
        return artistPerformanceStatistic;
    }

    public void setArtistPerformanceStatistic(ArtistPerformanceStatistic artistPerformanceStatistic) {
        this.artistPerformanceStatistic = artistPerformanceStatistic;
    }
}
