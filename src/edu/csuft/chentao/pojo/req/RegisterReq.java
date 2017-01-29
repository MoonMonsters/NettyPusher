package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * ע��
 * 
 * @author cusft.chentao
 *
 */
public class RegisterReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** �û��� */
	String username;
	/** �û����� */
	String password;
	/** �û��ǳ� */
	String nickname;
	/** ͷ�� */
	byte[] headImage;
	/** ����ǩ�� */
	String signature;

	public RegisterReq(String username, String password, String nickname,
			byte[] headImage, String signature) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.headImage = headImage;
		this.signature = signature;
	}

	public RegisterReq() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
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
		return "RegisterReq [username=" + username + ", password=" + password
				+ ", nickname=" + nickname + ", headImage=" + headImage
				+ ", signature=" + signature + "]";
	}

}