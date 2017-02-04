/**
 * ����������Ϣ
 */
package edu.csuft.chentao.controller;

import java.util.List;

import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.Message;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:23:31
 */
public class MessageHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {

		Logger.log("MessageHandler-->����Ⱥ��Ϣ����");

		Message message = (Message) object;

		// �����͸ı䣬��������ʱ���Ƿ������ͣ���Ҫ�ĳɽ�������
		message.setType(Constant.TYPE_MSG_RECV);

		// ���һ��Ⱥ�������û���id
		List<Integer> useridList = GroupUserTableOperate
				.selectAllUserIdsWithGroupId(message.getGroupid());
		
		// ��Ϊ�Ƿ��͸������ˣ�������Ҫ�Ƴ����Լ�����Ķ���
		int index = useridList.indexOf(message.getUserid());
		if (index > -1) {
			useridList.remove(index);
		}

//		message.setType(Constant.TYPE_MSG_RECV);
		chc.writeAndFlush(message);
		
		// ����������Ҫ���͵Ķ���
		NettyCollections.traverse(useridList, message);
	}

}
