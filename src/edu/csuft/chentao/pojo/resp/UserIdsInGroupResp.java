package edu.csuft.chentao.pojo.resp;

import java.io.Serializable;
import java.util.List;

/**
 * new1.��Ⱥ�����е��û�id������Ӧ�����
 * 
 * @author csuft.chentao
 *
 *         2017��1��18�� ����3:12:38
 */
public class UserIdsInGroupResp implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ��Ӧ��Ⱥ��id
	 */
	private int groupId;
	/**
	 * ��Ⱥ�������û���id������Ӧ�����
	 */
	private List<UserCapitalResp> userIdCapitalList;

	public UserIdsInGroupResp(int groupId,
			List<UserCapitalResp> userIdCapitalList) {
		super();
		this.groupId = groupId;
		this.userIdCapitalList = userIdCapitalList;
	}

	public UserIdsInGroupResp() {
		super();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<UserCapitalResp> getUserIdCapitalList() {
		return userIdCapitalList;
	}

	public void setUserIdCapitalList(List<UserCapitalResp> userIdCapitalList) {
		this.userIdCapitalList = userIdCapitalList;
	}

}
