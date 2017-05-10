/**
 * 
 */
package edu.csuft.chentao.util;

import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;

/**
 * @author csuft.chentao
 *
 *         2016��12��10�� ����8:10:06
 */
public class OperationUtil {

	/**
	 * ��ͷ�񱣴浽����
	 * 
	 * @param headImage
	 *            ͷ����
	 */
	public static void saveHeadImage(final byte[] headImage, final int fileId) {
		new Thread(new Runnable() {
			public void run() {
				try {
					File file = new File("./headimage", String.valueOf(fileId));

					// ����Ѿ����ڣ���ɾ��
					if (file.exists()) {
						file.delete();
					}

					FileOutputStream fos = new FileOutputStream(file);
					fos.write(headImage);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/** �ر����ݿ��PreparedStatement��ResultSet���� */
	public static void closeDataConnection(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����û�id���ļ����еõ����ļ�
	 * 
	 * @param userId
	 *            �û�id
	 * @return �ļ���byte����
	 */
	public static byte[] getHeadImage(int fileId) {
		byte[] buf = null;
		try {
			File file = new File(Constant.PATH, String.valueOf(fileId));
			FileInputStream fis = new FileInputStream(file);
			int length = fis.available();
			buf = new byte[length];
			fis.read(buf, 0, length);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf;
	}

	/**
	 * ����0.5��
	 */
	public static void sleepFor500() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����5��
	 */
	public static void sleepFor5000() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����Ⱥid�õ�Ⱥ��
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @return Ⱥ����
	 */
	public static String getGroupNameWithGroupId(int groupId) {

		return GroupTableOperate.getGroupNameWithGroupId(groupId);
	}

	/**
	 * ����ReturnInfoResp������Ϣ
	 * 
	 * @param chc
	 *            ������Ϣ����
	 * @param type
	 *            ����
	 * @param description
	 *            ����
	 */
	public static void sendReturnInfoResp(ChannelHandlerContext chc, int type,
			String description) {
		ReturnInfoResp resp = new ReturnInfoResp();
		resp.setType(type);
		resp.setObj(description);
		chc.writeAndFlush(resp);
		Logger.log("����ReturnInfoResp��Ϣ----"+type);
	}

}
