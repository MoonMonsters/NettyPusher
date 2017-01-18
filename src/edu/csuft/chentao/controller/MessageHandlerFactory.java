/**
 * ����ģʽ������Handler����
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.pojo.req.CreateGrourpReq;
import edu.csuft.chentao.pojo.req.GroupOperationReq;
import edu.csuft.chentao.pojo.req.LoginReq;
import edu.csuft.chentao.pojo.req.Message;
import edu.csuft.chentao.pojo.req.RegisterReq;
import edu.csuft.chentao.pojo.req.UpdateUserInfoReq;
import edu.csuft.chentao.util.Logger;

/**
 * @author csuft.chentao
 *
 * 2016��12��9�� ����8:52:30
 */
public class MessageHandlerFactory {

	/**
	 * ����object���ͣ����ض�Ӧ�������
	 * @param object �ͻ��˷��͵���������
	 */
	public static Handler getHandlerInstance(Object object){
		Handler handler = null;
		
		String msg = "MessageHandlerFactory-->";
		
		if(object instanceof CreateGrourpReq){
			msg += "CreateGroupReq";
			handler = new CreateGroupHandler();
		}else if(object instanceof GroupOperationReq){
			msg += "GroupOperationReq";
			handler = new GroupOperationHandler();
		}else if(object instanceof LoginReq){
			msg += "LoginReq";
			handler = new LoginHandler();
		}else if(object instanceof Message){
			msg += "MessageReq";
			handler = new MessageHandler();
		}else if(object instanceof RegisterReq){
			msg += "RegisterReq";
			handler = new RegisterHandler();
		}else if(object instanceof UpdateUserInfoReq){
			msg += "UpdateInfoReq";
			handler = new UpdateUserInfoHandler();
		}
		
		Logger.log(msg);
		
		return handler;
	}
	
}
