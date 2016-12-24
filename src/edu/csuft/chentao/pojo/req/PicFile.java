package edu.csuft.chentao.pojo.req;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ����ͼƬ
 * 
 * @author cusft.chentao
 *
 */
public class PicFile implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** �ļ��� */
	String filename;// (�������16λ�ַ�����ͼƬ����)
	/** �ļ����� */
	int length;
	/** ���� */
	byte[] buf;

	public PicFile(String filename, int length, byte[] buf) {
		super();
		this.filename = filename;
		this.length = length;
		this.buf = buf;
	}

	public PicFile() {
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
		return "PicFileReq [filename=" + filename + ", length=" + length
				+ ", buf=" + Arrays.toString(buf) + "]";
	}

}
