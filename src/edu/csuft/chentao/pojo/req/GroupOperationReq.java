package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * Ⱥ����������Ⱥ�����˳�Ⱥ
 * 
 * @author cusft.chentao
 *
 */
public class GroupOperationReq implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ��Ⱥ�ĸ��ֲ��� */
	int type;
	/** ������Ⱥ */
	int groupId;
	/** ���������û���id */
	int userId1;
	/** ��������2����ִ���������Ⱥ����ʱ��Ҫ���� */
	int userId2;

	public GroupOperationReq(int type, int groupId, int userId1, int userId2) {
		super();
		this.type = type;
		this.groupId = groupId;
		this.userId1 = userId1;
		this.userId2 = userId2;
	}

	public GroupOperationReq() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId1() {
		return userId1;
	}

	public void setUserId1(int userId1) {
		this.userId1 = userId1;
	}

	public int getUserId2() {
		return userId2;
	}

	public void setUserId2(int userId2) {
		this.userId2 = userId2;
	}

	@Override
	public String toString() {
		return "GroupOperationReq [type=" + type + ", groupId=" + groupId
				+ ", userId1=" + userId1 + ", userId2=" + userId2 + "]";
	}

}
