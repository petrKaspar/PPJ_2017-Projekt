package cz.tul.client;

import cz.tul.data.Author;
import cz.tul.data.Picture;
import retrofit.http.*;

import java.util.List;

public interface ServerApi {

	public static final int DEFAULT_PAGE_NUMBER = 0;
	public static final int DEFAULT_PAGE_SIZE = 5;

	public static final String AUTHORS_PATH = "/authors";
	public static final String AUTHOR_PATH = AUTHORS_PATH + "/{id}";

	public static final String PICTURES_PATH = "/pictures";
	public static final String PICTURE_PATH = PICTURES_PATH + "/{id}";
	public static final String PICTURE_LIKE_PATH = PICTURE_PATH + "/addLike";
	public static final String PICTURE_DISLIKE_PATH = PICTURE_PATH + "/addDislike";

	public static final String COMMENTS_PATH = "/comments";
	public static final String COMMENT_PATH = COMMENTS_PATH + "/{id}";
	public static final String COMMENT_LIKE_PATH = COMMENT_PATH + "/addLike";
	public static final String COMMENT_DISLIKE_PATH = COMMENT_PATH + "/addDislike";


	@GET(AUTHORS_PATH)
	public List<Author> showAuthors();

	@GET(PICTURES_PATH)
	public List<Picture> showPictures();

	@Multipart
	@POST(PICTURES_PATH)
	public void addPicture(@Body Picture picture);


}
