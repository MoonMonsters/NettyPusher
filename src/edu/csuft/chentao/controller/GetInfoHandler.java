/**
 * ����GetInfoHandler���ݣ�����������ָ����Ĵӿͻ��˷����������ݣ���type�����ֿ���
 */
package edu.csuft.chentao.controller;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.csuft.chentao.dao.GroupFileZipTableOperation;
import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.dao.MessageTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.FileZip;
import edu.csuft.chentao.pojo.req.GetInfoReq;
import edu.csuft.chentao.pojo.req.Message;
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
 *         2017��2��1�� ����9:59:51
 */
public class GetInfoHandler implements Handler {

	/**
	 * ��Ҫ�������ͬ����ͬ�����⣬��Ϊÿ���յ�����ʱ�����ǲ�ͬ���߳��� ������Ҫ�ܿ��̵߳�hashmap���������
	 */
	private static ConcurrentHashMap<Integer, Boolean> mIsSyncMessageMap = new ConcurrentHashMap<Integer, Boolean>();

	public void handle(final ChannelHandlerContext chc, Object object) {
		final GetInfoReq req = (GetInfoReq) object;

		Logger.log("�û���������GetInfoReq->" + req.toString());

		switch (req.getType()) {
		case Constant.TYPE_GET_INFO_USERINFO: // ��ȡ�û���Ϣ
			getUserInfo(chc, req);
			break;
		case Constant.TYPE_GET_INFO_UNLOGIN: // �˳���¼
			userExitLogin(req);
			break;
		case Constant.TYPE_GET_INFO_SEARCH_GROUP_ID: // ����Ⱥid����Ⱥ
			searchGroupById(chc, req);
			break;
		case Constant.TYPE_GET_INFO_SEARCH_GROUP_NAME: // ����Ⱥ������Ⱥ
			searchGroupByName(chc, req);
			break;
		case Constant.TYPE_GET_INFO_SEARCH_GROUP_TAG: // ����Ⱥ��ǩ����Ⱥ
			searchGroupByTag(chc, req);
			break;
		case Constant.TYPE_GET_INFO_GROUP_FILE_LIST: // ���Ⱥ�е��ļ��б�
			getGroupFileList(chc, req);
			break;
		case Constant.TYPE_GET_INFO_DOWNLOAD_FILE: // �����ļ�
			downloadFile(chc, req);
			break;
		case Constant.TYPE_GET_INFO_REMOVE_FILE: // ɾ���ļ�
			removeGroupFile(chc, req);
			break;
		case Constant.TYPE_GET_INFO_START_SYNC_GROUP_MESSAGE: // ͬ��������Ϣ
			// ��Ҫ�ŵ����߳���ȥ����1.���ʹ������ݺܺ�ʱ
			// 2.��Ҫ�õ�ֹͣͬ�����ܣ���������ŵ����߳��У�����ͬ��������һ���߳��У������Ⱥ�˳��ִ�У��޷�ʵ��ֹͣ����
			new Thread(new Runnable() {
				public void run() {
					startSyncMessage(chc, req);
				}
			}).start();
			break;
		case Constant.TYPE_GET_INFO_STOP_SYNC_GROUP_MESSAGE:
			stopSyncMessage(req);
			break;
		}
	}

	/**
	 * ���group�ļ��б�
	 */
	private void getGroupFileList(ChannelHandlerContext chc, GetInfoReq req) {
		List<FileZip> fileZipList = GroupFileZipTableOperation.queryAll(req
				.getArg1());
		if (fileZipList.size() == 0) {
			ReturnInfoResp resp = new ReturnInfoResp();
			resp.setType(Constant.TYPE_RETURN_INFO_FILE_LIST_SIZE_0);
			resp.setObj("��Ⱥ����û���κ��ļ�");
			chc.writeAndFlush(resp);
		} else {
			for (FileZip fz : fileZipList) { // ���������ݷ��͵��ͻ���
				chc.writeAndFlush(fz);
			}
		}
	}

