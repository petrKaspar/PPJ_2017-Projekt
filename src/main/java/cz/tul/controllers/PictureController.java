package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Author;
import cz.tul.data.Picture;
import cz.tul.services.AuthorService;
import cz.tul.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Petr on 20.05.2017.
 */
@RestController
public class PictureController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = ServerApi.PICTURES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Picture>> showPictures() {
        List<Picture> picture = pictureService.getAllPictures();
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.PICTURES_PATH, method = RequestMethod.POST)
    public ResponseEntity<Picture> addPicture(@RequestBody Picture picture) {
        if (authorService.exists(picture.getAuthor().getAuthorId())) {
            pictureService.create(picture);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = ServerApi.PICTURE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Picture> getPicture(@PathVariable("id") int id) {
        if (pictureService.exists(id)) {
            Picture picture = pictureService.getPicture(id);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}