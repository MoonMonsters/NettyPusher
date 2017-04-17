/**
 * ������Ⱥ����
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.pojo.req.CreateGroupReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:03:06
 */
public class CreateGroupHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		Logger.log("CreateGroupHandler-->����Ⱥ����");
		CreateGroupReq req = (CreateGroupReq) object;
		// ִ�в�����������ҷ��ؽ��
		int groupId = GroupTableOperate.insert(req);

		Logger.log("����Ⱥ�Ƿ�ɹ�-->" + (groupId == -1 ? false : true));

		/**
		 * ���ش�����Ⱥ
		 */
		if (groupId != -1) {
			OperationUtil.sendReturnInfoResp(chc,
					Constant.TYPE_RETURN_INFO_CREATE_GROUP_SUCCESS,
					req.getGroupname() + "�����ɹ�");

			GroupInfoResp resp2 = new GroupInfoResp();
			resp2.setGroupid(groupId);
			resp2.setGroupname(req.getGroupname());
			resp2.setHeadImage(req.getHeadImage());
			resp2.setNumber(1);
			resp2.setTag(req.getTag());
			resp2.setType(Constant.TYPE_GROUP_INFO_OWNER);
			chc.writeAndFlush(resp2);
		} else {
			OperationUtil.sendReturnInfoResp(chc,
					Constant.TYPE_RETURN_INFO_CREATE_GROUP_FAIL,
					req.getGroupname() + "����ʧ�ܣ����Ժ�����");
		}

		Logger.log("CreateGroupHandler-->���ش���Ⱥ��Ⱥ��Ϣ");
	}
}