	/**
	 * ����Ⱥ��ǩ����Ⱥ �������Ŀ��ܻ��кܶ࣬����һ��һ���ķ��͵��ͻ���ȥ
	 */
	private void searchGroupByTag(ChannelHandlerContext chc, GetInfoReq req) {
		// �õ���ǩ
		String tag = (String) req.getObj();
		// ���ݼ���
		List<GroupInfoResp> groupInfoList = GroupTableOperate
				.getGroupInfoListWithGroupTag(tag);
		Logger.log("��Ⱥ��ǩ��ȡ����������-->" + groupInfoList.size());
		// ��������
		for (GroupInfoResp gir : groupInfoList) {
			// ��������
			gir.setType(Constant.TYPE_GROUP_INFO_SEARCH);
			// ����0.5��
			OperationUtil.sleepFor500();
			chc.writeAndFlush(gir);
		}
		if (groupInfoList.size() == 0) {
			sendRespForSearchGroupSize0(chc);
		}
	}

	/**
	 * ����Ⱥ��������Ⱥ
	 */
	private void searchGroupByName(ChannelHandlerContext chc, GetInfoReq req) {
		// �õ�Ⱥ���еĹؼ���
		String groupName = (String) req.getObj();
		// �õ�����Ĳ�����10������
		List<GroupInfoResp> groupInfoList = GroupTableOperate
				.getGroupInfoListWithGroupName(groupName);
		Logger.log("��Ⱥ����ȡ����������-->" + groupInfoList.size());
		for (GroupInfoResp gir : groupInfoList) {
			// ��������
			gir.setType(Constant.TYPE_GROUP_INFO_SEARCH);
			// ����0.5��
			OperationUtil.sleepFor500();
			chc.writeAndFlush(gir);
		}
		if (groupInfoList.size() == 0) {
			sendRespForSearchGroupSize0(chc);
		}
	}

	/**
	 * ����Ⱥid����Ⱥ
	 */
	private void searchGroupById(ChannelHandlerContext chc, GetInfoReq req) {
		// Ⱥid
		int groupId = req.getArg1();
		// �����ݱ��ж�ȡȺ����
		GroupInfoResp resp = GroupTableOperate.getGroupInfoWithId(groupId);
		if (resp != null) {
			// ���÷�������
			resp.setType(Constant.TYPE_GROUP_INFO_SEARCH);
			// ����
			chc.writeAndFlush(resp);
		} else {
			sendRespForSearchGroupSize0(chc);
		}
	}

	/**
	 * �û��˳���¼
	 */
	private void userExitLogin(GetInfoReq req) {
		int userId = req.getArg1();
		NettyCollections.removeWithUserId(userId); // �Ƴ�
	}

	/**
	 * ����û�
	 */
	private void getUserInfo(ChannelHandlerContext chc, GetInfoReq req) {
		// �õ��û�id
		int userId = req.getArg1();
		Logger.log("�û������userId->" + userId);
		// �����û�id�õ��û���Ϣ
		UserInfoResp resp = UserTableOperate.selectUserInfoWithUserId(userId);
		resp.setType(Constant.TYPE_LOGIN_USER_INFO);
		// ����
		chc.writeAndFlush(resp);
	}

	/**
	 * ����Ⱥʱ��������������Ϊ0
	 */
	private void sendRespForSearchGroupSize0(ChannelHandlerContext chc) {

		OperationUtil.sendReturnInfoResp(chc,
				Constant.TYPE_RETURN_INFO_SEARCH_GROUP_SIZE_0,
				"û�����������Ⱥ���ݣ����֤���ٳ���");
	}

	/**
	 * �����ļ�
	 */
	private void downloadFile(ChannelHandlerContext chc, GetInfoReq req) {
		String serialNumber = (String) req.getObj();

		Logger.log(req.toString());

		byte[] content = OperationUtil.getGroupFile(req.getArg1(),
				(String) req.getObj());

		// �����ݱ��л������
		FileZip fz = GroupFileZipTableOperation
				.queryBySerialNumber(serialNumber);
		fz.setZip(content);

		// ���͵��ͻ���
		chc.writeAndFlush(fz);

	}

