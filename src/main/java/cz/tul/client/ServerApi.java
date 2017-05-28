package cz.tul.client;

import cz.tul.data.Author;
import cz.tul.data.Picture;
import retrofit.client.Response;
import retrofit.http.*;
import retrofit.mime.TypedFile;

import java.util.List;

public interface ServerApi {

	public static final int DEFAULT_PAGE_NUMBER = 0;
	public static final int DEFAULT_PAGE_SIZE = 5;

	public static final String AUTHORS_PATH = "/authors";
	public static final String AUTHOR_PATH = AUTHORS_PATH + "/{id}";

	public static final String TAGS_PATH = "/tags";
	public static final String TAG_PATH = TAGS_PATH+ "/{id}";

	public static final String PICTURES_PATH = "/pictures";
	public static final String PICTURE_PATH = PICTURES_PATH + "/{id}";
	public static final String PICTURE_LIKE_PATH = PICTURE_PATH + "/addLike";
	public static final String PICTURE_DISLIKE_PATH = PICTURE_PATH + "/addDislike";
	public static final String PICTURES_DOWNLOAD_PATH = "/download/{name}";
	public static final String PICTURES_UPLOAD_PATH = "/upload/{name}";
	public static final String PICTURES_FIND_BY_TITLE_PATH = PICTURES_PATH + "/title/{title}";
	public static final String PICTURES_FIND_BY_AUTHOR_PATH = PICTURES_PATH + "/author/{authorId}";
	public static final String PICTURES_FIND_BY_TAG_PATH = PICTURES_PATH + "/tag/{tag}";

	public static final String COMMENTS_PATH = "/comments";
	public static final String COMMENT_PATH = COMMENTS_PATH + "/{id}";
	public static final String COMMENT_LIKE_PATH = COMMENT_PATH + "/addLike";
	public static final String COMMENT_DISLIKE_PATH = COMMENT_PATH + "/addDislike";

	@Multipart
	@POST(PICTURES_UPLOAD_PATH)
	public ImageStatus uploadImage(@Path("name") String name, @Part("data") TypedFile imageData);

	@Streaming
	@GET(PICTURES_DOWNLOAD_PATH)
	Response downloadImage(@Path("name") String name);


	@GET(AUTHORS_PATH)
	public List<Author> showAuthors();

	@GET(PICTURES_PATH)
	public List<Picture> showPictures();

	@Multipart
	@POST(PICTURES_PATH)
	public void addPicture(@Body Picture picture);

}
