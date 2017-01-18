/**
 * group���������
 */
package edu.csuft.chentao.dao;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:19:35
 */
public class GroupTable {

	/** group���� */
	public static final String GROUPTABLE = "chatgroup";
	public static final String GROUPTABLE_ALL_FIELD = "chatgroup(groupid,groupname,tag,number)";
	public static final String ID = "id";
	public static final String GROUPID = "groupid";
	public static final String GROUPNAME = "groupname";
	public static final String TAG = "tag";
	/** ���� */
	public static final String NUMBER = "number";

	/** id */
	private int id;
	/** Ⱥid */
	private int groupid;
	/** Ⱥ���� */
	private String groupname;
	/** Ⱥ��ǩ */
	private String tag;
	/** ���� */
	private int number;

	public GroupTable(int id, int groupid, String groupname, String tag,
			int number) {
		super();
		this.id = id;
		this.groupid = groupid;
		this.groupname = groupname;
		this.tag = tag;
		this.number = number;
	}

	public GroupTable() {
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

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
