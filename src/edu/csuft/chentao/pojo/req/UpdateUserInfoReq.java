package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * �����û���Ϣ
 * 
 * @author cusft.chentao
 *
 */
public class UpdateUserInfoReq implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** ������Ϣ�û���idֵ */
	int userid;
	/**
	 * TYPE_UPDATE_NICKNAME 0 �����û��ǳ� 
	 * TYPE_UPDATE_SIGNATURE 1 �����û�ǩ��
	 * TYPE_UPDATE_HEADIMAGE 2 �����û�ͷ��
	 */
	int type; // ������Ϣ������
	/** ���µ�ֵ���������ǳƻ���ǩ�� */
	String content;
	/** ���µ�ͷ�� */
	byte[] headImage;

	public UpdateUserInfoReq(int userid, int type, String content,
			byte[] headImage) {
		super();
		this.userid = userid;
		this.type = type;
		this.content = content;
		this.headImage = headImage;
	}

	public UpdateUserInfoReq() {
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "UpdateInfoReq [userid=" + userid + ", type=" + type
				+ ", content=" + content + ", headImage=" + headImage + "]";
	}

}
