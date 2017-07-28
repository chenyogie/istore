package cn.itsource.istore.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * User µÃÂ¿‡
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String role;
	private int state;
	private String activecoede;
	private Timestamp updatetime;

	public User() {

	}

	public User(int id, String username, String password, String nickname, String email, String role, int state,
			String activecoede, Timestamp updatetime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
		this.state = state;
		this.activecoede = activecoede;
		this.updatetime = updatetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getActivecoede() {
		return activecoede;
	}

	public void setActivecoede(String activecoede) {
		this.activecoede = activecoede;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + ", role=" + role + ", state=" + state + ", activecoede=" + activecoede
				+ ", updatetime=" + updatetime + "]";
	}

}
