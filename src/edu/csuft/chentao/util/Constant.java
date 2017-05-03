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
	int TYPE_RETURN_INFO_SUCCESS = 0;
	/** ʧ�� */
	int TYPE_RETURN_INFO_FAIL = 1;
	/**
	 * ����Ⱥ�ɹ�
	 */
	int TYPE_RETURN_INFO_CREATE_GROUP_SUCCESS = 2;
	/**
	 * ����Ⱥʧ��
	 */
	int TYPE_RETURN_INFO_CREATE_GROUP_FAIL = 3;
	/**
	 * ����ͷ��ɹ�
	 */
	int TYPE_RETURN_INFO_UPDATE_HEAD_IMAGE_SUCCESS = 4;
	/**
	 * ����ͷ��ʧ��
	 */
	int TYPE_RETURN_INFO_UPDATE_HEAD_IMAGE_FAIL = 5;
	/**
	 * ����ǩ���ɹ�
	 */
	int TYPE_RETURN_INFO_UPDATE_SIGNATURE_SUCESS = 6;
	/**
	 * ����ǩ��ʧ��
	 */
	int TYPE_RETURN_INFO_UPDATE_SIGNATURE_FAIL = 7;
	/**
	 * �����ǳƳɹ�
	 */
	int TYPE_RETURN_INFO_UPDATE_NICKNAME_SUCCESS = 8;
	/**
	 * �����ǳ�ʧ��
	 */
	int TYPE_RETURN_INFO_UPDATE_NICKNAME_FAIL = 9;
	/**
	 * �����û���ݳɹ�
	 */
	int TYPE_RETURN_INFO_UPDATE_USER_CAPITAL_SUCCESS = 10;
	/**
	 * �����û����ʧ��
	 */
	int TYPE_RETURN_INFO_UPDATE_USER_CAPITAL_FAIL = 11;
	/**
	 * ����Ⱥʱ�����ݸ���Ϊ0
	 */
	int TYPE_RETURN_INFO_SEARCH_GROUP_SIZE_0 = 12;

	/**
	 * �˳�Ⱥʧ��
	 */
	int TYPE_RETURN_INFO_EXIT_GROUP_FAIL = 13;

	/*
	 * new
	 */
	/**
	 * ����Ա���û��߳�Ⱥ�ɹ�
	 */
	int TYPE_RETURN_INFO_REMOVE_USER_SUCCESS = 14;
	/**
	 * ����Ա���û��߳�Ⱥʧ��
	 */
	int TYPE_RETURN_INFO_REMOVE_USER_FAIL = 15;
	/**
	 * �������Ⱥ��Ⱥ������
	 */
	int TYPE_RETURN_INFO_GROUP_NOT_EXIST = 16;
	/**
	 * �������Ⱥ���û��Ѿ���Ⱥ�д���
	 */
	int TYPE_RETURN_INFO_GROUP_MUL_USER = 17;
	/**
	 * ������û�id
	 */
	int TYPE_RETURN_INFO_ERROR_USERID = 18;
	/**
	 * �����û�����Ⱥ������ɹ�
	 */
	int TYPE_RETURN_INFO_INVITE_SUCCESS = 19;
	/**
	 * �����û�ʱ���û��Ѿ���Ⱥ��ظ�����
	 */
	int TYPE_RETURN_INFO_INVITE_REPEAT = 20;
	/**
	 * �ļ��б�����Ϊ0
	 */
	int TYPE_RETURN_INFO_FILE_LIST_SIZE_0 = 21;

	/**
	 * ��ȡ�û�����
	 */
	int TYPE_GET_INFO_USERINFO = 0;
	/**
	 * �˳���¼
	 */
	int TYPE_GET_INFO_UNLOGIN = 1;
	/**
	 * ����Ⱥid����
	 */
	int TYPE_GET_INFO_SEARCH_GROUP_ID = 2;
	/**
	 * ����Ⱥ������
	 */
	int TYPE_GET_INFO_SEARCH_GROUP_NAME = 3;
	/**
	 * ���ݱ�ǩ����
	 */
	int TYPE_GET_INFO_SEARCH_GROUP_TAG = 4;
	/**
	 * �õ�Ⱥ�����е��ļ��б�
	 */
	int TYPE_GET_INFO_GROUP_FILE_LIST = 5;
	/**
	 * �����ļ�
	 */
	int TYPE_GET_INFO_DOWNLOAD_FILE = 6;
	
	/**
	 * �û�ӵ�е�Ⱥ
	 */
	int TYPE_GROUP_INFO_OWNER = 0;
	/**
	 * �û�����ʱ��ȡ��Ⱥ
	 */
	int TYPE_GROUP_INFO_SEARCH = 1;

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

	/*
	 * Ⱥ����
	 */
	/**
	 * �Լ��˳�Ⱥ
	 */
	int TYPE_GROUP_OPERATION_EXIT_BY_MYSELF = 1;
	/**
	 * ������Ա�߳�Ⱥ
	 */
	int TYPE_GROUP_OPERATION_EXIT_BY_ADMIN = 2;
	/**
	 * �Լ�����Ⱥ
	 */
	int TYPE_GROUP_OPERATION_ADD_BY_MYSELF = 3;
	/**
	 * ���������Ⱥ
	 */
	int TYPE_GROUP_OPERATION_ADD_BY_INVITE = 4;
	/**
	 * ͬ�����Ⱥ
	 */
	int TYPE_GROUP_OPERATION_AGREE_ADD_GROUP = 5;
	/**
	 * �ܾ�����Ⱥ
	 */
	int TYPE_GROUP_OPERATION_REFUSE_ADD_GROUP = 6;

	/*
	 * Ⱥ��Ϣ��Ӧ
	 */
	// 1.�˳�Ⱥ
	int TYPE_GROUP_REMINDER_EXIT_BY_MYSELF = 0;
	// 2.�߳�Ⱥ
	int TYPE_GROUP_REMINDER_REMOVE_USER = 1;
	// 3.����Ⱥ,ֱ�Ӿ��Ǽ�����Ⱥ��ʾ��Ϣ
	int TYPE_GROUP_REMINDER_ADD_GROUP = 2;
	// 4.������Ⱥ
	int TYPE_GROUP_REMINDER_INVITE_GROUP = 3;
	// 5.�ܾ��û�����Ⱥ
	int TYPE_GROUP_REMINDER_REFUSE_ADD_GROUP = 4;
	// 6.ͬ���û�����Ⱥ
	int TYPE_GROUP_REMINDER_AGREE_ADD_GROUP = 5;
	// 7.ĳ�û��������Ⱥ
	int TYPE_GROUP_REMINDER_WANT_TO_ADD_GROUP = 6;
}