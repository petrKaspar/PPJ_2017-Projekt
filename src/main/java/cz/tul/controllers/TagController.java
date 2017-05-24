package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Tag;
import cz.tul.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = ServerApi.TAGS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> showTags() {
        List<Tag> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.TAGS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        if(tagService.exists(tag.getTag())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            tagService.create(tag);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.TAG_PATH, method = RequestMethod.GET)
    public ResponseEntity<Tag> getTag(@PathVariable("id") String id) {
        if (tagService.exists(id)) {
            Tag tag = tagService.getTag(id);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.TAG_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteTag(@PathVariable("id") String id) {
        if (tagService.exists(id)) {
            tagService.deleteTag(tagService.getTag(id));
            return new ResponseEntity("The tag with id:"+id+" was successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity("The tag with id:"+id+" does not exist!", HttpStatus.NOT_FOUND);
        }
    }


}
