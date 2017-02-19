/**
 * �ڽ���Ⱥ�󣬷��ظ�Ⱥ��Ϣ
 */
package edu.csuft.chentao.pojo.resp;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author csuft.chentao
 *
 *         2016��12��11�� ����8:56:28
 */
public class GroupInfoResp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ����Ⱥ������
	 */
	private int type;
	/** Ⱥid */
	private int groupid;
	/** Ⱥ���� */
	private String groupname;
	/** Ⱥ��ǩ */
	private String tag;
	/** Ⱥ���� */
	private int number;
	/** Ⱥͷ�� */
	private byte[] headImage;

	public GroupInfoResp(int type, int groupid, String groupname, String tag,
			int number, byte[] headImage) {
		super();
		this.type = type;
		this.groupid = groupid;
		this.groupname = groupname;
		this.tag = tag;
		this.number = number;
		this.headImage = headImage;
	}

	public GroupInfoResp() {
		super();
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

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "GroupInfoResp [type=" + type + ", groupid=" + groupid
				+ ", groupname=" + groupname + ", tag=" + tag + ", number="
				+ number + ", headImage=" + Arrays.toString(headImage) + "]";
	}

}
