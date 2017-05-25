package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.Tag;
import cz.tul.services.TagService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Petr on 25.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootRestApplication.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Before
    public void init() {
        tagService.deleteTags();
    }

    @Test
    public void create() throws Exception {

        Tag tag = new Tag("budova");

        tagService.create(tag);
        List<Tag> tagList = tagService.getAllTags();

        assertEquals("Should be one tag.", 1, tagList.size());
        assertEquals("Retrieved tag should equal inserted tag name.", tag.getTag(),
                tagList.get(0).getTag());

    }

    @Test
    public void getAllTags() throws Exception {
        tagService.create(new Tag("auto"));
        tagService.create(new Tag("zvire"));
        tagService.create(new Tag("mesto"));
        tagService.create(new Tag("strom"));

        List<Tag> tagListBefore = tagService.getAllTags();

        assertTrue("Size of tagList should be bigger or equal 4", tagListBefore.size() >= 4);
    }

    @Test
    public void getTag() throws Exception {
        Tag tag = new Tag("osoba");
        String key = tagService.create(tag);
        assertEquals("Retrieved tag id should equal inserted tag id.", key, tagService.getTag(key).getTag());

    }

    @Test
    public void exists() throws Exception {
        tagService.deleteTags();

        Tag tag = new Tag("celebrita");
        String key = tagService.create(tag);

        assertTrue("Tag should exist with id " + key, tagService.exists(tag.getTag()));
        assertFalse("Tag should not exist", tagService.exists("modelka"));
    }

    @Test
    public void deleteTags() throws Exception {
        tagService.create(new Tag("auto"));
        tagService.create(new Tag("zvire"));
        tagService.create(new Tag("mesto"));
        tagService.create(new Tag("strom"));

        List<Tag> tagListBefore = tagService.getAllTags();
        tagService.deleteTags();
        List<Tag> tagListAfter = tagService.getAllTags();

        assertTrue("Size of tagList should be bigger or equal 4", tagListBefore.size() >= 4);
        assertTrue("TagList should be empty", tagListAfter.isEmpty());
    }

    @Test
    public void deleteTag() throws Exception {
        tagService.deleteTags();

        tagService.create(new Tag("auto"));
        tagService.create(new Tag("zvire"));
        tagService.create(new Tag("mesto"));
        tagService.create(new Tag("strom"));

        List<Tag> tagList = tagService.getAllTags();
        assertEquals("Should be four tags.", 4, tagList.size());

        tagService.deleteTag(tagList.get(0));
        tagService.deleteTag(tagList.get(1));

        tagList = tagService.getAllTags();
        assertEquals("Should be two tags.", 2, tagList.size());
    }

}