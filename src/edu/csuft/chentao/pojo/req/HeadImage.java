package edu.csuft.chentao.pojo.req;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ͷ��
 * @author cusft.chentao
 *
 */
public class HeadImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** �ļ��� */
	String filename;// (�ڷ���˸����û�idת��md5��洢)
	/** �ļ����� */
	int length;
	/** ���� */
	byte[] buf;

	public HeadImage(String filename, int length, byte[] buf) {
		super();
		this.filename = filename;
		this.length = length;
		this.buf = buf;
	}

	public HeadImage() {
		super();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getBuf() {
		return buf;
	}

	public void setBuf(byte[] buf) {
		this.buf = buf;
	}

	@Override
	public String toString() {
		return "HeadImageReq [filename=" + filename + ", length=" + length
				+ ", buf=" + Arrays.toString(buf) + "]";
	}
}
