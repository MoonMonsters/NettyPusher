/**
 * ����ͷ��
 */
package edu.csuft.chentao.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.csuft.chentao.pojo.req.HeadImage;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 * 2016��12��9�� ����9:21:49
 */
public class HeadImageHandler implements Handler {

	//����ͷ�񵽵�ǰ��Ŀ��headimage�ļ�����
	private static final String PATH = "./headimage";
	
	@Override
	public void handle(ChannelHandlerContext chc, Object object) {
		HeadImage headImage = (HeadImage)object;
		FileOutputStream fos = null;
		
		try {
			File file = new File(PATH,headImage.getFilename());
			//����ͷ�����
			if(file.exists()){
				file.delete();
			}
			fos = new FileOutputStream(file);
			fos.write(headImage.getBuf(), 0, headImage.getLength());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
