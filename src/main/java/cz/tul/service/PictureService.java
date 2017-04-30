package cz.tul.service;

import cz.tul.data.Picture;
import cz.tul.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Petr on 09.04.2017.
 */
@Service
@Transactional
public class PictureService {


    @Autowired
    private PictureRepository pictureRepository;

    public int create(Picture picture){
        Picture newPicture = pictureRepository.save(picture);
        return newPicture.getPicture_id();
    }

    public List<Picture> getAllPictures() {
        return StreamSupport.stream(pictureRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Picture getPicture(int id) {
        return pictureRepository.findOne(id);
    }

    public boolean incrementNLike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        pictureRepository.incrementNLike(id, localDateTime.toString());
        return true;
    }

    public boolean incrementNDislike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        pictureRepository.incrementNDisLike(id, localDateTime.toString());
        return true;
    }

}
