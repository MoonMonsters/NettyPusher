/**
 * 
 */
package edu.csuft.chentao.dao;

/**
 * @author csuft.chentao
 *
 * 2017��4��16�� ����3:52:53
 */
/**
 * Announcement���ݿ⣬�����洢���еĹ�������
 */
public class AnnouncementTable {

	public static final String TABLENAME = "announcement";
	public static final String TABLE_ALL_FIELD = "announcement(serialnumber,title,content,username,time,userid,groupid)";
	public static final String ID = "id";
	public static final String SERIALNUMBER = "serialnumber";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String USERNAME = "username";
	public static final String TIME = "time";
	public static final String USERID = "userid";
	public static final String GROUPID = "groupid";

	/**
	 * �������ݿ�ı��
	 */
	private int id;
	/**
	 * ��ţ�Ԥ��������Ժ���ɾ�������õ���
	 */
	private String serialnumber;

	/**
	 * �������
	 */
	private String title;

	/**
	 * ��������
	 */
	private String content;

	/**
	 * ���������֣�����Ԥ�����֣���������û�id���û����ݿ���û������������ʹ�ø�����
	 */
	private String username;

	/**
	 * �������û�id
	 */
	private int userid;

	/**
	 * Ⱥid
	 */
	private int groupid;
	/**
     * ����ʱ��
     */
    private String time;
    
	public AnnouncementTable() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * @param serialnumber
	 *            the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	/**
	 * @param id
	 * @param serialnumber
	 * @param title
	 * @param content
	 * @param username
	 * @param userid
	 * @param groupid
	 * @param time
	 */
	public AnnouncementTable(int id, String serialnumber, String title,
			String content, String username, int userid, int groupid,
			String time) {
		super();
		this.id = id;
		this.serialnumber = serialnumber;
		this.title = title;
		this.content = content;
		this.username = username;
		this.userid = userid;
		this.groupid = groupid;
		this.time = time;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

}
