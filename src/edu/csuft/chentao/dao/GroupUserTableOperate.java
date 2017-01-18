/**
 * groupuser���ݱ���ز���
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.csuft.chentao.pojo.req.GroupOperationReq;
import edu.csuft.chentao.pojo.resp.ReturnMessageResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:39:40
 */
public class GroupUserTableOperate {

	/**
	 * ��������
	 */
	public static synchronized ReturnMessageResp insert(GroupOperationReq req) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnMessageResp resp = new ReturnMessageResp();

		try {
			if (isExit(connection, req.getGroupid(), req.getUserid())) { // ����Ⱥʧ��
				Logger.log("���û�����Ⱥ��");
				resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
				resp.setDescription("����Ⱥʧ��");
				return resp;
			}

			// ��������
			ps = connection.prepareCall("insert into "
					+ GroupUserTable.GROUPUSERTABLE_ALL_FIELD + " values(?,?)");
			ps.setInt(1, req.getGroupid());
			ps.setInt(2, req.getUserid());
			if (ps.execute()) { // ִ�гɹ�
				resp.setType(Constant.TYPE_RETURN_MESSAGE_SUCCESS);
				resp.setDescription("����Ⱥ�ɹ�");
			} else { // ִ��ʧ��
				resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
				resp.setDescription("����Ⱥʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;

	}

	/**
	 * �Ƴ�����
	 */
	public static synchronized ReturnMessageResp remove(GroupOperationReq req) {

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnMessageResp resp = new ReturnMessageResp();

		try {

			if (isExit(connection, req.getGroupid(), req.getUserid())) {
				ps = connection.prepareStatement("delete from "
						+ GroupUserTable.GROUPUSERTABLE + " where "
						+ GroupUserTable.GROUPID + "=? and "
						+ GroupUserTable.USERID + "=?");
				ps.setInt(1, req.getGroupid());
				ps.setInt(2, req.getUserid());
				if (ps.execute()) {
					resp.setType(Constant.TYPE_RETURN_MESSAGE_SUCCESS);
					resp.setDescription("�˳�Ⱥ�ɹ�");
				} else {
					resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
					resp.setDescription("�˳�Ⱥʧ��");
				}
			} else {
				resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
				resp.setDescription("�˳�Ⱥʧ��");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;

	}

	/**
	 * �жϸ������������ݱ����Ƿ����
	 * 
	 * @param connection
	 *            Connection�����������ݿ�
	 * @param groupid
	 *            Ⱥid
	 * @param userid
	 *            �û�id
	 * @return �������Ƿ����
	 */
	public static boolean isExit(Connection connection, int groupid, int userid) {
		boolean isExit = false;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("select * from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.GROUPID + "=? and "
					+ GroupUserTable.USERID + "=?");
			ps.setInt(1, groupid);
			ps.setInt(2, userid);
			rs = ps.executeQuery();
			if (rs.next()) { // ����Ⱥʧ��
				isExit = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return isExit;
	}

	/**
	 * ����Ⱥid�õ���Ⱥ�����еĳ�Ա
	 * @param groupid Ⱥid
	 * @return ��Աid�б�
	 */
	public static List<Integer> selectUserIdsWithGroupId(int groupid) {
		List<Integer> list = new ArrayList<Integer>();
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select userid from " + GroupUserTable.GROUPUSERTABLE
					+ " where " + GroupUserTable.GROUPID + "=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupid);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return list;
	}

	/**
	 * �����Ñ�id�õ����е�Ⱥ��id
	 * @param userId �Ñ�id
	 * @return ԓ�Ñ�����Ⱥ��id����
	 */
	public static List<Integer> selectGroupIdsWithUserId(int userId){
		List<Integer> groupIdList = new ArrayList<Integer>();
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			String sql = "select " + GroupUserTable.GROUPID+" from " +
					GroupUserTable.GROUPUSERTABLE + " where "+GroupUserTable.USERID + " = ? ";
			ps = connection.prepareStatement(sql);
			//�����û�id
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				groupIdList.add(rs.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			OperationUtil.closeDataConnection(ps, rs);
		}
		
		return groupIdList;
	}
	
}
