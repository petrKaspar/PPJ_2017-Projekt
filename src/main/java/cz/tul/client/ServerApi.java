package cz.tul.client;

import cz.tul.data.Picture;
import retrofit.http.*;

import java.util.List;

public interface ServerApi {

	public static final String PICTURES_PATH = "/pictures";
	public static final String PICTURE_PATH = PICTURES_PATH + "/{id}";
	public static final String USERS_PATH = "/users";
	public static final String USER_PATH = USERS_PATH+ "/{username}";

	@GET(PICTURES_PATH)
	public List<Picture> showPictures();

	@Multipart
	@POST(PICTURES_PATH)
	public void addPicture(@Body Picture picture);
//
//	@GET(OFFER_PATH)
//	public Offer getOffer(@Path("id") long id);
//
//	@DELETE(OFFER_PATH)
//	public void deleteOffer(@Path("id") long id);
//
//    @GET(USERS_PATH)
//    public List<User> showUsers();
//
//	@POST(USERS_PATH)
//	public void addUser(@Body User user);
//
//	@GET(USER_PATH)
//	public User getUser(@Path("username") String username);
//
//	@DELETE(USER_PATH)
//	public void deleteUser(@Path("username") String username);
}
