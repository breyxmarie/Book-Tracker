package bookTracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

public class GuiLogIn extends JFrame implements ActionListener {

	// Declare components
	JLabel userLabel, passLabel;
	JTextField userField;
	JPasswordField passField;
	JButton loginButton;

	private static String baseURI = "http://localhost:8080/ProjectWebsite/rest/books";

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(baseURI);
		new GuiLogIn();
	}

	static WebTarget getWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		return client.target(baseURI);
	}

	public GuiLogIn() {

		// Set window title
		setTitle("Login Form");

		// Initialize components
		userLabel = new JLabel("Username:");
		passLabel = new JLabel("Password:");
		userField = new JTextField();
		passField = new JPasswordField();
		loginButton = new JButton("Login");

		// Set component positions and sizes
		userLabel.setBounds(50, 50, 80, 25);
		passLabel.setBounds(50, 100, 80, 25);
		userField.setBounds(140, 50, 160, 25);
		passField.setBounds(140, 100, 160, 25);
		loginButton.setBounds(140, 150, 80, 25);

		// Add components to the frame
		add(userLabel);
		add(passLabel);
		add(userField);
		add(passField);
		add(loginButton);

		// Set frame properties
		setSize(350, 250);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add action listener to the login button
		loginButton.addActionListener(this);

	}

	// Handle button clicks
	public void actionPerformed(ActionEvent e) {
		String username = userField.getText();
		String password = new String(passField.getPassword());

		WebTarget target = getWebTarget();
		String[] result = target.path("LogIn").queryParam("username", username, "password", password)
				.queryParam("password", password).request().accept(MediaType.APPLICATION_JSON).get(String[].class);

		if (result[0].equals("true")) {
			System.out.println(result[0]);

			if (result[1].equals("user")) {
				User users = new User(Integer.parseInt(result[3]), result[2], result[1]);
				UserHome homes = new UserHome(users);
				homes.setVisible(true);
				dispose();
			}

			else {
				GuiMyBooks admin = new GuiMyBooks();
				admin.setVisible(true);
				dispose();

			}
		} else {
			// System.out.println(result[0]);
			JOptionPane.showMessageDialog(this, "Invalid Username or Password");
		}

		System.out.println(Arrays.deepToString(result));

	}

}
