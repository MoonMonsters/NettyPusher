/**
 * �����û���Ⱥ�Ĳ�����������ӻ��˳���
 */
package edu.csuft.chentao.controller;

import java.util.List;

import edu.csuft.chentao.dao.GroupOperationTable;
import edu.csuft.chentao.dao.GroupOperationTableOperate;
import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.pojo.req.GroupOperationReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.pojo.resp.GroupReminderResp;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:14:10
 */
public class GroupOperationHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {

		GroupOperationReq req = (GroupOperationReq) object;
		GroupInfoResp groupInfo = GroupTableOperate.getGroupInfoWithId(req
				.getGroupId());

		if (req.getType() == Constant.TYPE_GROUP_OPERATION_EXIT_BY_MYSELF) { // �Լ��˳�

			Logger.log("�Լ��˳�Ⱥ");
			boolean result = GroupUserTableOperate.exitGroup(req.getGroupId(),
					req.getUserId1());
			if (result) { // ����˳��ɹ�

				GroupReminderResp resp = new GroupReminderResp();
				resp.setType(Constant.TYPE_GROUP_REMINDER_EXIT_BY_MYSELF);
				resp.setGroupId(req.getGroupId());
				resp.setImage(groupInfo.getHeadImage());
				resp.setDescription("�˳�Ⱥ�ɹ�");
				resp.setGroupName(groupInfo.getGroupname());
				resp.setUserId(req.getUserId1());

				chc.writeAndFlush(resp);
			} else { // �˳�Ⱥʧ��
				ReturnInfoResp resp = new ReturnInfoResp();
				resp.setType(Constant.TYPE_RETURN_INFO_EXIT_GROUP_FAIL);
				resp.setDescription("�˳�Ⱥʧ�ܣ����֤������");

				chc.writeAndFlush(resp);
			}
		} else if (req.getType() == Constant.TYPE_GROUP_OPERATION_EXIT_BY_ADMIN) { // ���߳�

			Logger.log("�߳�Ⱥ");
			int userId = req.getUserId1();
			int groupId = req.getGroupId();
			boolean result = GroupUserTableOperate.exitGroup(req.getGroupId(),
					req.getUserId1());
			if (result) { // �߳��ɹ�
				GroupOperationTable table = new GroupOperationTable();
				table.setDescription("���ѱ��߳�Ⱥ");
				table.setGroupId(groupId);
				table.setReaderId(userId);
				table.setType(Constant.TYPE_GROUP_REMINDER_REMOVE_USER);
				table.setUserId(userId);

				GroupOperationTableOperate.insert(table);

				// ReturnInfoResp
			} else { // �߳�ʧ��
				// ReturnInfoResp
			}
		} else if (req.getType() == Constant.TYPE_GROUP_OPERATION_ADD_BY_MYSELF) { // �Լ��������Ⱥ

			int userId = req.getUserId1();
			int groupId = req.getGroupId();

			Logger.log(userId + "�Լ��������Ⱥ" + groupId);

			// ��������Ⱥ������
			if (GroupTableOperate.isExitGroupWithGroupId(groupId)) {
				Logger.log(groupId + " Ⱥ������");
				// ReturnInfoResp
			} else if (!GroupUserTableOperate.isExit(groupId, userId)) { // �������Ⱥ��Ա

				Logger.log("��Ⱥ�ﲻ����");

				String name = UserTableOperate.getUsernameWithUserId(userId);
				List<Integer> readerIdList = GroupUserTableOperate
						.getCapital_0_WithGroupId(groupId);

				GroupOperationTable table = new GroupOperationTable();
				table.setDescription(name + " �������Ⱥ");
				table.setGroupId(groupId);
				table.setType(Constant.TYPE_GROUP_REMINDER_WANT_TO_ADD_GROUP);
				table.setUserId(userId);

				Logger.log("�������Ⱥʱ��Ⱥ������Ա����->" + readerIdList.size());

				for (int readerId : readerIdList) {
					// ���ò�ͬ�Ķ�ȡ��Ϣ�û�
					table.setReaderId(readerId);
					GroupOperationTableOperate.insert(table);
				}
			} else if (GroupUserTableOperate.isExit(groupId, userId)) { // �����Ⱥ���Ѿ�����
				Logger.log("��Ⱥ���Ѿ�����");
				// ReturnInfoResp
			}
		} else if (req.getType() == Constant.TYPE_GROUP_OPERATION_ADD_BY_INVITE) { // ���������Ⱥ
			Logger.log("�������Ⱥ");

			// ��������id
			int userId1 = req.getUserId1();
			// Ⱥid
			int groupId = req.getGroupId();

			if(!UserTableOperate.isExitUserWithUserId(userId1)){	//���������û�id���󣬲����ڸ��û�
				
			}else if (!GroupUserTableOperate.isExit(groupId, userId1)) {	//������û����ڸ�Ⱥ��
				Logger.log(userId1 + "�����ڣ����Բ���");
				int userId2 = req.getUserId2();
				// ��������˵�����
				String userName2 = UserTableOperate
						.getUsernameWithUserId(userId2);
				GroupOperationTable table = new GroupOperationTable();
				table.setDescription(userName2 + "���������Ⱥ");
				table.setGroupId(groupId);
				table.setType(Constant.TYPE_GROUP_REMINDER_INVITE_GROUP);
				table.setUserId(userId1);
				// ���ö�ȡ��Ϣ���û�id
				table.setReaderId(userId1);
				GroupOperationTableOperate.insert(table);
			} else if (GroupUserTableOperate.isExit(groupId, userId1)){	//�Ѿ�����Ⱥ��
				Logger.log(userId1 + "�Ѿ����ڣ����ܲ���");
				// TODO
				// ReturnInfoResp
			}

		} else if (req.getType() == Constant.TYPE_GROUP_OPERATION_AGREE_ADD_GROUP) { // ͬ�����Ⱥ
			Logger.log("ͬ�����Ⱥ");
			// ͬ�����Ⱥ
			boolean result = GroupUserTableOperate.insert(req.getGroupId(),
					req.getUserId1(), 2);
			if (result) { // ���ݲ���ɹ�
				GroupOperationTable table = new GroupOperationTable();
				table.setDescription("�ɹ�����Ⱥ");
				table.setGroupId(req.getGroupId());
				table.setType(Constant.TYPE_GROUP_REMINDER_ADD_GROUP);
				table.setUserId(req.getUserId1());
				// ���ö�ȡ��Ϣ���û�id
				table.setReaderId(req.getUserId1());
				GroupOperationTableOperate.insert(table);
			}else{	//�Ѿ����ڸ�Ⱥ��
				//ReturnInfoResp
			}
		} else if (req.getType() == Constant.TYPE_GROUP_OPERATION_REFUSE_ADD_GROUP) { // �ܾ�����Ⱥ
			Logger.log("�ܾ�����Ⱥ");
			
			int userId = req.getUserId1();
			int groupId = req.getGroupId();
			
			if(!GroupUserTableOperate.isExit(groupId, userId)){	//���û�б��������Ⱥ����ô����ʾ�ܾ���Ϣ
				GroupOperationTable table = new GroupOperationTable();
				table.setDescription("�㱻�ܾ�����Ⱥ");
				table.setGroupId(req.getGroupId());
				table.setType(Constant.TYPE_GROUP_REMINDER_REFUSE_ADD_GROUP);
				table.setUserId(req.getUserId1());
				// ���ö�ȡ��Ϣ���û�id
				table.setReaderId(req.getUserId1());
				GroupOperationTableOperate.insert(table);
			}
		}
	}

}
