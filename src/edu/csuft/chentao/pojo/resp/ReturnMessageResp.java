/**
 * 
 */
package edu.csuft.chentao.pojo.resp;

/**
 * @author csuft.chentao
 *
 *         2016��12��11�� ����8:52:29
 */
public class ReturnMessageResp {

	/** ���ͣ��ɹ�orʧ�� */
	private int type;
	/** �����͵����� */
	private String description;

	public ReturnMessageResp(int type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public ReturnMessageResp() {
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

}
