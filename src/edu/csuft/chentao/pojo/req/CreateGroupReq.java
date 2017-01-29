package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * 
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:49:23
 */
public class CreateGroupReq implements Serializable {

	private static final long serialVersionUID = 1L;
	/** Ⱥ�� */
	private String groupname;
	/** Ⱥ��ǩ */
	private String tag;
	/** Ⱥͷ�� */
	private byte[] headImage;
	/**
	 * ������id
	 */
	private int creatorId;

	public CreateGroupReq(String groupname, String tag, byte[] headImage,
			int creatorId) {
		this.groupname = groupname;
		this.tag = tag;
		this.headImage = headImage;
		this.creatorId = creatorId;
	}

	public CreateGroupReq() {
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

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

}
