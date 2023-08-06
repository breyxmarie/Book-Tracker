package net.codejava.ws;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DbConnect {

	private static final String url = "jdbc:mysql://localhost:3306/book_tracker";
	private static final String username = "root";
	private static final String password = "admin";
	private String qry = "";
	private ResultSet rs;
	private PreparedStatement st;
	private Statement stmt = null;
	private Connection con;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/book_tracker";
	private static final String USER = "root";
	private static final String PASS = "admin";
	// private ArrayList<Book> data = new ArrayList<Book>();
	private Map<String, Book> data = new HashMap<String, Book>();

	public DbConnect() {
		try {
			Initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Initialize() throws Exception {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to database successfully");
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public byte[] convertBlob(Blob blobImage) {
		byte[] byteArray = null;

		if (blobImage == null) {
			ImageIcon icon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("default book.png"))
					.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH));

			Image img = icon.getImage();

			if (img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				throw new IllegalArgumentException("Invalid image dimensions");
			}

			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", baos);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			byteArray = baos.toByteArray();

		} else {
			try {
				byteArray = blobImage.getBytes(1, (int) blobImage.length());
				return byteArray;
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

		return byteArray;
	}

	// public List<Book> getAllRecords() {3
	public Map<String, Book> getAllRecords() {
		try {
			qry = "SELECT * FROM book_tracker.book_records";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, status));
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getToBeRead() {
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE status = 'To Be Read';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, status));
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getBooksNotInLibrary(int user_id) {
		try {
			qry = "SELECT DISTINCT book_tracker.book_records.book_id, book_tracker.book_records.title, book_tracker.book_records.author, book_tracker.book_records.pub_year,\r\n"
					+ "book_tracker.book_records.genre, book_tracker.book_records.plot, book_tracker.book_records.image \r\n"
					+ "FROM book_tracker.book_records RIGHT JOIN book_tracker.userbook_records ON \r\n"
					+ "book_tracker.book_records.book_id NOT IN (SELECT DISTINCT book_id FROM book_tracker.userbook_records WHERE book_tracker.userbook_records.user_id = '"
					+ user_id + "');";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");

				Blob blob = rs.getBlob("image");
				byte[] icon = convertBlob(blob);

				Book books = new Book();
				books.setId(bid);
				books.setTitle(title);
				books.setAuthor(author);
				books.setPubYear(pub_year);
				books.setGenre(genre);
				books.setPlot(plot);
				books.setCover(icon);

				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				// data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre,
				// plot));
				data.put(String.valueOf(bid), books);
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getBooksFromLibrary(int user_id) {
		try {
			qry = "SELECT * FROM book_tracker.book_records INNER JOIN book_tracker.userbook_records ON book_tracker.book_records.book_id \r\n"
					+ "= book_tracker.userbook_records.book_id AND book_tracker.userbook_records.user_id = " + user_id
					+ ";";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");

				Blob blob = rs.getBlob("image");
				byte[] icon = convertBlob(blob);

				Book books = new Book();
				books.setId(bid);
				books.setTitle(title);
				books.setAuthor(author);
				books.setPubYear(pub_year);
				books.setGenre(genre);
				books.setPlot(plot);
				books.setCover(icon);

				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				// data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre,
				// plot));
				data.put(String.valueOf(bid), books);
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public String[] checkLogIn(String username, String password) {
		String[] result = new String[4];

		System.out.println(username + password);
		try {
			qry = "SELECT * FROM book_tracker.user WHERE name = '" + username + "' AND password = '" + password + "';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			if (rs.next()) {
				result[0] = "true";
				result[1] = rs.getString("privilege");
				result[2] = rs.getString("name");
				result[3] = String.valueOf(rs.getInt("user_id"));
			}

			else {
				result[0] = "false";
				result[1] = "false";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return result;
	}

	public Map<String, Book> getRecordByTitle(String titles) {
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE title LIKE '%" + titles + "%';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");

				Blob blob = rs.getBlob("image");
				byte[] icon = convertBlob(blob);

				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, icon));
			}
			// return new ArrayList<Book>(data);
			System.out.println(titles);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getRecordByTitleforBooksNotInLibrary(String titles, int id) {
		try {
			qry = "SELECT DISTINCT book_tracker.book_records.book_id, book_tracker.book_records.title, book_tracker.book_records.author, book_tracker.book_records.pub_year,\r\n"
					+ "book_tracker.book_records.genre, book_tracker.book_records.plot, book_tracker.book_records.image\r\n"
					+ "FROM book_tracker.book_records RIGHT JOIN book_tracker.userbook_records ON \r\n"
					+ "book_tracker.book_records.book_id NOT IN (SELECT DISTINCT book_id FROM book_tracker.userbook_records WHERE book_tracker.userbook_records.user_id = "
					+ id + ")  AND\r\n" + " book_tracker.book_records.title LIKE '%" + titles + "%';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				// String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot));
			}
			// return new ArrayList<Book>(data);
			System.out.println(titles);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getRecordByTitleforBooksInLibrary(String titles, int id) {
		try {
			qry = "SELECT * FROM book_tracker.book_records INNER JOIN book_tracker.userbook_records ON book_tracker.book_records.book_id \r\n"
					+ "= book_tracker.userbook_records.book_id AND book_tracker.userbook_records.user_id = " + id
					+ " AND book_tracker.book_records.title LIKE '%" + titles + "%';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				// String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot));
			}
			// return new ArrayList<Book>(data);
			System.out.println(titles);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getRecordByGenre(String genres) {
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE genre = '" + genres + "';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, status));
			}
			// return new ArrayList<Book>(data);
			// System.out.println(titles);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getCurrently() {
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE status = 'Currently Reading';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, status));
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getDone() {
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE status = 'Done Reading';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);
			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				data.put(String.valueOf(bid), new Book(bid, title, author, pub_year, genre, plot, status));
			}
			// return new ArrayList<Book>(data);
			return data;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}
	}

	public Map<String, Book> getCommentsRatings(int id) {

		Map<String, Book> datas = new HashMap<String, Book>();
		try {
			qry = "SELECT book_tracker.userbook_records.user_id, name, rating, comments FROM book_tracker.userbook_records INNER JOIN book_tracker.user ON \r\n"
					+ "book_tracker.userbook_records.user_id = book_tracker.user.user_id AND book_id =" + id + ";";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);

			while (rs.next()) {
				Book books = new Book();
				books.setRatings(rs.getString("rating"));
				books.setComment(rs.getString("comments"));
				books.setTitle(rs.getString("name"));
				datas.put(Integer.toString(rs.getInt("user_id")), books);
			}
		}

		catch (SQLException e) {
			System.out.println(e);
		}

		return datas;
	}

	public Book getRecords(int id) {
		Book books = new Book();
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE book_id = " + id + "";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);

			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");
				// data.add(new Book(bid, title, author, pub_year, genre,plot, status));
				// = new Book(bid, title, author, pub_year, genre, plot, status);
				System.out.print(id);
				books.setId(id);
				books.setTitle(title);
				books.setAuthor(author);
				books.setPubYear(pub_year);
				books.setGenre(genre);
				books.setPlot(plot);
				books.setStatus(status);
			}
			// return new ArrayList<Book>(data);
			return books;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}

		// return books;
	}

	public Book getRecordsWithTitle(String id) {
		Book books = new Book();
		try {
			qry = "SELECT * FROM book_tracker.book_records WHERE title = '" + id + "';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);

			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");

				Blob blob = rs.getBlob("image");
				byte[] icon = convertBlob(blob);
				books.setCover(icon);

//				try {
//				Blob blob = rs.getBlob("image");   
//				byte[] icon = convertBlob(blob);
//				books.setCover(icon);
//				}
//				catch(Exception e) {
//					
//				}

				// System.out.print(id);
				books.setId(bid);
				books.setTitle(title);
				books.setAuthor(author);
				books.setPubYear(pub_year);
				books.setGenre(genre);
				books.setPlot(plot);
				books.setStatus(status);

				// if(icon != null) {
				// books.setCover(icon);
				// }
			}
			// return new ArrayList<Book>(data);
			return books;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}

		// return books;
	}

	public Book getUserRecordsWithTitle(String titles, int uid) {
		Book books = new Book();
		try {
			qry = "SELECT * FROM book_tracker.book_records INNER JOIN book_tracker.userbook_records ON book_tracker.book_records.book_id = book_tracker.userbook_records.book_id "
					+ "AND book_tracker.userbook_records.user_id = " + uid + " AND book_tracker.book_records.title = '"
					+ titles + "';";
			st = con.prepareStatement(qry);
			rs = st.executeQuery(qry);

			while (rs.next()) {
				int bid = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub_year = rs.getString("pub_year");
				String genre = rs.getString("genre");
				String plot = rs.getString("plot");
				String status = rs.getString("status");

				Blob blob = rs.getBlob("image");
				byte[] icon = convertBlob(blob);
				books.setCover(icon);

//				try {
//				Blob blob = rs.getBlob("image");   
//				byte[] icon = convertBlob(blob);
//				books.setCover(icon);
//				}
//				catch(Exception e) {
//					
//				}

				// System.out.print(id);
				books.setId(bid);
				books.setTitle(title);
				books.setAuthor(author);
				books.setPubYear(pub_year);
				books.setGenre(genre);
				books.setPlot(plot);
				books.setStatus(status);
				books.setStartDate(rs.getString("start_date"));
				books.setEndDate(rs.getString("end_date"));
				books.setRatings(rs.getString("rating"));
				books.setComment(rs.getString("comments"));

				// if(icon != null) {
				// books.setCover(icon);
				// }
			}
			// return new ArrayList<Book>(data);
			return books;
		} catch (SQLException e) {
			System.out.println("Error retrieving data");
			System.out.println(e);
			return null;
		}

		// return books;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	// public void addRecord(String Title,String Author, String PubYear,String
	// Genre,String Plot, String Status) {

	private static byte[] loadImageData() {
		// Load the byte[] data from your source
		// For example, reading from a file or database
		// Here, we are using a dummy data
		return new byte[] { /* your byte data here */ };
	}

	private static Image convertByteArrayToImage(byte[] imageData) {
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData)) {
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Image loadImage(String path) {
		Image image = null;
		try {
			image = ImageIO.read(DbConnect.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private static Blob convertImageToBlob(Image image, String format) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		bufferedImage.getGraphics().drawImage(image, 0, 0, null);

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(bufferedImage, format, baos);
			byte[] imageData = baos.toByteArray();

			// Create a BLOB object from the byte array
			return new javax.sql.rowset.serial.SerialBlob(imageData);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void addRecord(Book book) throws SQLException {
//		String hexString = bytesToHex(book.getCover());
//		Blob blob = con.createBlob();
//		blob.setBytes(1, book.getCover());
//		try {
//			Blob hexString = new SerialBlob(book.getCover());
//			// Now you can use the 'blob' object as needed
//			// For example, you can pass it to a JDBC PreparedStatement
//
//			// To retrieve the byte[] from the Blob object, you can use the following code:
//			byte[] retrievedBytes = hexString.getBytes(1, (int) hexString.length());
//			// 'retrievedBytes' will contain the original byte array
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		 ByteArrayInputStream inputStream = new ByteArrayInputStream(book.getCover());
//         st.setBlob(inputStream);

		byte[] imageData = loadImageData();

		// Convert byte[] to Image
		Image image = convertByteArrayToImage(book.getCover());
		// Blob blob = convertImageToBlob(image, "png");

		String qrys = "SELECT * FROM book_tracker.book_records WHERE book_id = " + book.getTitle() + "";
		try {
			String qry = "INSERT INTO book_tracker.book_records (`title`, `author`, `pub_year`, `genre`, `plot`, `status`, `image`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(qry);

			// Set the values using the appropriate methods
			st.setString(1, book.getTitle());
			st.setString(2, book.getAuthor());
			st.setString(3, book.getPubYear());
			st.setString(4, book.getGenre());
			st.setString(5, book.getPlot());
			st.setString(6, book.getStatus());

			// Create a Blob object from the byte array
			Blob blob = con.createBlob();
			blob.setBytes(1, book.getCover());

			// Set the Blob parameter
			st.setBlob(7, blob);

			// Execute the prepared statement
			st.executeUpdate();
			System.out.println("Data Insert Success");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adduserBookRecords(int user_id, int book_id) {
		// String qrys = "SELECT * FROM book_tracker.book_records WHERE book_id = " +
		// book.getTitle() + "";
		try {
			qry = "INSERT INTO book_tracker.userbook_records(user_id, book_id) VALUES (" + user_id + " , " + book_id
					+ " );";
			st = con.prepareStatement(qry);
			st.executeUpdate(qry);
			System.out.println("Data Insert Success");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateRecord(Book book) {
		// int id,String Title,String Author, String PubYear,String Genre,String Plot,
		// String Status

		try {

			qry = "UPDATE `book_tracker`.`book_records` SET" + "`title` = '" + book.getTitle() + "',"
					+ "	`author` = '" + book.getAuthor() + " '," + "	`pub_year` = '" + book.getPubYear()
					+ "', `genre` = '" + book.getGenre() + "'," + "	`plot` = '" + book.getPlot() + "',`status` = '"
					+ book.getStatus() + "' WHERE (`book_id` = '" + book.getId() + "');";
			st = con.prepareStatement(qry);
			st.executeUpdate(qry);
			System.out.println("Data Updated");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUserBookRecord(Book book, int user_id) {
		// int id,String Title,String Author, String PubYear,String Genre,String Plot,
		// String Status

		try {

			qry = "UPDATE book_tracker.userbook_records SET rating = '" + book.getRatings() + "' , comments = '"
					+ book.getComment() + "', start_date = '" + book.getStartDate() + "', end_date = '"
					+ book.getEndDate() + "',  status = '" + book.getStatus() + "' " + "WHERE user_id = " + user_id
					+ " AND book_id = " + book.getId() + " ;";
			st = con.prepareStatement(qry);
			st.executeUpdate(qry);
			System.out.println("Data Updated");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteRecord(int id) {
		qry = "DELETE FROM `book_tracker`.`book_records` WHERE (`book_id` =" + id + ");";
		try {
			st = con.prepareStatement(qry);
			st.executeUpdate(qry);
			System.out.println("Data Deleted");

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ImageIcon convertImage(Blob blobImage) {
		ImageIcon image = new ImageIcon();
		byte[] byteArray = null;
		// "/bookTracker/default book.png"
		try {
			byteArray = blobImage.getBytes(1, (int) blobImage.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (byteArray.length == 0) {
			image = new ImageIcon(getClass().getResource("/bookTracker/default book.png"));

		}

		else {
			image = new ImageIcon(byteArray);
		}

		return image;
	}

	public void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
