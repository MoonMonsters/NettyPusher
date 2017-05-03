/**
 * 
 */
package edu.csuft.chentao.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import edu.csuft.chentao.dao.GroupFileZipTable;
import edu.csuft.chentao.dao.GroupFileZipTableOperation;
import edu.csuft.chentao.pojo.req.FileZip;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 * 
 *         2017��4��23�� ����12:03:13
 */
public class FileZipHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		FileZip fz = (FileZip) object;

		System.out.println("fz.getFileName = " + fz.getFileName());
		System.out.println("fz.fileLength = " + fz.getZip().length);
		System.out.println("fz.serailNumber = " + fz.getSerialNumber());
		// �����ļ�
		File file = new File(fz.getSerialNumber());
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
			fos.write(fz.getZip());

			//�������ݱ���
			GroupFileZipTable table = GroupFileZipTable.copyToGroupFileZipTable(fz);
			GroupFileZipTableOperation.insert(table);
			
			/*
			 * �ϴ���Ϻ󣬽����ļ���Ϣ���͵��ͻ���ȥ
			 */
			//�ļ���С
			fz.setFileSize(String.valueOf(fz.getZip().length));
			//�������ļ�����
			fz.setZip(null);
			
			//����
			chc.writeAndFlush(fz);
			
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

}
