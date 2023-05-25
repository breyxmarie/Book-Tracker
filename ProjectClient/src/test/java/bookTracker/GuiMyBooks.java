package bookTracker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import net.codejava.ws.Book;

@SuppressWarnings("serial")
public class GuiMyBooks extends JFrame {

	private static String baseURI = "http://localhost:8080/ProjectWebsite/rest/books";

	static WebTarget getWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		return client.target(baseURI);
	}

	private JPanel contentPane;
	private JTextField txtSearchTitle;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	final JTable tablesCurs = new JTable();

	// final JTable tablesCurs = new JTable(modelsCurs);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(baseURI);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMyBooks frame = new GuiMyBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiMyBooks() {

		WebTarget target = getWebTarget();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 789);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(229, 233, 236));
		contentPane.setBorder(new LineBorder(new Color(128, 128, 128)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 231, 76));
		panel.setBounds(0, 0, 1264, 59);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("BookTracker\r\n");
		// JLabel lblNewLabel_3 = new JLabel("BookTracker\r\n");
		lblNewLabel_3.setBounds(83, 10, 196, 42);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 32));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book.png"))
						.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_1.setBounds(22, 0, 60, 42);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_3_1 = new JLabel("Hi! USERNAME!");
		lblNewLabel_3_1.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/download.png"))
						.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(1017, 10, 206, 42);
		// panel.add(lblNewLabel_3_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode("#4F7AA3"));
		panel_1.setBounds(0, 59, 302, 691);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		final JButton btnNewButton = new JButton("Home");
		btnNewButton.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/home (1).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnNewButton.setBackground(Color.decode("#4F7AA3"));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnNewButton.setBounds(0, 74, 320, 55);
		panel_1.add(btnNewButton);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#4F7AA3"));
			}
		});

		final JButton btnMyBooks = new JButton("My Books\r\n");
		btnMyBooks.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book (1).png"))
						.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		btnMyBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnMyBooks.setBackground(Color.decode("#4F7AA3"));
		btnMyBooks.setBounds(0, 148, 320, 55);
		panel_1.add(btnMyBooks);

		btnMyBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMyBooks.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMyBooks.setBackground(Color.decode("#4F7AA3"));
			}
		});

		final JButton btnAddBooks = new JButton("Add Book\r\n\r\n");
		btnAddBooks.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book (2).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnAddBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnAddBooks.setBackground(Color.decode("#4F7AA3"));
		btnAddBooks.setBounds(0, 218, 320, 55);
		panel_1.add(btnAddBooks);

		btnAddBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddBooks.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAddBooks.setBackground(Color.decode("#4F7AA3"));
			}
		});

		//////////////////////////////// nav bar button mouse click event

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiMain mains = new GuiMain();
				setVisible(false);
				mains.setVisible(true);
			}
		});

		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiMyBooks mainss = new GuiMyBooks();
				setVisible(false);
				mainss.setVisible(true);
			}
		});

		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAddBook mainsss = new GuiAddBook();
				setVisible(false);
				mainsss.setVisible(true);
			}
		});
		////////////////////////////////////////////////////////////////

		JLabel lblNewLabel = new JLabel("BookTracker");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel.setBounds(106, 11, 205, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("MY BOOKS");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 41));
		lblNewLabel_1_1.setBounds(352, 62, 368, 53);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Search");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 41));
		lblNewLabel_1_1_1.setBounds(350, 142, 140, 53);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel FilterGenre = new JLabel("Filter Genre");
		FilterGenre.setVerticalAlignment(SwingConstants.TOP);
		FilterGenre.setHorizontalAlignment(SwingConstants.LEFT);
		FilterGenre.setFont(new Font("SansSerif", Font.BOLD, 20));
		FilterGenre.setBounds(350, 202, 190, 100);
		contentPane.add(FilterGenre);

		String[] optionsGenre = { "Romance", "Horror", "Thriller", "Fiction", "Mystery", "Drama", "Nonfiction", "Drama",
				"Poetry", "Folktale", "Novel" };

		final JComboBox comboBox = new JComboBox(optionsGenre);
		comboBox.setBounds(550, 202, 150, 30);
		contentPane.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected item from the JComboBox
				String selectedItem = (String) comboBox.getSelectedItem();

				// Do something with the selected item
				System.out.println("Selected item: " + selectedItem);

				WebTarget target = getWebTarget();

				String param = comboBox.getSelectedItem().toString();
				System.out.println(param);

				// LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle =
				// target.path("genreResult")
				// .queryParam("genre", param).request().accept(MediaType.APPLICATION_JSON)
				// .get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>()
				// {
				// });

				LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = null;
				try {
					responseMapTitle = target.path("genreResult/{genre}").resolveTemplate("genre", param).request()
							.accept(MediaType.APPLICATION_JSON)
							.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
							});
					// Create a new HashMap to hold the converted Book objects

				}
				// Loop through the responseMap and convert each entry to a Book object

				catch (Exception ds) {
					JOptionPane.showMessageDialog(null, "No Books Found");
				}

				HashMap<String, Book> bookMapTitle = new HashMap<String, Book>();
				try {

					for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapTitle.entrySet()) {
						String key = entry.getKey();
						LinkedHashMap<String, Object> valueMap = entry.getValue();
						System.out.println(entry.getValue());
						// Extract the Book data from the valueMap
						String title = (String) valueMap.get("title");
						String author = (String) valueMap.get("author");
						String pub_year = (String) valueMap.get("pubYear");
						String genre = (String) valueMap.get("genre");
						String plot = (String) valueMap.get("plot");
						String status = (String) valueMap.get("status");

						// Create a new Book object
						Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
						bookMapTitle.put(key, book);
					}
				}

				catch (Exception es) {
					// JOptionPane.showMessageDialog(null, "No Books Found");
				}

				String[] columns = { "All Books" };
				DefaultTableModel modelsTitle = new DefaultTableModel(columns, 0);
				for (Map.Entry<String, Book> entry : bookMapTitle.entrySet()) {
					String key = entry.getKey();
					Book book = entry.getValue();
					// System.out.println("Key: " + key + ", Book: " + book.getTitle());
					Object[] row = { book.getTitle() };
					modelsTitle.addRow(row);
				}

				// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

				// Create a JTable object and set its model
				// final JTable tablesCurs = new JTable(modelsTitle);

				// Add the JTable to a JScrollPane and add the scroll pane to a container
				// JScrollPane scrollPanesCur = new JScrollPane(tablesCurs);
				// scrollPanesCur.setBounds(363, 462, 343, 208);
				// contentPane.add(scrollPanesCur);

				tablesCurs.setModel(modelsTitle);

				tablesCurs.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = tablesCurs.rowAtPoint(e.getPoint());
						int col = tablesCurs.columnAtPoint(e.getPoint());
						Object value = tablesCurs.getValueAt(row, col);
						WebTarget targets = getWebTarget();
						System.out.println("Clicked cell value: " + value);
						String sampleData = value.toString();
						Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON)
								.get(Book.class);

						System.out.println(product.getAuthor());
						System.out.println(product.getPlot());

						GuiPopupBox PopUp = new GuiPopupBox(product, "pop");
						PopUp.setVisible(true);
					}
				});

			}
		});

		txtSearchTitle = new JTextField();
		txtSearchTitle.setForeground(new Color(128, 128, 128));
		txtSearchTitle.setBackground(new Color(192, 192, 192));
		txtSearchTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtSearchTitle.setText("Search Title");

		txtSearchTitle.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (txtSearchTitle.getText().equals("Search Title")) {
					txtSearchTitle.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtSearchTitle.getText().equals("")) {
					txtSearchTitle.setText("Search Title");
				}
			}
		});

		txtSearchTitle.setBounds(538, 146, 511, 42);
		contentPane.add(txtSearchTitle);
		txtSearchTitle.setColumns(10);

		JButton btnNewButton_1 = new JButton("🔎");
		btnNewButton_1.setBounds(1101, 150, 60, 42);
		contentPane.add(btnNewButton_1);

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WebTarget target = getWebTarget();

				String param = txtSearchTitle.getText();
				System.out.println(param);

				LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = target.path("titleResult")
						.queryParam("title", param).request().accept(MediaType.APPLICATION_JSON)
						.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
						});
				// Create a new HashMap to hold the converted Book objects
				HashMap<String, Book> bookMapTitle = new HashMap<String, Book>();

				// Loop through the responseMap and convert each entry to a Book object
				try {
					for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapTitle.entrySet()) {
						String key = entry.getKey();
						LinkedHashMap<String, Object> valueMap = entry.getValue();
						System.out.println(entry.getValue());
						// Extract the Book data from the valueMap
						String title = (String) valueMap.get("title");
						String author = (String) valueMap.get("author");
						String pub_year = (String) valueMap.get("pubYear");
						String genre = (String) valueMap.get("genre");
						String plot = (String) valueMap.get("plot");
						String status = (String) valueMap.get("status");

						// Create a new Book object
						Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
						bookMapTitle.put(key, book);
					}
				}

				catch (Exception es) {
					JOptionPane.showMessageDialog(null, "No Books Found");
				}

				String[] columns = { "All Books" };
				DefaultTableModel modelsTitle = new DefaultTableModel(columns, 0);
				for (Map.Entry<String, Book> entry : bookMapTitle.entrySet()) {
					String key = entry.getKey();
					Book book = entry.getValue();
					// System.out.println("Key: " + key + ", Book: " + book.getTitle());
					Object[] row = { book.getTitle() };
					modelsTitle.addRow(row);
				}

				// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

				// Create a JTable object and set its model
				// final JTable tablesCurs = new JTable(modelsTitle);

				// Add the JTable to a JScrollPane and add the scroll pane to a container
				// JScrollPane scrollPanesCur = new JScrollPane(tablesCurs);
				// scrollPanesCur.setBounds(363, 462, 343, 208);
				// contentPane.add(scrollPanesCur);

				tablesCurs.setModel(modelsTitle);

				tablesCurs.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = tablesCurs.rowAtPoint(e.getPoint());
						int col = tablesCurs.columnAtPoint(e.getPoint());
						Object value = tablesCurs.getValueAt(row, col);
						WebTarget targets = getWebTarget();
						System.out.println("Clicked cell value: " + value);
						String sampleData = value.toString();
						Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON)
								.get(Book.class);

						System.out.println(product.getAuthor());
						System.out.println(product.getPlot());

						// PopUp = new GuiPopupBox(product);
						// PopUp.setVisible(true);
					}
				});
			}
		});
		// List<Book> datas =
		// target.request().accept(MediaType.APPLICATION_JSON).get(List.class);

		/*
		 * @SuppressWarnings("unchecked") Map<Book, Object> map = (Map<Book, Object>)
		 * target.request().accept(MediaType.APPLICATION_JSON).get(List.class); int id =
		 * (int) map.get("id"); String title = (String) map.get("Title"); String author
		 * = (String) map.get("Author"); String pubYear = (String) map.get("PubYear");
		 * String genre = (String) map.get("Genre"); String plot = (String)
		 * map.get("Plot"); String status = (String) map.get("Status"); Book book = new
		 * Book(title, author, pubYear, genre, plot, status);
		 */

		// Book[] objects = (Book[]) datas.toArray();

		// Book sample;
		/*
		 * Book[] books = new Book[datas.size()]; for (int i = 0; i < datas.size(); i++)
		 * { //sample = datas.get(i); books[i] = new Book(); // or you can initialize
		 * the book object with other constructor }
		 */

		//////////////////////////////////////////// response.readEntity(new
		//////////////////////////////////////////// GenericType<Map<String, Book>>(){})
		/*
		 * LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap =
		 * target.request() .accept(MediaType.APPLICATION_JSON) .get(new
		 * GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {});
		 */

		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap = target.path("toread").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		final HashMap<String, Book> bookMap = new HashMap<String, Book>();
		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMap.entrySet()) {
			String key = entry.getKey();
			LinkedHashMap<String, Object> valueMap = entry.getValue();
			System.out.println(entry.getValue());
			// Extract the Book data from the valueMap
			String title = (String) valueMap.get("title");
			String author = (String) valueMap.get("author");
			String pub_year = (String) valueMap.get("pubYear");
			String genre = (String) valueMap.get("genre");
			String plot = (String) valueMap.get("plot");
			String status = (String) valueMap.get("status");

			// Create a new Book object
			Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
			bookMap.put(key, book);
		}
		System.out.println(bookMap.size());
		// System.out.println(bookMap.get("5").getTitle());
		// Loop through the bookMap and print the Book objects
		String[] columnNames = { "To Be Read" };
		// DefaultTableModel models = new DefaultTableModel(bookMap.size(),1);
		DefaultTableModel models = new DefaultTableModel(columnNames, 0);
		models.setColumnIdentifiers(columnNames);

		// JLabel read = new JLabel("To Be Read");
		// read.setBounds(352, 80, 243, 408);
		// contentPane.add(read);

		int i = 0;

		for (Book key : bookMap.values()) {

			// models.setValueAt(key.getTitle(), i++,0);
			// models.addRow(getComponentListeners());
			// models.addRow(new Object[]{key.getTitle()});

			// Object[] row = { key.getTitle() };
			// models.addRow(row);

			Object[] row = { key.getTitle() };
			models.addRow(row);
			JButton button = new JButton("Details");
			// models.addRow(button);
			i++;
		}

		table = new JTable(models);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		table.setModel(models);
		// table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("To be
		// Read");
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("To Be Read");
		table.setRowHeight(58);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBackground(new Color(192, 192, 192));
		table.setBounds(352, 292, 243, 408);
		// contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(352, 292, 243, 408);
		// contentPane.add(scrollPane);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				System.out.println("Clicked point: (" + e.getX() + ", " + e.getY() + ")");
				System.out.println("Row: " + row + ", Column: " + col);

				if (row >= 0 && col >= 0) {
					Object value = table.getValueAt(row, col);
					System.out.println("Selected value: " + value);
				}
			}
		});

		////////////////////////////////////
		///////////////////////////////////
		/////////////////////////////////////
		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapCurrently = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		HashMap<String, Book> bookMapCur = new HashMap<String, Book>();

		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapCurrently.entrySet()) {
			String key = entry.getKey();
			LinkedHashMap<String, Object> valueMap = entry.getValue();
			System.out.println(entry.getValue());
			// Extract the Book data from the valueMap
			String title = (String) valueMap.get("title");
			String author = (String) valueMap.get("author");
			String pub_year = (String) valueMap.get("pubYear");
			String genre = (String) valueMap.get("genre");
			String plot = (String) valueMap.get("plot");
			String status = (String) valueMap.get("status");

			// Create a new Book object
			Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
			bookMapCur.put(key, book);
		}

		// Loop through the bookMap and print the Book objects
		for (Map.Entry<String, Book> entry : bookMapCur.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());

		}

		JLabel Cread = new JLabel("To Be  Read");
		// read.setBounds(352, 80, 243, 408);
		// contentPane.add(read);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DefaultTableModel modelsCur = new DefaultTableModel(bookMapCur.size(), 1);

		int iC = 0;

		for (Book key : bookMapCur.values()) {
			modelsCur.setValueAt(key.getTitle(), iC++, 0);
			JButton button = new JButton("Details");
		}
		/////////////////////////////////////
		table_1 = new JTable();
		table_1.setModel(modelsCur);

		table_1.setRowHeight(58);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table_1.setBackground(Color.LIGHT_GRAY);
		table_1.setBounds(683, 292, 243, 408);
		// contentPane.add(table_1);

		////////////////////////////////////////
		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapDone = target.path("done").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		HashMap<String, Book> bookMapDone = new HashMap<String, Book>();

		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapDone.entrySet()) {
			String key = entry.getKey();
			LinkedHashMap<String, Object> valueMap = entry.getValue();
			System.out.println(entry.getValue());
			// Extract the Book data from the valueMap
			String title = (String) valueMap.get("title");
			String author = (String) valueMap.get("author");
			String pub_year = (String) valueMap.get("pubYear");
			String genre = (String) valueMap.get("genre");
			String plot = (String) valueMap.get("plot");
			String status = (String) valueMap.get("status");

			// Create a new Book object
			Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
			bookMapDone.put(key, book);
		}

		// Loop through the bookMap and print the Book objects
		for (Map.Entry<String, Book> entry : bookMapDone.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());

		}

		// JLabel Doneread = new JLabel("To Be Read");
		// read.setBounds(352, 80, 243, 408);
		// contentPane.add(read);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		DefaultTableModel modelsDone = new DefaultTableModel(bookMapDone.size(), 1);

		int iD = 0;

		for (Book key : bookMapDone.values()) {
			modelsDone.setValueAt(key.getTitle(), iD++, 0);

		}
		////////////////////////////////////////

		table_2 = new JTable();
		table_2.setModel(modelsDone);
		/*
		 * table_2.setModel(new DefaultTableModel( new Object[][] { {"Done Reading"},
		 * {null}, {null}, {null}, {null}, {null}, {null}, }, new String[] {
		 * "New column" } ) { Class[] columnTypes = new Class[] { String.class }; public
		 * Class getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
		 * }); table_2.getColumnModel().getColumn(0).setPreferredWidth(140);
		 * table_2.getColumnModel().getColumn(0).setMinWidth(20);
		 */
		table_2.setRowHeight(58);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table_2.setBackground(Color.LIGHT_GRAY);
		table_2.setBounds(1013, 292, 243, 408);
