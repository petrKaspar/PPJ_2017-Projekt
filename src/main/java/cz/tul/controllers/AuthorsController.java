package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Author;
import cz.tul.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Petr on 20.05.2017.
 */

@RestController
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Author>> showAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.AUTHORS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if(authorService.exists(author.getAuthorId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            authorService.create(author);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
    }

}
