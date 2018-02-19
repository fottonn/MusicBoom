package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.FeedBack;
import ru.bugmakers.repository.FeedBackRepo;

/**
 * Created by Ivan
 */
@Service
public class FeedBackService {

    private FeedBackRepo feedBackRepo;

    @Autowired
    public void setFeedBackRepo(FeedBackRepo feedBackRepo) {
        this.feedBackRepo = feedBackRepo;
    }

    public FeedBack saveFeedBack(FeedBack feedBack) {
        return feedBackRepo.saveAndFlush(feedBack);
    }

}
