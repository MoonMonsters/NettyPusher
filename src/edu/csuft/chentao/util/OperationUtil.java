/**
 * 
 */
package edu.csuft.chentao.util;

import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	 * �����ļ���
	 * 
	 * @param path
	 *            ·��
	 */
	private static void createDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

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
					createDirectory(Constant.PATH_HEAD_IMAGE);
					File file = new File(Constant.PATH_HEAD_IMAGE,
							String.valueOf(fileId));

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
			File file = new File(Constant.PATH_HEAD_IMAGE,
					String.valueOf(fileId));
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
	 * ����ͼƬ���кţ��õ�ͼƬ��Դ
	 * 
	 * @param imageSerialNumber
	 *            ͼƬ���к�
	 * @return ͼƬ��Դ
	 */
	public static byte[] getMessageImage(int groupId, String imageSerialNumber) {
		byte[] buf = null;
		try {
			File file = new File(Constant.PATH_MESSAGE_IMAGE + "/" + groupId,
					imageSerialNumber);
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
	 * ����ͼƬ��Դ
	 * 
	 * @param groupId
	 * @param imageSerialNumber
	 * @param image
	 */
	public static void saveMessageImage(final int groupId,
			final String imageSerialNumber, final byte[] image) {
		new Thread(new Runnable() {
			public void run() {
				try {

					createDirectory(Constant.PATH_MESSAGE_IMAGE + "/" + groupId);

					File file = new File(Constant.PATH_MESSAGE_IMAGE + "/"
							+ groupId, imageSerialNumber);

					// ����ļ������ڣ��򴴽�
					if (!file.exists()) {
						file.createNewFile();
					}

					FileOutputStream fos = new FileOutputStream(file);
					fos.write(image);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * �����ϴ���Ⱥ�ļ�
	 * 
	 * @param groupId
	 *            ȺID
	 * @param serialNumber
	 *            �ļ����к�
	 * @param buf
	 *            ��Դ
	 */
	public static void saveGroupFile(int groupId, String serialNumber,
			byte[] buf) {

		createDirectory(Constant.PATH_FILE + "/" + groupId);

		// �����ļ�
		File file = new File(Constant.PATH_FILE + "/" + groupId, serialNumber);
		// ��������ڣ����½�
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileOutputStream fos = null;
		try {
			// д��
			fos = new FileOutputStream(file);
			fos.write(buf);
		} catch (FileNotFoundException e) {
			System.out.println("�ļ�δ�ҵ�");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO������");
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���Ⱥ�ļ�
	 * 
	 * @param groupId
	 *            ȺID
	 * @param serialNumber
	 *            �ļ����к�
	 * 
	 */
	public static byte[] getGroupFile(int groupId, String serialNumber) {
		byte[] buf = null;

		File file = new File(Constant.PATH_FILE + "/" + groupId, serialNumber);
		FileInputStream fis = null;
		try {
			buf = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			// ��ȡ�ļ�����
			fis.read(buf);
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
		Logger.log("����ReturnInfoResp��Ϣ----" + type);
	}

	/**
	 * �õ����16λ���к�
	 * 
	 * @return ������к�
	 */
	public static String getSerialNumber() {
		String serial = "abcdefghijklmnopqrstuvwxyz0123456";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			sb.append(serial.charAt((int) (Math.random() * serial.length())));
		}

		return sb.toString();
	}
}
