/**
 * ����ģʽ������Handler����
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.pojo.req.CreateGroupReq;
import edu.csuft.chentao.pojo.req.GetUserAndGroupInfoReq;
import edu.csuft.chentao.pojo.req.GroupOperationReq;
import edu.csuft.chentao.pojo.req.LoginReq;
import edu.csuft.chentao.pojo.req.Message;
import edu.csuft.chentao.pojo.req.RegisterReq;
import edu.csuft.chentao.pojo.req.UpdateUserInfoReq;
import edu.csuft.chentao.util.Logger;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����8:52:30
 */
public class MessageHandlerFactory {

	/**
	 * ����object���ͣ����ض�Ӧ��������
	 * 
	 * @param object
	 *            �ͻ��˷��͵���������
	 */
	public static Handler getHandlerInstance(Object object) {
		Handler handler = null;

		String msg = "MessageHandlerFactory-->";

		if (object instanceof CreateGroupReq) { // ��������Ⱥ��Ϣ
			msg += "CreateGroupReq";
			handler = new CreateGroupHandler();
		} else if (object instanceof GroupOperationReq) { // Ⱥ����
			msg += "GroupOperationReq";
			handler = new GroupOperationHandler();
		} else if (object instanceof LoginReq) { // ��¼
			msg += "LoginReq";
			handler = new LoginHandler();
		} else if (object instanceof Message) { // ������Ϣ
			msg += "MessageReq";
			handler = new MessageHandler();
		} else if (object instanceof RegisterReq) { // ע��
			msg += "RegisterReq";
			handler = new RegisterHandler();
		} else if (object instanceof UpdateUserInfoReq) { // �����û���Ϣ
			msg += "UpdateInfoReq";
			handler = new UpdateUserInfoHandler();
		} else if (object instanceof GetUserAndGroupInfoReq) { // ����û�����Ⱥ��Ϣ
			msg += "GetUserAndGroupInfoReq";
			handler = new GetUserAndGroupInfoHandler();
		} 

		Logger.log(msg);

		return handler;
	}

}