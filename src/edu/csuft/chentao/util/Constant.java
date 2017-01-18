/**
 * 
 */
package edu.csuft.chentao.util;

/**
 * @author csuft.chentao
 *
 *         2016��12��10�� ����10:22:54
 */
public interface Constant {

	/** Ĭ���û�idֵ����10000��ʼ */
	int DEFAULT_USERID = 10000;
	/** Ĭ��Ⱥid��20000��ʼ */
	int DEFAULT_GROUPID = 100000;

	/** ע��ʧ�ܣ��û����ظ� */
	int TYPE_REGISTER_REPEAT_USERNAME = 0;
	/** ע��ɹ� */
	int TYPE_REGISTER_SUCCESS = 1;

	/** ����Ⱥ�ɹ� */
	int CREATE_GROUP_SUCCESS = 0;
	/** ����Ⱥʧ�� */
	int CREATE_GROUP_FAIL = 1;

	/** ����Ⱥ */
	int TYPE_GROUP_ENTER = 0;
	/** �˳�Ⱥ */
	int TYPE_GROUP_EXIT = 1;

	/** �ɹ� */
	int TYPE_RETURN_MESSAGE_SUCCESS = 0;
	/** ʧ�� */
	int TYPE_RETURN_MESSAGE_FAIL = 3;

	/** �����ǳ� */
	int TYPE_UPDATE_NICKNAME = 0;
	/** ����ǩ�� */
	int TYPE_UPDATE_SIGNATURE = 1;
	/** ����ͷ�� */
	int TYPE_UPDATE_HEADIMAGE = 2;

	/** �Զ���¼ */
	int TYPE_LOGIN_AUTO = 0;
	/** �µĵ�¼ */
	int TYPE_LOGIN_NEW = 1;
	/** �����û���Ϣ */
	int TYPE_LOGIN_USER_INFO = 2;

	/** ������Ϣ */
	int TYPE_MSG_SEND = 0;
	/** ������Ϣ */
	int TYPE_MSG_RECV = 1;

	String PATH = "./headimage";

}
