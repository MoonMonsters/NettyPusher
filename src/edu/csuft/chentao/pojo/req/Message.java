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
	int userid;
	/** Ⱥ��id����ʾ���ĸ�Ⱥ�з��͵���Ϣ */
	int groupid;
	/**
	 * TYPE_MSG_TEXT 0 TYPE_MSG_PIC 1
	 */
	int typeMsg; // ��Ϣ���ݣ���������Ϣ����ͼƬ��Ϣ
	/**
	 * TYPE_MSG_SEND 0 TYPE_MSG_RECV 1
	 */
	int type; // �Ƿ��ͻ��ǽ���
	/** ��Ϣ����ʱ�� */
	String time;
	/** ���͵��������� */
	String message;
	/** ���͵�ͼƬ */
	byte[] picFile;

	public Message(int userid, int groupid, int typeMsg, int type, String time,
			String message, byte[] picFile) {
		super();
		this.userid = userid;
		this.groupid = groupid;
		this.typeMsg = typeMsg;
		this.type = type;
		this.time = time;
		this.message = message;
		this.picFile = picFile;
	}

	public Message() {
		super();
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

	@Override
	public String toString() {
		return "Message [userid=" + userid + ", groupid=" + groupid
				+ ", typeMsg=" + typeMsg + ", type=" + type + ", time=" + time
				+ ", message=" + message + ", picFile="
				+ Arrays.toString(picFile) + "]";
	}

}
