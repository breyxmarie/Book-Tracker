package bookTracker;

public class User {
	int userId;
	String name;
	String privilege;

	public User() {

	}

	public User(int userId, String name, String privilege) {
		this.userId = userId;
		this.name = name;
		this.privilege = privilege;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
