package ru.bugmakers.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.entity.Photo;
import ru.bugmakers.repository.PhotoRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ivan
 */
@Service
public class PhotoService {

    private PhotoRepo photoRepo;
    private ImagesService imagesService;

    @Autowired
    public void setPhotoRepo(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    public void savePhotos(Collection<Photo> photos) {
        if (CollectionUtils.isNotEmpty(photos)) {
            photoRepo.saveAll(photos);
            photoRepo.flush();
        }
    }

    public List<String> getPhotosByUserId(Long userId) {
        if (userId == null) return null;
        List<Photo> photos = photoRepo.findAllByUserId(userId);
        List<String> photosName = new ArrayList<>(photos.size());
        if (CollectionUtils.isNotEmpty(photos)) {
            photos.forEach(photo -> photosName.add(imagesService.fullImagePath(photo.getTitle())));
        }
        return photosName;
    }

    @Transactional
    public void deletePhotos(Collection<String> photos) {
        if (CollectionUtils.isNotEmpty(photos)) {
            photos.forEach(photo -> photoRepo.deleteByTitle(photo));
            photoRepo.flush();
        }
    }

}
