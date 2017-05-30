/**
 * 
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.pojo.req.GetUserAndGroupInfoReq;
import edu.csuft.chentao.pojo.resp.UserIdsInGroupResp;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import io.netty.channel.ChannelHandlerContext;

/**
 * new1.�ӿͻ��˷��͸�ָ�����û���Ϣ����Ⱥ�г�Ա��Ϣ
 * 
 * @author csuft.chentao
 *
 *         2017��1��18�� ����3:21:19
 */
public class GetUserAndGroupInfoHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		GetUserAndGroupInfoReq req = (GetUserAndGroupInfoReq) object;
		if (req.getType() == Constant.TYPE_USER_GROUP_INFO_USER) { // �����û���Ϣ
			int userId = req.getId();
			UserInfoResp resp = UserTableOperate
					.selectUserInfoWithUserId(userId);
			resp.setType(Constant.TYPE_LOGIN_USER_INFO);
			chc.writeAndFlush(resp);
		} else if (req.getType() == Constant.TYPE_USER_GROUP_INFO_GROUP) {
			int groupId = req.getId();
			UserIdsInGroupResp resp = new UserIdsInGroupResp();
			resp.setGroupId(groupId);
			resp.setUserIdCapitalList(GroupUserTableOperate
					.selectIdAndCapitalInUserWithGroupId(groupId));
			chc.writeAndFlush(resp);
		}
	}
}
