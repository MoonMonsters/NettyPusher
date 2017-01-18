package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * 
 * @author csuft.chentao
 *
 * 2016��12��8�� ����9:49:23
 */
public class CreateGrourpReq implements Serializable{

	private static final long serialVersionUID = 1L;
	/** Ⱥ�� */
	private String groupname;
	/** Ⱥ��ǩ */
	private String tag;
	/** Ⱥͷ�� */
	private byte[] headImage;

	public CreateGrourpReq(String groupname, String tag, byte[] headImage) {
		super();
		this.groupname = groupname;
		this.tag = tag;
		this.headImage = headImage;
	}

	public CreateGrourpReq() {
		super();
	}

	public final String getGroupname() {
		return groupname;
	}

	public final void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public final String getTag() {
		return tag;
	}

	public final void setTag(String tag) {
		this.tag = tag;
	}

	public final byte[] getHeadImage() {
		return headImage;
	}

	public final void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "CreateGrourpReq [groupname=" + groupname + ", tag=" + tag
				+ ", headImage=" + headImage + "]";
	}

}
