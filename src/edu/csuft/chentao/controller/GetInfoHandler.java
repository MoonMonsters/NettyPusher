/**
 * 
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.GetInfoReq;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2017��2��1�� ����9:59:51
 */
public class GetInfoHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		GetInfoReq req = (GetInfoReq) object;

		Logger.log("�û���������GetInfoReq->" + req.toString());

		if (req.getType() == Constant.TYPE_GET_INFO_USERINFO) { // �����û���Ϣ
			// �õ��û�id
			int userId = req.getArg1();
			Logger.log("�û������userId->" + userId);
			// �����û�id�õ��û���Ϣ
			UserInfoResp resp = UserTableOperate
					.selectUserInfoWithUserId(userId);
			resp.setType(Constant.TYPE_LOGIN_USER_INFO);
			Logger.log("���������UserInfo����-->" + resp.toString());
			// ����
			chc.writeAndFlush(resp);
		} else if (req.getType() == Constant.TYPE_GET_INFO_UNLOGIN) { // �˳���¼
			int userId = req.getArg1();
			NettyCollections.removeWithUserId(userId); // �Ƴ�
		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_ID) { // ����Ⱥid����

		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_NAME) { // ����Ⱥ������

		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_TAG) { // ����Ⱥ��ǩ����

		}
	}

}
