package cz.tul.controllers;

import cz.tul.SpringBootRestApplication;
import cz.tul.client.FileManager;
import cz.tul.client.ImageStatus;
import cz.tul.client.ServerApi;
import cz.tul.data.Picture;
import cz.tul.services.AuthorService;
import cz.tul.services.PictureService;
import cz.tul.services.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Petr on 20.05.2017.
 */
@RestController
public class PictureController {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApplication.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private TagService tagService;

    private FileManager imageDataMgr;

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

    @RequestMapping(value = ServerApi.PICTURE_LIKE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Picture> addLike(@PathVariable("id") int id) {
        if (pictureService.exists(id)) {
            pictureService.incrementNLike(id);
            Picture picture = pictureService.getPicture(id);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.PICTURE_DISLIKE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Picture> addDisike(@PathVariable("id") int id) {
        if (pictureService.exists(id)) {
            pictureService.incrementNDislike(id);
            Picture picture = pictureService.getPicture(id);
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // vytvorim si vlastni REST endpoint
    @RequestMapping(value = ServerApi.PICTURES_UPLOAD_PATH, method = RequestMethod.POST)
    public
    // to co bude vracet
    @ResponseBody   // budu pouzivat vzdy pro Json
    ImageStatus uploadImage(@PathVariable("name") String name,
                            @RequestParam("data") MultipartFile imageData,
                            HttpServletResponse response) {

        ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);
        setFileManager();
        try {
            imageDataMgr.saveImageData(name, imageData.getInputStream());
        } catch (IOException e) {
//            e.printStackTrace();    // vypisuje to do konzole, coz je spatne, melo by se to logovat
            logger.warn(e.getMessage());
        }
        return state;
    }
    @RequestMapping(value = ServerApi.PICTURES_DOWNLOAD_PATH, method = RequestMethod.GET)
    public
    @ResponseBody
    HttpEntity<byte[]> downloadImage(@PathVariable("name") String name, HttpServletResponse response) {
        byte[] image = new byte[0];
        HttpHeaders headers = new HttpHeaders();

        setFileManager();
        if (imageDataMgr.imageExists(name)) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                imageDataMgr.copyImageData(name, bos);
                image = bos.toByteArray();
                headers.setContentLength(image.length);
                String mime = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
                headers.setContentType(MediaType.valueOf(mime)); //or what ever type it is
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return new HttpEntity<byte[]>(image, headers);
    }

    public void setFileManager() {
        try {
            imageDataMgr = FileManager.get();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    @RequestMapping(value = ServerApi.PICTURES_FIND_BY_TITLE_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Picture>>  findByTitle(@PathVariable("title") String title) {
        List<Picture> picture = pictureService.getPicturesByTitle(title);
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.PICTURES_FIND_BY_AUTHOR_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Picture>>  findByTag(@PathVariable("authorId") int authorId) {
        List<Picture> picture = pictureService.getPicturesByAuthor(authorService.getAuthor(authorId));
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.PICTURES_FIND_BY_TAG_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Picture>>  findByTag(@PathVariable("tag") String tag) {
        List<Picture> picture = pictureService.getPicturesByTag(tagService.getTag(tag));
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.PICTURE_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deletePicture (@PathVariable("id") int id) {
        if (pictureService.exists(id)) {
            pictureService.deletePicture(pictureService.getPicture(id));
            return new ResponseEntity("The picture with id:"+id+" was successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity("The picture with id:"+id+" does not exist!", HttpStatus.NOT_FOUND);
        }
    }

}
