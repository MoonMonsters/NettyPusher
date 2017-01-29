/**
 * ������Ϣ�������
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 * 2016��12��9�� ����8:48:12
 */
public class AllMessageHandler {

	public static void handleMessage(ChannelHandlerContext chc, Object object){
		
		Logger.log("AllMessageHandler-->������Ϣ���");
		
		//������Ӧ��object���ͣ��õ���Ӧ�Ĵ�������
		Handler handler = MessageHandlerFactory.getHandlerInstance(object);
		
		//��������
		handler.handle(chc, object);
	}
}