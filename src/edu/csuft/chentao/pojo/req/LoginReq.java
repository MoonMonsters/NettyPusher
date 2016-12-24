package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * ��¼��Ϣ
 * 
 * @author cusft.chentao
 *
 */
public class LoginReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** �û��� */
	String username;
	/** ���� */
	String password;
	/**
	 * ���ͣ��ֳ����֣�һ�����Զ���¼��һ�������¿�ʼ��¼ �Զ���¼�Ļ���ֻ���ص�¼�ɹ���־ ���µ�¼����Ҫ�ѹ����û������ݷ��ع�ȥ
	 */
	/**
	 * TYPE_AUTOLOGIN 0 
	 * TYPE_RESTARTLOGIN 1
	 */
	int type;

	public LoginReq(String username, String password, int type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public LoginReq() {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LoginReq [username=" + username + ", password=" + password
				+ ", type=" + type + "]";
	}

}
