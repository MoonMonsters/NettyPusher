/**
 * 
 */
package edu.csuft.chentao.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.csuft.chentao.controller.HeadImageHandler;
import edu.csuft.chentao.pojo.req.HeadImage;

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
	public static void saveHeadImage(HeadImage headImage) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				new HeadImageHandler().handle(null, headImage);
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
	 * �����ļ������HeadImage����
	 * @param filename �ļ���
	 */
	public static HeadImage getHeadImage(String filename) {
		HeadImage headImage = new HeadImage();

		try {
			//��headimageĿ¼�£����filename�ļ�
			File file = new File("./headimage", filename);
			FileInputStream fis = new FileInputStream(file);
			int length = fis.available();
			byte[] buf = new byte[length];
			fis.read(buf, 0, length);
			headImage.setBuf(buf);
			headImage.setFilename(filename);
			headImage.setLength(length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return headImage;
	}
}
