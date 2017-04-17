/**
 * 
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.pojo.req.ManagerUserReq;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2017��1��30�� ����10:53:03
 */
public class ManagerUserHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		Logger.log("ManagerUserHandler->�����û���Ϣ");
		ManagerUserReq req = (ManagerUserReq) object;
		
		ReturnInfoResp resp = GroupUserTableOperate.updateCapital(
				req.getUserId(), req.getGroupId(), req.getCapital());
		chc.writeAndFlush(resp);
	}

}
