/**
 * �����û���Ⱥ�Ĳ�����������ӻ��˳���
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.pojo.req.GroupOperationReq;
import edu.csuft.chentao.pojo.resp.ReturnMessageResp;
import edu.csuft.chentao.util.Constant;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 * 2016��12��9�� ����9:14:10
 */
public class GroupOperationHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		GroupOperationReq req = (GroupOperationReq)object;
		ReturnMessageResp resp = null;
		if(req.getType() == Constant.TYPE_GROUP_ENTER){	//��Ⱥ
			resp = GroupUserTableOperate.insert(req);
		}else if(req.getType() == Constant.TYPE_GROUP_EXIT){ //��Ⱥ
			resp = GroupUserTableOperate.remove(req);
		}
		chc.writeAndFlush(resp);
	}

}
