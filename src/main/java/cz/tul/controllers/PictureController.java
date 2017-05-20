package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Picture;
import cz.tul.service.AuthorService;
import cz.tul.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Petr on 20.05.2017.
 */
@RestController
public class PictureController {

    private AuthorService authorService;
    private PictureService pictureService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setAuthorService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(value = ServerApi.PICTURES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Picture>> showPictures() {
        List<Picture> picture = pictureService.getAllPictures();
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.PICTURES_PATH, method = RequestMethod.POST)
//    public ResponseEntity<Picture> addPicture(@RequestBody Picture picture, @RequestParam(value = "authorId") int idAuthor) {
    public ResponseEntity<Picture> addPicture(@RequestBody Picture picture) {
        System.out.println("************************************************");
        System.out.println(picture.toString());
        System.out.println("************************************************");

        if (authorService.exists(picture.getAuthor().getAuthorId())) {
//        if (authorService.exists(idAuthor)) {
            System.out.println("1111111111111111111111111111111111111111111");
//            picture.setAuthor(authorService.getAuthor(idAuthor));
            pictureService.create(picture);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
