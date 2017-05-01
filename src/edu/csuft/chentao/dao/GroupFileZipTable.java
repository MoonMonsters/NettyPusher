/**
 * 
 */
package edu.csuft.chentao.dao;

import edu.csuft.chentao.pojo.req.FileZip;

/**
 * @author csuft.chentao
 *
 * 2017��5��1�� ����9:52:31
 */
public class GroupFileZipTable {
	
	public static final String TABLE_NAME = "group_file_zip";
	public static final String INSERT_ALL_FIELD = "group_file_zip(filename,serial_number,group_id,user_id,nickname,time)";
	public static final String QUERY_ALL_FIELD = "filename,serial_number,group_id,user_id,nickname,time";
	public static final String FILENAME = "filename";
	public static final String NICKNAME = "nickname";
	public static final String SERIAL_NUMBER = "serial_number";
	public static final String USER_ID = "user_id";
	public static final String GROUP_ID = "group_id";
	public static final String TIME = "time";
	
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
     * �ļ����ڵ�Ⱥid
     */
    private int groupId;
    /**
     * �ļ��ϴ�ʱ��
     */
    private String time;
	public GroupFileZipTable(String fileName, String serialNumber,
			String nickname, int userId, int groupId, String time) {
		super();
		this.fileName = fileName;
		this.serialNumber = serialNumber;
		this.nickname = nickname;
		this.userId = userId;
		this.groupId = groupId;
		this.time = time;
	}
    
    public GroupFileZipTable(){}
    
    
    
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

	/**
     * ��FileZipת����GroupFileZipTable���������������ݿ����
     * @param fileZip ������ļ�����
     * @return GroupFileZipTable���������������ݱ�
     */
    public static GroupFileZipTable copyToGroupFileZipTable(FileZip fileZip){
    	GroupFileZipTable table = new GroupFileZipTable();
    	
    	table.setFileName(fileZip.getFileName());
    	table.setGroupId(fileZip.getGroupId());
    	table.setNickname(fileZip.getNickname());
    	table.setSerialNumber(fileZip.getSerialNumber());
    	table.setTime(fileZip.getTime());
    	table.setUserId(fileZip.getUserId());
    	
    	return table;
    }
    
    /**
     * ��Table��������ݽ��ת����FileZip�����������͵��ͻ���
     * @param table ���ݱ����
     * @return �ɷ��Ͷ���
     */
    public static FileZip copyToFileZip(GroupFileZipTable table,byte[] content, boolean attachContent){
    	FileZip fileZip = new FileZip();
    	
    	fileZip.setFileName(table.getFileName());
    	fileZip.setGroupId(table.getGroupId());
    	fileZip.setNickname(table.getNickname());
    	fileZip.setSerialNumber(table.getSerialNumber());
    	fileZip.setTime(table.getTime());
    	fileZip.setUserId(table.getUserId());
    	if(attachContent){	//�����Ҫ����ļ����ݣ������
    		fileZip.setZip(content);
    	}
    	
    	return fileZip;
    }
}
