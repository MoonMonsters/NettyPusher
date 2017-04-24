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
     * �ļ���
     */
    private String fileName;
    /**
     * �ļ������к�
     */
    private String serialNumber;
    /**
     * �û���
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

    public FileZip(String fileName, String serialNumber, String nickname, int userId, byte[] zip) {
        this.fileName = fileName;
        this.serialNumber = serialNumber;
        this.nickname = nickname;
        this.userId = userId;
        this.zip = zip;
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
}