package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * Ⱥ����������Ⱥ�����˳�Ⱥ
 * 
 * @author cusft.chentao
 *
 */
public class GroupOperationReq implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * TYPE_GROUP_ENTER 0 ����Ⱥ 
	 * TYPE_GROUP_EXIT 1 �˳�Ⱥ
	 */
	int type; // ������������������˳�
	/** ������Ⱥ */
	int groupid;
	/** ���������û���id */
	int userid;

	public GroupOperationReq(int type, int groupid, int userid) {
		super();
		this.type = type;
		this.groupid = groupid;
		this.userid = userid;
	}

	public GroupOperationReq() {
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "GroupOperationReq [type=" + type + ", groupid=" + groupid
				+ ", userid=" + userid + "]";
	}

}
