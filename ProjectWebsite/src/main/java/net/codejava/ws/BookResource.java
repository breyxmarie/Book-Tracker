package net.codejava.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books")
public class BookResource {

	// private DbConnect cons = new DbConnect();
	private DbConnect cons = new DbConnect();

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public List<Book> lists() { List<Book>
	 * listProducts = cons.getAllRecords();
	 * 
	 * return cons.getAllRecords(); }
	 */

	@GET
	@Path("/LogIn")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response CheckLogsIn(@QueryParam("username") String username, @QueryParam("password") String password) {
		// List<Book> listProducts = cons.getAllRecords();
		String[] results = cons.checkLogIn(username, password);

		// if (authenticate.length) {
		// return Response.noContent().build();
		// }
		return Response.ok(results).build();
	}

	@GET
	@Path("/RecommendBooks")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapRecommendBooks(@QueryParam("userId") int user_id) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getBooksNotInLibrary(user_id);

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/Library/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapLibrary(@PathParam("user_id") int user_id) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getBooksFromLibrary(user_id);

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/titleResult")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapTitleRecords(@QueryParam("title") String title) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getRecordByTitle(title);

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/titleResultNotInLibrary/{title}/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	// public Response MapTitleRecordsFromnooksNotLibrary(@QueryParam("title")
	// String title, @QueryParam("user_id") int uid) {
	public Response MapTitleRecordsFromnooksNotLibrary(@PathParam("title") String title,
			@PathParam("user_id") int uid) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getRecordByTitleforBooksNotInLibrary(title, uid);

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/titleResultInLibrary/{title}/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	// public Response MapTitleRecordsFromnooksNotLibrary(@QueryParam("title")
	// String title, @QueryParam("user_id") int uid) {
	public Response MapTitleRecordsFromBooksLibrary(@PathParam("title") String title, @PathParam("user_id") int uid) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getRecordByTitleforBooksInLibrary(title, uid);

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/genreResult/{genre}")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapGenreRecords(@PathParam("genre") String genre) {
		// public Response MapGenreRecords(@QueryParam("genre") String genre) {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getRecordByGenre(genre);

		if (listProducts.isEmpty()) {
			// return Response.noContent().build();

			return Response.noContent().status(Response.Status.NOT_FOUND).build();
		}

		// return Response.ok(listProducts).build();
		return Response.ok(listProducts).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response Map() {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getAllRecords();

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}

		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/toread")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapToBeRead() {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getToBeRead();

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}

		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/currently")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapCurrently() {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getCurrently();

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}

		return Response.ok(listProducts).build();
	}

	@GET
	@Path("/rate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRatingComment(@QueryParam("book_id") int id) {
		// Book product = cons.getRecords(id);
		Map<String, Book> ratings = cons.getCommentsRatings(id);

		if (ratings.isEmpty()) {
			return Response.noContent().build();
		}

		return Response.ok(ratings).build();
	}

	@GET
	@Path("/done")
	@Produces(MediaType.APPLICATION_JSON)
	// public Response list() {
	public Response MapDone() {
		// List<Book> listProducts = cons.getAllRecords();
		Map<String, Book> listProducts = cons.getDone();

		if (listProducts.isEmpty()) {
			return Response.noContent().build();
		}

		return Response.ok(listProducts).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		// Book product = cons.getRecords(id);
		Book product = cons.getRecordsWithTitle(id);
		if (product != null) {
			return Response.ok(product, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/userBooksDeets/{user_id}/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("user_id") int uid, @PathParam("title") String title) {
		// Book product = cons.getRecords(id);
		Book product = cons.getUserRecordsWithTitle(title, uid);
		if (product != null) {
			return Response.ok(product, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Book product) throws URISyntaxException {
		// int newProductId = cons.addRecord(product);;
		cons.addRecord(product);
		Book bid = cons.getRecordsWithTitle(product.getTitle());
		URI uri = new URI("/books/" + bid.getId());
		System.out.println(uri);
		// URI uri = new URI("/products/" );
		return Response.created(uri).build();
	}

	@POST
	@Path("/users/{user_id}/books/{book_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adduserBook(@PathParam("user_id") int user_id, @PathParam("book_id") int book_id)
			throws URISyntaxException {
		// int newProductId = cons.addRecord(product);;
		cons.adduserBookRecords(user_id, book_id);
		// Book bid = cons.getRecordsWithTitle(product.getTitle());
		URI uri = new URI("/books/");
		// System.out.println(uri);
		// URI uri = new URI("/products/" );
		return Response.created(uri).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") int id, Book product) {
		product.setId(id);
		if (cons.updateRecord(product)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/{user_id}")
	public Response updateUserBooks(@PathParam("user_id") int user_id, Book book) {
		// book.setId(user_id);
		if (cons.updateUserBookRecord(book, user_id)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		if (cons.deleteRecord(id)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
}
