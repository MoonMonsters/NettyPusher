/**
 * 
 */
package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * @author csuft.chentao
 *
 * 2017��1��30�� ����10:33:01
 */
/**
 * ����Ա����Ⱥ����ȺԱ���й���
 * 
 * @author csuft.chentao
 *
 *         2017��1��30�� ����10:34:19
 */
public class ManagerUserReq implements Serializable{

	private static final long serialVersionUID = 1L;
	/** �û�id */
	private int userId;
	/** Ⱥid */
	private int groupId;
	/** ������� */
	private int capital;

	public ManagerUserReq(int userId, int groupId, int capital) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.capital = capital;
	}

	public ManagerUserReq() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

}
