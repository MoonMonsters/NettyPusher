package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

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
	/** �û��ǳ� */
	String nickname;
	/** ͷ��ֱ�Ӹ���idֵ�����ݱ��ж�ȡ���ڿͻ����У����û�д洢��ͼƬ����ӷ����ȡ�� */
	String headimage;
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
	PicFile picFile;

	public Message(int userid, String nickname, String headimage,
			int groupid, int typeMsg, int type, String time, String message,
			PicFile picFile) {
		super();
		this.userid = userid;
		this.nickname = nickname;
		this.headimage = headimage;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImageMd5() {
		return headimage;
	}

	public void setHeadImageMd5(String headimage) {
		this.headimage = headimage;
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

	public PicFile getPicFile() {
		return picFile;
	}

	public void setPicFile(PicFile picFile) {
		this.picFile = picFile;
	}

	@Override
	public String toString() {
		return "MessageReq [userid=" + userid + ", nickname=" + nickname
				+ ", headimage=" + headimage + ", groupid=" + groupid
				+ ", typeMsg=" + typeMsg + ", type=" + type + ", time=" + time
				+ ", message=" + message + ", picFile=" + picFile + "]";
	}

}