	/**
	 * ɾ��Ⱥ�ļ�
	 */
	private void removeGroupFile(ChannelHandlerContext chc, GetInfoReq req) {

		// �ļ����к�
		String serialNumber = (String) req.getObj();
		// Ⱥid
		int groupId = req.getArg1();
		// �û�id
		int userId = req.getArg2();

		int capital = GroupUserTableOperate.getCapitalWithUserIdAndGroupId(
				groupId, userId);
		int owerId = GroupFileZipTableOperation.getFileOwnerBySerialNumber(
				serialNumber, groupId);

		ReturnInfoResp resp = new ReturnInfoResp();

		// ���Ҫɾ�����ļ����û��ǣ�����Ա��Ⱥ�������ļ����ϴ��ߣ���ô������ִ��ɾ������
		if (capital == Constant.TYPE_GROUP_CAPITAL_ADMIN
				|| capital == Constant.TYPE_GROUP_CAPITAL_OWNER
				|| owerId == userId) {
			// ɾ����������
			boolean result1 = GroupFileZipTableOperation.deleteBySerialNumber(
					serialNumber, groupId);
			boolean result2 = false;
			File file = new File(serialNumber);
			// ɾ���ļ�
			if (file.exists()) {
				result2 = file.delete();
			}
			if (result1 && result2) {
				resp.setType(Constant.TYPE_RETURN_INFO_REMOVE_FILE_SUCCESS);
				resp.setObj(serialNumber);
			}
		} else {
			resp.setType(Constant.TYPE_RETURN_INFO_REMOVE_FILE_FAIL);
			resp.setObj("ɾ��ʧ�ܣ����Ժ�����");
		}

		// ����ִ�н�����ͻ���
		chc.writeAndFlush(resp);
	}

	/**
	 * �����ݿ��ж�ȡ��Ϣ���ݣ����͵��ͻ���
	 */
	private void syncGroupMessage(ChannelHandlerContext chc, GetInfoReq req) {

		int groupId = req.getArg1();
		int from = 0;
		int to = 20;

		List<Message> messageList = null;
		do {
			messageList = MessageTableOperate.queryAllByGroupId(groupId, from,
					to);
			// ��ҳ��ѯ����from��ȡ��to��
			from = to;
			to = to + 20;

			// ��Map��ȡ��ֵ�����ֹͣͬ������ֱ�ӷ���
			if (!mIsSyncMessageMap.get(req.getArg1())) {
				System.out.println("����ͬ��");
				return;
			}

			for (Message message : messageList) {

				chc.writeAndFlush(message);
			}

			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ������ϲ�Ϊ�գ����Ҵ�С��Ϊ0������������ݱ���ȡ����
		} while (messageList != null && messageList.size() != 0);

		/*
		 * ͬ�������¼��ɣ�����֪ͨ���ͻ���
		 */
		ReturnInfoResp resp = new ReturnInfoResp();
		resp.setType(Constant.TYPE_RETURN_INFO_SYNC_COMPLETE);
		resp.setArg1(req.getArg1());
		chc.writeAndFlush(resp);
	}

	/**
	 * ��ʼͬ��
	 */
	private void startSyncMessage(ChannelHandlerContext chc, GetInfoReq req) {

		int groupId = req.getArg1();
		// ��ʼͬ������groupId��Ϊkeyֵ���룬ͬʱ��valueֵ��Ϊtrue
		mIsSyncMessageMap.put(groupId, true);
		syncGroupMessage(chc, req);
	}

	/**
	 * ֹͣͬ��
	 */
	private void stopSyncMessage(GetInfoReq req) {

		int groupId = req.getArg1();
		// ֹͣͬ������groupId��Ӧ��ֵ��Ϊfalse
		mIsSyncMessageMap.put(groupId, false);
	}
}
