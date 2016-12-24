/**
 * �ڽ���Ⱥ�󣬷��ظ�Ⱥ��Ϣ
 */
package edu.csuft.chentao.pojo.resp;

import edu.csuft.chentao.pojo.req.HeadImage;

/**
 * @author csuft.chentao
 *
 *         2016��12��11�� ����8:56:28
 */
public class GroupInfoResp {

	/** Ⱥid */
	private int groupid;
	/** Ⱥ���� */
	private String groupname;
	/** Ⱥ��ǩ */
	private String tag;
	/** Ⱥ���� */
	private int number;
	/** Ⱥͷ�� */
	private HeadImage headImage;

	public GroupInfoResp(int groupid, String groupname, String tag, int number,
			HeadImage headImage) {
		super();
		this.groupid = groupid;
		this.groupname = groupname;
		this.tag = tag;
		this.number = number;
		this.headImage = headImage;
	}

	public GroupInfoResp() {
		super();
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public HeadImage getHeadImage() {
		return headImage;
	}

	public void setHeadImage(HeadImage headImage) {
		this.headImage = headImage;
	}

}
