/**
 * ����ע����Ϣ
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.pojo.req.RegisterReq;
import edu.csuft.chentao.pojo.resp.RegisterResp;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:24:30
 */
public class RegisterHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		
		RegisterReq registerReq = (RegisterReq) object;
		//ע��
		RegisterResp resp = UserTableOperate.insert(registerReq);
		
		//����ע������Ϣ
		chc.writeAndFlush(resp);
	}
}
