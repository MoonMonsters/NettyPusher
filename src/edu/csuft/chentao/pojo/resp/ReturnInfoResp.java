/**
 * 
 */
package edu.csuft.chentao.pojo.resp;

import java.io.Serializable;

/**
 * @author csuft.chentao
 *
 *         2016��12��11�� ����8:52:29
 */
public class ReturnInfoResp implements Serializable{

	private static final long serialVersionUID = 1L;
	/** ���ͣ��ɹ�orʧ�� */
	private int type;
	/** �����͵����� */
	private String description;

	public ReturnInfoResp(int type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public ReturnInfoResp() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ReturnMessageResp [type=" + type + ", description="
				+ description + "]";
	}

}
