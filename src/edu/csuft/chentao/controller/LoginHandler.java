/**
 * �����¼��Ϣ
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.LoginReq;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:22:25
 */
public class LoginHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		LoginReq req = (LoginReq) object;

		// ������Ϣ
		UserInfoResp resp = new UserInfoResp();

		// �Զ���¼
		if (req.getType() == Constant.TYPE_LOGIN_AUTO) {
			resp.setType(Constant.TYPE_LOGIN_AUTO);
			resp.setUserid(UserTableOperate.selectUserId(req.getUsername(),
					req.getPassword()));
			// �µĵ�¼
		} else if (req.getType() == Constant.TYPE_LOGIN_NEW) {
			resp = UserTableOperate.selectUserInfo(req.getUsername(),
					req.getPassword());
		}

		if (resp.getUserid() >= Constant.DEFAULT_USERID) {
			// �ڵ�¼ʱ���Ѹ�ChannelHandlerContext������뼯����
			NettyCollections.add(resp.getUserid(), chc);
		}

		chc.writeAndFlush(resp);
	}
}
