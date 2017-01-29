/**
 * ����������Ϣ
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.UserTableOperate;
import edu.csuft.chentao.pojo.req.UpdateUserInfoReq;
import edu.csuft.chentao.pojo.resp.ReturnMessageResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:25:49
 */
public class UpdateUserInfoHandler implements Handler {

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {

		Logger.log("UpdateUserInfoHandler-->������Ϣ����");

		UpdateUserInfoReq req = (UpdateUserInfoReq) object;
		ReturnMessageResp resp = null;

		/*
		 * req.getContent�Ͱ�����ǩ�����ǳ��������ͣ��ڿͻ��˾����ú�
		 */
		if (req.getType() == Constant.TYPE_UPDATE_NICKNAME) { // �����ǳ�
			resp = UserTableOperate.updateUserNickname(req.getUserid(),
					req.getContent());
		} else if (req.getType() == Constant.TYPE_UPDATE_SIGNATURE) { // ����ǩ��
			resp = UserTableOperate.updateUserSignature(req.getUserid(),
					req.getContent());
		} else if (req.getType() == Constant.TYPE_UPDATE_HEADIMAGE) { // ����ͷ��
			// ����ͼƬ��
			// ���±���һ��ͷ��
			resp = new ReturnMessageResp();
			try {
				OperationUtil
						.saveHeadImage(req.getHeadImage(), req.getUserid());
				resp.setType(Constant.TYPE_RETURN_MESSAGE_UPDATE_HEAD_IMAGE_SUCCESS);
				resp.setDescription("ͷ����ĳɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				resp.setType(Constant.TYPE_RETURN_MESSAGE_UPDATE_HEAD_IMAGE_FAIL);
				resp.setDescription("ͷ�����ʧ��");
			}
		}

		Logger.log("ReturnMessageResp-->���ظ��º����Ϣ");

		// ���ؽ��
		chc.writeAndFlush(resp);
	}

}