package cz.tul.service;

import cz.tul.data.Autor;
import cz.tul.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Petr on 09.04.2017.
 */
@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public int create(Autor autor){
        Autor newAutor = autorRepository.save(autor);
        return newAutor.getAutor_id();
    }

    public List<Autor> getAllAutors() {
        return StreamSupport.stream(autorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public Autor getAutor(int id) {
        return autorRepository.findOne(id);
    }

}
