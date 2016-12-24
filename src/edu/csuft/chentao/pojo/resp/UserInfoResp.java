package edu.csuft.chentao.pojo.resp;

import java.io.Serializable;

import edu.csuft.chentao.pojo.req.HeadImage;

/**
 * �û���Ϣ��������Ⱥʱ�����߸���Ⱥ��ϵ��ʱ������˴洢�û����ݲ����͵��ͻ���
 * 
 * @author cusft.chentao
 *
 */
public class UserInfoResp implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ���ͣ����Զ���¼�������µ�¼ */
	private int type;
	/** �û���idֵ */
	private int userid;
	/** �û��ǳ� */
	private String nickname;
	/** ͷ�� */
	private HeadImage headImage;
	/** ǩ�� */
	private String signature;

	public UserInfoResp(int type, int userid, String nickname,
			HeadImage headImage, String signature) {
		super();
		this.type = type;
		this.userid = userid;
		this.nickname = nickname;
		this.headImage = headImage;
		this.signature = signature;
	}

	public UserInfoResp() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public HeadImage getHeadImage() {
		return headImage;
	}

	public void setHeadImage(HeadImage headImage) {
		this.headImage = headImage;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "UserInfoResp [type=" + type + ", userid=" + userid
				+ ", nickname=" + nickname + ", headImage=" + headImage
				+ ", signature=" + signature + "]";
	}

}
