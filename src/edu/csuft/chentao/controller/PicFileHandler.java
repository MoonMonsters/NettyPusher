/**
 * ����ͼƬ����
 */
package edu.csuft.chentao.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.csuft.chentao.pojo.req.PicFile;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����9:23:51
 */
public class PicFileHandler implements Handler {

	private static final String PATH = "./picfile";

	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		PicFile picFile = (PicFile) object;
		FileOutputStream fos = null;

		try {
			//���ͼƬ�Ѿ����ڣ���ô���£�ɾ�������´��룩
			File file = new File(PATH, picFile.getFilename());
			if (file.exists()) {
				file.delete();
			}
			fos = new FileOutputStream(file);
			fos.write(picFile.getBuf(), 0, picFile.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
