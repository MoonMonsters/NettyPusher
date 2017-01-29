/**
 * groupuser��������ݣ�Ⱥid��Ӧ�û�id
 */
package edu.csuft.chentao.dao;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:24:58
 */
public class GroupUserTable {

	public static final String GROUPUSERTABLE = "groupuser";
	public static final String GROUPUSERTABLE_ALL_FIELD = "groupuser(groupid,userid,capital)";
	public static final String ID = "id";
	public static final String GROUPID = "groupid";
	public static final String USERID = "userid";
	public static final String CAPITAL = "capital";

	/** id */
	private int id;
	/** Ⱥid */
	private int groupid;
	/** �û�id */
	private int userid;
	/**
	 * �û����
	 */
	private int capital;

	public GroupUserTable(int id, int groupid, int userid, int capital) {
		super();
		this.id = id;
		this.groupid = groupid;
		this.userid = userid;
		this.capital = capital;
	}

	public GroupUserTable() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

}
