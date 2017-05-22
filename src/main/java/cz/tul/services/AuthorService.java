package cz.tul.services;

import cz.tul.data.Author;
import cz.tul.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public int create(Author author){
        Author newAuthor = authorRepository.save(author);
        return newAuthor.getAuthorId();
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }
    public Author getAuthor(int id) {
        return authorRepository.findOne(id);
    }

    public boolean exists(int id){
        return authorRepository.exists(id);
    }

    public void deleteAuthors(){
        authorRepository.deleteAll();
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

}
