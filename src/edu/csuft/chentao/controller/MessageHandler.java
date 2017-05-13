/**
 * ����������Ϣ
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.Message;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:23:31
 */
public class MessageHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {

		Logger.log("MessageHandler-->����Ⱥ��Ϣ����");

		Message message = (Message) object;

		// �����͸ı䣬��������ʱ���Ƿ������ͣ���Ҫ�ĳɽ�������
		message.setType(Constant.TYPE_MSG_RECV);

		/*
		 * ��Ϣ���ͳɹ������ض����֪�ͻ���
		 */
		ReturnInfoResp resp = new ReturnInfoResp();
		resp.setType(Constant.TYPE_RETURN_INFO_SEND_MESSAGE_SUCCESS);
		resp.setObj(message.getSerial_number());

		chc.writeAndFlush(resp);

		// ����������Ҫ���͵Ķ���
		NettyCollections.traverse(message.getGroupid(), message);
	}

}
