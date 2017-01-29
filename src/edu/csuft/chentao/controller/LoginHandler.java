/**
 * �����¼��Ϣ
 */
package edu.csuft.chentao.controller;

import java.util.ArrayList;
import java.util.List;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.LoginReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:22:25
 */
public class LoginHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {

		Logger.log("LoginHandler-->��¼����");

		LoginReq req = (LoginReq) object;

		// ������Ϣ
		UserInfoResp resp = new UserInfoResp();

		/*
		 * �Զ���¼���µĵ�¼��ȣ��µĵ�¼��Я���������û����ݻ�ȥ
		 */
		// �Զ���¼
		if (req.getType() == Constant.TYPE_LOGIN_AUTO) {
			resp.setType(Constant.TYPE_LOGIN_AUTO);
			resp.setUserid(UserTableOperate.selectUserId(req.getUsername(),
					req.getPassword()));
			// �µĵ�¼
		} else if (req.getType() == Constant.TYPE_LOGIN_NEW) {
			resp = UserTableOperate.selectUserInfo(req.getUsername(),
					req.getPassword());
			resp.setType(Constant.TYPE_LOGIN_NEW);
		}

		if (resp.getUserid() >= Constant.DEFAULT_USERID) { // �����¼�ɹ��������ص������У��û�id�Ǵ��ڵ�

			// �ڵ�¼ʱ���Ѹ�ChannelHandlerContext������뼯����
			NettyCollections.add(resp.getUserid(), chc);

			// �����¼�ɹ����������µĵ�¼
			if (req.getType() == Constant.TYPE_LOGIN_NEW) {
				// �õ����û����������Ⱥ��Ϣ
				// ���û�����Ⱥ��id
				List<Integer> groupIdList = GroupUserTableOperate
						.selectGroupIdsWithUserId(resp.getUserid());
				// ���û�����Ⱥ������Ⱥ��Ϣ
				List<GroupInfoResp> groupInfoList = GroupTableOperate
						.selectAllGroupInfosWithGroupIds(groupIdList);

				/**
				 * �õ������û���Ϣ
				 */
				List<Integer> userIdList = new ArrayList<Integer>();
				List<UserInfoResp> userInfoList = new ArrayList<>();
				for (int groupId : groupIdList) {
					userIdList.addAll(GroupUserTableOperate
							.selectAllUserIdsWithGroupId(groupId));
				}
				for (int userId : userIdList) {
					userInfoList.add(UserTableOperate
							.selectUserInfoWithUserId(userId));
				}

				// �����߳��з�����������
				new Thread(new Runnable() {
					@Override
					public void run() {
						// ������Ⱥ��Ϣ���͵��ͻ���
						for (GroupInfoResp gir : groupInfoList) {
							chc.writeAndFlush(gir);
						}

						/**
						 * ���������û�����
						 */
						for (UserInfoResp resp : userInfoList) {
							chc.writeAndFlush(resp);
						}
					}
				}).start();
			}
		}

		Logger.log("UserInfoResp-->�����û���Ϣ");

		chc.writeAndFlush(resp);
	}
}
