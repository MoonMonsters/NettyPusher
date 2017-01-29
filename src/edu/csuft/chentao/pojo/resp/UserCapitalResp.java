package edu.csuft.chentao.pojo.resp;

import java.io.Serializable;

/**
 * @author csuft.chentao
 *
 *         2017��1��20�� ����11:06:08
 */
/**
 * Я�����û�id���û������Ϣ����
 * 
 * @author csuft.chentao
 *
 *         2017��1��20�� ����11:07:22
 */
public class UserCapitalResp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * �û�id
	 */
	private int userId;
	/**
	 * ��ݱ�ʶ
	 */
	private int capital;

	public UserCapitalResp(int userId, int capital) {
		this.userId = userId;
		this.capital = capital;
	}

	public UserCapitalResp() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

}
