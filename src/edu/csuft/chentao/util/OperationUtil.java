/**
 * 
 */
package edu.csuft.chentao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
	public static void saveHeadImage(byte[] headImage,int fileId) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					File file = new File("./headimage",String.valueOf(fileId));
					
					//����Ѿ����ڣ���ɾ��
					if(file.exists()){
						file.delete();
					}
					
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(headImage);
					fos.close();
				}catch(Exception e){
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
	 * @param userId �û�id
	 * @return �ļ���byte����
	 */
	public static byte[] getHeadImage(int fileId){
		byte[] buf = null;
		try{
			File file = new File(Constant.PATH,String.valueOf(fileId));
			FileInputStream fis = new FileInputStream(file);
			int length = fis.available();
			buf = new byte[length];
			fis.read(buf, 0, length);
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return buf;
	}
}
