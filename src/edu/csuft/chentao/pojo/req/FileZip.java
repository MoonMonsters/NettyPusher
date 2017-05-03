package edu.csuft.chentao.pojo.req;

/**
 * @author csuft.chentao
 */

import java.io.Serializable;
import java.util.Arrays;

/**
 * �ļ��ϴ�������
 */
public class FileZip implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �ļ���
	 */
	private String fileName;
	/**
	 * �ļ������к�,���������䵱�������У��������������ض�Ӧ���ļ��������Ǹ����ļ���
	 */
	private String serialNumber;
	/**
	 * �û��ǳ�
	 */
	private String nickname;
	/**
	 * �û�id
	 */
	private int userId;
	/**
	 * �ļ�����
	 */
	private byte[] zip;
	/**
	 * �ļ����ڵ�Ⱥid
	 */
	private int groupId;
	/**
	 * �ļ��ϴ�ʱ��
	 */
	private String time;
	/**
	 * �ļ���С
	 */
	private String fileSize;

	public FileZip(String fileName, String serialNumber, String nickname,
			int userId, byte[] zip, int groupId, String time, String fileSize) {
		super();
		this.fileName = fileName;
		this.serialNumber = serialNumber;
		this.nickname = nickname;
		this.userId = userId;
		this.zip = zip;
		this.groupId = groupId;
		this.time = time;
		this.fileSize = fileSize;
	}

	public FileZip() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte[] getZip() {
		return zip;
	}

	public void setZip(byte[] zip) {
		this.zip = zip;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileZip [fileName=" + fileName + ", serialNumber="
				+ serialNumber + ", nickname=" + nickname + ", userId="
				+ userId + ", zip=" + Arrays.toString(zip) + ", groupId="
				+ groupId + ", time=" + time + ", fileSize=" + fileSize + "]";
	}

}