package edu.csuft.chentao.pojo.req;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ���͵���Ϣ
 * 
 * @author cusft.chentao
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/** �û�id */
	private int userid;
	/** Ⱥ��id����ʾ���ĸ�Ⱥ�з��͵���Ϣ */
	private int groupid;
	/**
	 * TYPE_MSG_TEXT 0 TYPE_MSG_PIC 1
	 */
	private int typeMsg; // ��Ϣ���ݣ���������Ϣ����ͼƬ��Ϣ
	/**
	 * TYPE_MSG_SEND 0 TYPE_MSG_RECV 1
	 */
	private int type; // �Ƿ��ͻ��ǽ���
	/** ��Ϣ����ʱ�� */
	private String time;
	/** ���͵��������� */
	private String message;
	/** ���͵�ͼƬ */
	private byte[] picFile;
	/**
     * ��־λ���ж��Ƿ��ͳɹ�
     */
    private int serial_number;
	
    public Message(){}


	public Message(int userid, int groupid, int typeMsg, int type, String time,
			String message, byte[] picFile, int serial_number) {
		super();
		this.userid = userid;
		this.groupid = groupid;
		this.typeMsg = typeMsg;
		this.type = type;
		this.time = time;
		this.message = message;
		this.picFile = picFile;
		this.serial_number = serial_number;
	}


	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getTypeMsg() {
		return typeMsg;
	}

	public void setTypeMsg(int typeMsg) {
		this.typeMsg = typeMsg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getPicFile() {
		return picFile;
	}

	public void setPicFile(byte[] picFile) {
		this.picFile = picFile;
	}

	public int getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(int serial_number) {
		this.serial_number = serial_number;
	}

	@Override
	public String toString() {
		return "Message [userid=" + userid + ", groupid=" + groupid
				+ ", typeMsg=" + typeMsg + ", type=" + type + ", time=" + time
				+ ", message=" + message + ", picFile="
				+ Arrays.toString(picFile) + ", serial_number=" + serial_number
				+ "]";
	}
}
