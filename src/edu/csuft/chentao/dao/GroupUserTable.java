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
	public static final String GROUPUSERTABLE_ALL_FIELD = "groupuser(groupid,userid)";
	public static final String ID = "id";
	public static final String GROUPID = "groupid";
	public static final String USERID = "userid";

	/** id */
	private int id;
	/** Ⱥid */
	private int groupid;
	/** �û�id */
	private int userid;

	public GroupUserTable(int id, int groupid, int userid) {
		super();
		this.id = id;
		this.groupid = groupid;
		this.userid = userid;
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
}
