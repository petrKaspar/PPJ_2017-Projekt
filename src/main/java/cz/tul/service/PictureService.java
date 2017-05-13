package cz.tul.service;

import cz.tul.data.Picture;
import cz.tul.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public int create(Picture picture){
        Picture newPicture = pictureRepository.save(picture);
        return newPicture.getPictureId();
    }

    public List<Picture> getAllPictures() {
        return (List<Picture>) pictureRepository.findAll();
    }

    public Picture getPicture(int id) {
        return pictureRepository.findOne(id);
    }

    public boolean exists(int id){
        return pictureRepository.exists(id);
    }

    public boolean incrementNLike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        pictureRepository.incrementNLike(id, LocalDateTime.now());
        return true;
    }

    public boolean incrementNDislike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        pictureRepository.incrementNDisLike(id, LocalDateTime.now());
        return true;
    }

    public void deletePictures(){
        pictureRepository.deleteAll();
    }

    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }

}
