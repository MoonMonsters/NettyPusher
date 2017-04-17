package edu.csuft.chentao.pojo.req;

import java.io.Serializable;

/**
 * ���ֻ��˺ͷ���˴���Ĺ�����Ϣ
 */
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ��ţ�Ԥ��������Ժ���ɾ�������õ���
     */
    private String serialnumber;

    /**
     * �������
     */
    private String title;

    /**
     * ��������
     */
    private String content;

    /**
     * ���������֣�����Ԥ�����֣���������û�id���û����ݿ���û������������ʹ�ø�����
     */
    private String username;

    /**
     * �������û�id
     */
    private int userid;

    /**
     * Ⱥid
     */
    private int groupid;

    /**
     * ����ʱ��
     */
    private String time;

    public Announcement() {
    }

    public Announcement(String serialnumber, String title, String content, String username, int userid, String time) {
        this.serialnumber = serialnumber;
        this.title = title;
        this.content = content;
        this.username = username;
        this.userid = userid;
        this.time = time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
