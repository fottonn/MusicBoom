package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.Photo;

import java.util.List;

/**
 * Created by Ivan
 */
public interface PhotoRepo extends JpaRepository<Photo, Long> {

    List<Photo> findAllByUserId(Long userId);

    void deleteByTitle(String title);

}
