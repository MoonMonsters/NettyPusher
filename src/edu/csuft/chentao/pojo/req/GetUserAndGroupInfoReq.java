/**
 * 
 */
package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * @author csuft.chentao
 *
 * 2017��1��18�� ����3:06:17
 */
/**
 * new1.����û���Ϣ��Ⱥ��Ϣ��ָ��
 * 
 * @author csuft.chentao
 *
 *         2017��1��18�� ����3:06:51
 */
public class GetUserAndGroupInfoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ��Ҫ��������ͣ��������û���Ϣ����Ⱥ��Ϣ
	 */
	private int type;
	/**
	 * �������ݵ�id
	 */
	private int id;

	public GetUserAndGroupInfoReq(int type, int id) {
		super();
		this.type = type;
		this.id = id;
	}

	public GetUserAndGroupInfoReq() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetUserAndGroupInfoReq [type=" + type + ", id=" + id + "]";
	}

}
