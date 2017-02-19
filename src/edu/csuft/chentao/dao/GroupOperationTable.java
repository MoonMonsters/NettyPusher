/**
 * 
 */
package edu.csuft.chentao.dao;

/**
 * @author csuft.chentao
 *
 *         2017��2��7�� ����10:29:23
 */
public class GroupOperationTable {
	private int type;
	private int userId;
	private int groupId;
	private String description;
	private int readerId;

	/**
	 * �����õ�
	 */
	public static String ALLFIELD = " groupoperation(type,userId,groupId,description,readerId) ";
	public static String VALUES = " values(?,?,?,?,?) ";
	/**
	 * ������
	 */
	public static String GROUPOPERATIONTABLE = "groupoperation";
	/**
	 * id
	 */
	public static String ID = "id";
	/**
	 * ����
	 */
	public static String TYPE = "type";
	/**
	 * �û�id
	 */
	public static String USERID = "userId";
	/**
	 * Ⱥid
	 */
	public static String GROUPID = "groupId";
	/**
	 * ����
	 */
	public static String DESCRIPTION = "description";
	/**
	 * ��ȡ����Ϣ���û�id
	 */
	public static String READERID = "readerId";

	public GroupOperationTable(int type, int userId, int groupId,
			String description, int readerId) {
		super();
		this.type = type;
		this.userId = userId;
		this.groupId = groupId;
		this.description = description;
		this.readerId = readerId;
	}

	public GroupOperationTable() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
}
