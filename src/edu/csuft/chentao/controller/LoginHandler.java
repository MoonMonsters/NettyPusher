/**
 * �����¼��Ϣ
 */
package edu.csuft.chentao.controller;

import java.util.List;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.LoginReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:22:25
 */
public class LoginHandler implements Handler {

	public void handle(final ChannelHandlerContext chc, Object object) {

		LoginReq req = (LoginReq) object;

		//�����ͬʱ��������ͬ���˺ŵ�¼���򶥵�ǰ��һ���˺�
		int userId = UserTableOperate.selectUserId(req.getUsername(), req.getPassword());
		if(NettyCollections.getChannelHandlerContextByUserId(userId) != null){
			Logger.log("�ظ���¼���Ƴ���ǰ��һ���ͻ���");
			//�õ�ǰһ���û���¼�����Ӷ���
			ReturnInfoResp resp = new ReturnInfoResp();
			resp.setType(Constant.TYPE_RETURN_INFO_CLIENT_EXIT);
			ChannelHandlerContext ctx = NettyCollections.getChannelHandlerContextByUserId(userId);
			//������Ϣ
			ctx.writeAndFlush(resp);
			
			//�ӷ���˽��ͻ������Ӷ����Ƴ���
			NettyCollections.removeWithUserId(userId);
		
		}
		
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
				final List<GroupInfoResp> groupInfoList = GroupTableOperate
						.selectAllGroupInfosWithGroupIds(groupIdList);

				// �����߳��з�����������
				new Thread(new Runnable() {
					public void run() {
						// ������Ⱥ��Ϣ���͵��ͻ���
						for (GroupInfoResp gir : groupInfoList) {
							gir.setType(Constant.TYPE_GROUP_INFO_OWNER);
							OperationUtil.sleepFor500();
							chc.writeAndFlush(gir);
						}

					}
				}).start();
			}
		}

		Logger.log("LoginHandler-->username = "+resp.getUsername() + ",userId = " + resp.getUserid());
		chc.writeAndFlush(resp);
	}
}
