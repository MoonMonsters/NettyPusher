package edu.csuft.chentao.dao;

/**
 * �û���Ϣ��
 * 
 * @author csuft.chentao
 *
 */
public class UserTable {

	/** user���� */
	public static final String USERTABLE = "user";
	public static final String USERTABLE_ALL_FIELD = "user(userid,username,password,nickname,headimage,signature)";
	public static final String ID = "id";
	public static final String USERID = "userid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NICKNAME = "nickname";
	public static final String HEADIMAGE = "headimage";
	public static final String SIGNATURE = "signature";

	/** idֵ */
	private int id;
	/** �û�id */
	private int userid;
	/** �û��� */
	private String username;
	/** �û����� */
	private String password;
	/** �û��ǳ� */
	private String nickname;
	/** �û�ͷ��useridת���ɵ�md5�� */
	private String headimage;
	/** ǩ�� */
	private String signature;

	public UserTable(int id, int userid, String username, String password,
			String nickname, String headimage, String signature) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.headimage = headimage;
		this.signature = signature;
	}

	public UserTable() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
