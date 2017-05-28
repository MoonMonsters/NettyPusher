/**
 * 
 */
package edu.csuft.chentao.dao;

/**
 * @author csuft.chentao
 *
 * 2017��4��16�� ����4:41:13
 */
public class AnnouncementReader {

	public static final String TABLE_NAME = "announcement_reader";
	public static final String TABLE_ALL_FIELD = "announcement_reader(serial_number,user_id)";
	public static final String ID = "id";
	public static final String SERIAL_NUMBER = "serial_number";
	public static final String USER_ID = "user_id";
	
	/**
	 * ������
	 */
	private int id;
	/**
	 * ����
	 */
	private String serialnumber;
	/**
	 * �û�id
	 */
	private int userid;
	/**
	 * @param id
	 * @param serialnumber
	 * @param userid
	 */
	public AnnouncementReader(int id, String serialnumber, int userid) {
		super();
		this.id = id;
		this.serialnumber = serialnumber;
		this.userid = userid;
	}
	/**
	 * 
	 */
	public AnnouncementReader() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
