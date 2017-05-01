package edu.csuft.chentao.pojo.req;

/**
 * @author csuft.chentao
 */

import java.io.Serializable;

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

    public FileZip(String fileName, String serialNumber, String nickname,
                   int userId, byte[] zip, int groupId, String time) {
        this.fileName = fileName;
        this.serialNumber = serialNumber;
        this.nickname = nickname;
        this.userId = userId;
        this.zip = zip;
        this.groupId = groupId;
        this.time = time;
    }

    public FileZip() {
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
}