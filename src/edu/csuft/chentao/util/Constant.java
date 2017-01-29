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
	int TYPE_RETURN_MESSAGE_FAIL = 1;
	/**
	 * ����Ⱥ�ɹ�
	 */
	int TYPE_RETURN_MESSAGE_CREATE_GROUP_SUCCESS = 2;
	/**
	 * ����Ⱥʧ��
	 */
	int TYPE_RETURN_MESSAGE_CREATE_GROUP_FAIL = 3;
	/**
	 * ����ͷ��ɹ�
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_HEAD_IMAGE_SUCCESS = 4;
	/**
	 * ����ͷ��ʧ��
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_HEAD_IMAGE_FAIL = 5;
	/**
	 * ����ǩ���ɹ�
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_SIGNATURE_SUCESS = 6;
	/**
	 * ����ǩ��ʧ��
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_SIGNATURE_FAIL = 7;
	/**
	 * �����ǳƳɹ�
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_NICKNAME_SUCCESS = 8;
	/**
	 * �����ǳ�ʧ��
	 */
	int TYPE_RETURN_MESSAGE_UPDATE_NICKNAME_FAIL = 9;

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

	
	
	
	/*
	 * ��Ⱥ��������Ϣ
	 */
	/**
	 * Ⱥ��
	 */
	int TYPE_GROUP_CAPITAL_OWNER = 0;
	/**
	 * ����Ա
	 */
	int TYPE_GROUP_CAPITAL_ADMIN = 1;
	/**
	 * ��ͨ�û�
	 */
	int TYPE_GROUP_CAPITAL_USER = 2;
	
	/*
	 * �����������
	 */
	/**
	 * �����û���Ϣ
	 */
	int TYPE_USER_GROUP_INFO_USER = 0;
	/**
	 * ����Ⱥ��Ϣ
	 */
	int TYPE_USER_GROUP_INFO_GROUP = 1;
}
