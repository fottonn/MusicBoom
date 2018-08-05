package ru.bugmakers.localpers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.localpers.entity.WhiteToken;
import ru.bugmakers.localpers.repository.WhiteTokenRepo;

import java.util.Optional;

/**
 * Created by Ivan
 */
@Service
public class WhiteTokenService {

    private WhiteTokenRepo whiteTokenRepo;

    @Autowired
    public void setWhiteTokenRepo(WhiteTokenRepo whiteTokenRepo) {
        this.whiteTokenRepo = whiteTokenRepo;
    }

    public String findWhiteTokenByUserId(Long id) {
        Optional<WhiteToken> optional = whiteTokenRepo.findById(id);
        WhiteToken whiteToken = optional.orElse(null);
        return whiteToken != null ? whiteToken.getToken() : null;
    }

    @Transactional("jpaLocalTransactionManager")
    public WhiteToken saveWhiteToken(WhiteToken whiteToken) {
        return whiteTokenRepo.saveAndFlush(whiteToken);
    }

    @Transactional("jpaLocalTransactionManager")
    public void deleteWhiteToken(String token) {
        whiteTokenRepo.deleteWhiteTokenByToken(token);
    }
}