//		contentPane.add(table_2);

		String[] columnNamess = { "ID", "Name", "Age" };

		// Define the data
		Object[][] data = { { 1, "John", 25 }, { 2, "Mary", 30 }, { 3, "Bob", 35 }, { 4, "Alice", 28 },
				{ 5, "Tom", 40 }, { 6, "Sue", 22 } };

		// To be Read Table
		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			models.addRow(row);
		}

		DefaultTableModel modelss = new DefaultTableModel(columnNamess, 0);

		// Create a JTable object and set its model
		final JTable tables = new JTable(models);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanes = new JScrollPane(tables);
		scrollPanes.setBounds(352, 292, 243, 408);
		// contentPane.add(scrollPanes);

		tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tables.rowAtPoint(e.getPoint());
				int col = tables.columnAtPoint(e.getPoint());
				Object value = tables.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "pop");
				PopUp.setVisible(true);
			}
		});

		/////////////////////////////////////////////////

		// Currently Reading Table

		String[] columns = { "All Books" };
		DefaultTableModel modelsCurs = new DefaultTableModel(columns, 0);
		for (Map.Entry<String, Book> entry : bookMapCur.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			modelsCurs.addRow(row);
		}

		// Create a JTable object and set its model
		// final JTable tablesCurs = new JTable(modelsCurs);

		// Get the table header
		JTableHeader header = tablesCurs.getTableHeader();

		// Set the font size of the header
		Font headerFont = header.getFont();
		header.setFont(new Font(headerFont.getName(), headerFont.getStyle(), 30));

		// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

		// Create a JTable object and set its model

		tablesCurs.setModel(modelsCurs);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanesCur = new JScrollPane(tablesCurs);
		scrollPanesCur.setBounds(483, 262, 643, 408);
		contentPane.add(scrollPanesCur);

		tablesCurs.setRowHeight(58);
		// tablesDones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablesCurs.setBackground(new Color(192, 192, 192));
		tablesCurs.setBounds(352, 292, 243, 408);
		tablesCurs.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tablesCurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablesCurs.rowAtPoint(e.getPoint());
				int col = tablesCurs.columnAtPoint(e.getPoint());
				Object value = tablesCurs.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "pop");
				PopUp.setVisible(true);
			}
		});
		///////////////////////////////////////////////////////////////////////////

		// Done Reading Table

		String[] columnsDone = { "Currently Reading" };
		DefaultTableModel modelsDones = new DefaultTableModel(columnsDone, 0);
		for (Map.Entry<String, Book> entry : bookMapDone.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/default book.png"))
					.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH), book.getTitle() };
			modelsDones.addRow(row);
		}

		// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

		// Create a JTable object and set its model
		final JTable tablesDones = new JTable(modelsDones);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanesDone = new JScrollPane(tablesDones);
		scrollPanesDone.setBounds(1013, 292, 243, 408);
		// contentPane.add(scrollPanesDone);

		tablesDones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablesDones.rowAtPoint(e.getPoint());
				int col = tablesDones.columnAtPoint(e.getPoint());
				Object value = tablesDones.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "pop");
				PopUp.setVisible(true);
			}
		});

	}

	public DefaultTableModel getModel(LinkedHashMap<String, LinkedHashMap<String, Object>> response) {
		/*
		 * LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle =
		 * target .path("genreResult").queryParam("genre", param ) .request()
		 * .accept(MediaType.APPLICATION_JSON) .get(new
		 * GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {}); //
		 * Create a new HashMap to hold the converted Book objects
		 */

		DefaultTableModel models = new DefaultTableModel();
		HashMap<String, Book> bookMap = new HashMap<String, Book>();

		// Loop through the responseMap and convert each entry to a Book object

		try {

			for (Map.Entry<String, LinkedHashMap<String, Object>> entry : response.entrySet()) {
				String key = entry.getKey();
				LinkedHashMap<String, Object> valueMap = entry.getValue();
				System.out.println(entry.getValue());
				// Extract the Book data from the valueMap
				String title = (String) valueMap.get("title");
				String author = (String) valueMap.get("author");
				String pub_year = (String) valueMap.get("pubYear");
				String genre = (String) valueMap.get("genre");
				String plot = (String) valueMap.get("plot");
				String status = (String) valueMap.get("status");

				// Create a new Book object
				Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
				bookMap.put(key, book);
			}
		}

		catch (Exception es) {
			JOptionPane.showMessageDialog(null, "No Books Found");
		}

		String[] columns = { "All Books" };

		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			models.addRow(row);
		}

		return models;
	}

}
