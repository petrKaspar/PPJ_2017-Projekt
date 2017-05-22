package cz.tul.client;

import cz.tul.data.Author;
import cz.tul.data.Picture;
import retrofit.http.*;

import java.util.List;

public interface ServerApi {

	public static final String AUTHORS_PATH = "/authors";
	public static final String AUTHOR_PATH = AUTHORS_PATH + "/{id}";
	public static final String PICTURES_PATH = "/pictures";
	public static final String PICTURE_PATH = PICTURES_PATH + "/{id}";
	public static final String COMMENTS_PATH = "/comments";
	public static final String COMMENT_PATH = COMMENTS_PATH + "/{id}";

	@GET(AUTHORS_PATH)
	public List<Author> showAuthors();

	@GET(PICTURES_PATH)
	public List<Picture> showPictures();

	@Multipart
	@POST(PICTURES_PATH)
	public void addPicture(@Body Picture picture);


}
