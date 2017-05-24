package cz.tul.services;

import cz.tul.data.Tag;
import cz.tul.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {


    @Autowired
    private TagRepository tagRepository;

    public String create(Tag tag){
        Tag newTag = tagRepository.save(tag);
        return newTag.getTag();
    }

    public List<Tag> getAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }
    public Tag getTag(String id) {
        return tagRepository.findOne(id);
    }

    public boolean exists(String id){
        return tagRepository.exists(id);
    }

    public void deleteTags(){
        tagRepository.deleteAll();
    }

    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }




}
