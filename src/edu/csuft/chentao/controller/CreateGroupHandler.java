/**
 * ������Ⱥ����
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.pojo.req.CreateGrourpReq;
import edu.csuft.chentao.pojo.resp.CreateGroupResp;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:03:06
 */
public class CreateGroupHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		Logger.log("CreateGroupHandler-->����Ⱥ����");
		CreateGrourpReq req = (CreateGrourpReq) object;
		// ִ�в�����������ҷ��ؽ��
		CreateGroupResp resp = GroupTableOperate.insert(req);

		Logger.log("CreateGroupResp-->���ش���Ⱥ��Ⱥ��Ϣ");

		chc.writeAndFlush(resp);
	}
}
