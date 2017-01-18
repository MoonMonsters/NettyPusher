/**
 * �����¼��Ϣ
 */
package edu.csuft.chentao.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

		if (resp.getUserid() >= Constant.DEFAULT_USERID) {
			
			// �ڵ�¼ʱ���Ѹ�ChannelHandlerContext������뼯����
			NettyCollections.add(resp.getUserid(), chc);
			
			//�����¼�ɹ����������µĵ�¼
			if(req.getType() == Constant.TYPE_LOGIN_NEW){
				//1.�õ����û����������Ⱥ��Ϣ
				//���û�����Ⱥ��id
				List<Integer> groupIdList = GroupUserTableOperate.selectGroupIdsWithUserId(resp.getUserid());
				//���û�����Ⱥ������Ⱥ��Ϣ
				List<GroupInfoResp> groupInfoList = GroupTableOperate.selectAllGroupInfosWithGroupIds(groupIdList);
				
				//2.��Ⱥ�а����е��û���Ϣ���͹�ȥ
				Set<Integer> userIdSet = new HashSet<Integer>();
				//����Ⱥid�õ����е��û�id
				for(int groupId : groupIdList){
					userIdSet.addAll(GroupUserTableOperate.selectUserIdsWithGroupId(groupId));
				}
				List<UserInfoResp> userInfoList = new ArrayList<UserInfoResp>();
				Iterator<Integer> it = userIdSet.iterator();
				//�����û�id�õ��û���Ϣ
				while(it.hasNext()){
					int userId = it.next();
					UserInfoResp userInfo = UserTableOperate.selectUserInfoWithUserId(userId);
					//������Ϣ����
					userInfo.setType(Constant.TYPE_LOGIN_USER_INFO);
					userInfoList.add(userInfo);
				}
				
				//�����߳��з�����������
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						//������Ⱥ��Ϣ���͵��ͻ���
						for(GroupInfoResp gir : groupInfoList){
							chc.writeAndFlush(gir);
						}
						//�����е��û���Ϣ���͵��ͻ���
						for(UserInfoResp resp : userInfoList){
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
