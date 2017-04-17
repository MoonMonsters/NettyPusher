/**
 * ����GetInfoHandler���ݣ�����������ָ����Ĵӿͻ��˷����������ݣ���type�����ֿ���
 */
package edu.csuft.chentao.controller;

import java.util.List;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.GetInfoReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2017��2��1�� ����9:59:51
 */
public class GetInfoHandler implements Handler {

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
			// ����
			chc.writeAndFlush(resp);
		} else if (req.getType() == Constant.TYPE_GET_INFO_UNLOGIN) { // �˳���¼
			int userId = req.getArg1();
			NettyCollections.removeWithUserId(userId); // �Ƴ�
		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_ID) { // ����Ⱥid����
			// Ⱥid
			int groupId = req.getArg1();
			// �����ݱ��ж�ȡȺ����
			GroupInfoResp resp = GroupTableOperate.getGroupInfoWithId(groupId);
			if(resp != null){
				// ���÷�������
				resp.setType(Constant.TYPE_GROUP_INFO_SEARCH);
				// ����
				chc.writeAndFlush(resp);
			}else{
				sendRespForSearchGroupSize0(chc);
			}
		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_NAME) { // ����Ⱥ������
			// �õ�Ⱥ���еĹؼ���
			String groupName = (String) req.getObj();
			// �õ�����Ĳ�����10������
			List<GroupInfoResp> groupInfoList = GroupTableOperate
					.getGroupInfoListWithGroupName(groupName);
			Logger.log("��Ⱥ����ȡ����������-->"+groupInfoList.size());
			for (GroupInfoResp gir : groupInfoList) {
				// ��������
				gir.setType(Constant.TYPE_GROUP_INFO_SEARCH);
				// ����0.5��
				OperationUtil.sleepFor500();
				chc.writeAndFlush(gir);
			}
			if(groupInfoList.size() == 0){
				sendRespForSearchGroupSize0(chc);
			}
		} else if (req.getType() == Constant.TYPE_GET_INFO_SEARCH_GROUP_TAG) { // ����Ⱥ��ǩ����
			//�õ���ǩ
			String tag = (String) req.getObj();
			//���ݼ���
			List<GroupInfoResp> groupInfoList = GroupTableOperate
					.getGroupInfoListWithGroupTag(tag);
			Logger.log("��Ⱥ��ǩ��ȡ����������-->"+groupInfoList.size());
			//��������
			for (GroupInfoResp gir : groupInfoList) {
				// ��������
				gir.setType(Constant.TYPE_GROUP_INFO_SEARCH);
				// ����0.5��
				OperationUtil.sleepFor500();
				chc.writeAndFlush(gir);
			}
			if(groupInfoList.size() == 0){
				sendRespForSearchGroupSize0(chc);
			}
		}
	}

	private void sendRespForSearchGroupSize0(ChannelHandlerContext chc) {
		
		OperationUtil.sendReturnInfoResp(chc, Constant.TYPE_RETURN_INFO_SEARCH_GROUP_SIZE_0, "û�����������Ⱥ���ݣ����֤���ٳ���");
	}
}
