/**
 * ����GetInfoHandler���ݣ�����������ָ����Ĵӿͻ��˷����������ݣ���type�����ֿ���
 */
package edu.csuft.chentao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import edu.csuft.chentao.dao.GroupFileZipTableOperation;
import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.FileZip;
import edu.csuft.chentao.pojo.req.GetInfoReq;
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

	public void handle(ChannelHandlerContext chc, Object object) {
		GetInfoReq req = (GetInfoReq) object;

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
		}
	}

	/**
	 * ���group�ļ��б�
	 */
	private void getGroupFileList(ChannelHandlerContext chc, GetInfoReq req) {
		List<FileZip> fileZipList = GroupFileZipTableOperation.queryAll(req
				.getArg1());
		if (fileZipList.size() == 0) {
			ReturnInfoResp resp = new ReturnInfoResp(
					Constant.TYPE_RETURN_INFO_FILE_LIST_SIZE_0, "��Ⱥ����û���κ��ļ�");
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

		File file = new File(serialNumber);
		FileInputStream fis = null;
		try {
			byte[] content = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			// ��ȡ�ļ�����
			fis.read(content);

			//�����ݱ��л������
			FileZip fz = GroupFileZipTableOperation
					.queryBySerialNumber(serialNumber);
			fz.setZip(content);

			// ���͵��ͻ���
			chc.writeAndFlush(fz);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
