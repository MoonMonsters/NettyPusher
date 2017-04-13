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
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.pojo.resp.UserCapitalResp;
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
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @param userId
	 *            �û�id
	 * @param capital
	 *            ���ֵ
	 * @return
	 */
	public static synchronized boolean insert(int groupId, int userId,
			int capital) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// ��������
			ps = connection.prepareCall("insert into "
					+ GroupUserTable.GROUPUSERTABLE_ALL_FIELD
					+ " values(?,?,?)");
			ps.setInt(1, groupId);
			ps.setInt(2, userId);
			ps.setInt(3, capital);
			System.out.println(groupId + "-->" + userId + "-->" + capital);
			if (ps.executeUpdate() > 0) { // ִ�гɹ�
				return true;
			} else { // ִ��ʧ��
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return false;
	}

	/**
	 * �Ƴ�����
	 */
	public static synchronized ReturnInfoResp remove(GroupOperationReq req) {

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnInfoResp resp = new ReturnInfoResp();

		try {

			if (isExit(req.getGroupId(), req.getUserId1())) { // ���Ⱥid���û�id����
				ps = connection.prepareStatement("delete from "
						+ GroupUserTable.GROUPUSERTABLE + " where "
						+ GroupUserTable.GROUPID + "=? and "
						+ GroupUserTable.USERID + "=?");
				ps.setInt(1, req.getGroupId());
				ps.setInt(2, req.getUserId1());
				rs = ps.executeQuery();
				if (rs.next()) {
					resp.setType(Constant.TYPE_RETURN_INFO_SUCCESS);
					resp.setDescription("�˳�Ⱥ�ɹ�");
				} else {
					resp.setType(Constant.TYPE_RETURN_INFO_FAIL);
					resp.setDescription("�˳�Ⱥʧ��");
				}
			} else {
				resp.setType(Constant.TYPE_RETURN_INFO_FAIL);
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
	public static boolean isExit(int groupid, int userid) {
		boolean isExit = false;

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		// �������û�����Ⱥ֮ǰ�����ж��û���Ⱥ�Ƿ���ڣ���������ڣ���ֱ�ӷ���
		if (!UserTableOperate.isExitUserWithUserId(userid)
				|| !GroupTableOperate.isExitGroupWithGroupId(groupid)) {
			return isExit;
		}

		try {
			ps = connection.prepareStatement("select * from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.GROUPID + "= ? and "
					+ GroupUserTable.USERID + "= ?");
			ps.setInt(1, groupid);
			ps.setInt(2, userid);
			rs = ps.executeQuery();
			if (rs.next()) { // ��Ⱥ���Ѿ�����
				Logger.log("isExit-->"+userid+"�Ѿ�����");
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
	 * ����Ⱥid�õ���Ⱥ�����еĳ�Աid���������Ϣ
	 * 
	 * @param groupid
	 *            Ⱥid
	 * @return ��Աid�б�
	 */
	public static List<UserCapitalResp> selectIdAndCapitalInUserWithGroupId(
			int groupid) {
		List<UserCapitalResp> userCapitalList = new ArrayList<UserCapitalResp>();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select userid,capital from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.GROUPID + "=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupid);
			rs = ps.executeQuery();
			while (rs.next()) {
				userCapitalList.add(new UserCapitalResp(rs.getInt(1), rs
						.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return userCapitalList;
	}

	/**
	 * ����Ⱥid��ȡ��Ⱥ�������û���id
	 * 
	 * @param groupId
	 *            Ⱥid
	 */
	public static List<Integer> selectAllUserIdsWithGroupId(int groupId) {
		List<Integer> userIdList = new ArrayList<Integer>();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select userid from " + GroupUserTable.GROUPUSERTABLE
					+ " where " + GroupUserTable.GROUPID + "=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			while (rs.next()) {
				userIdList.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return userIdList;
	}

	/**
	 * �����Ñ�id�õ����е�Ⱥ��id
	 * 
	 * @param userId
	 *            �Ñ�id
	 * @return ԓ�Ñ�����Ⱥ��id����
	 */
	public static List<Integer> selectGroupIdsWithUserId(int userId) {
		List<Integer> groupIdList = new ArrayList<Integer>();
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select " + GroupUserTable.GROUPID + " from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.USERID + " = ? ";
			ps = connection.prepareStatement(sql);
			// �����û�id
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupIdList.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return groupIdList;
	}

	/**
	 * �����û������
	 * 
	 * @param userId
	 *            ��Ҫ���µ��û�id
	 * @param groupId
	 *            Ⱥid
	 * @param capital
	 *            �µ����
	 */
	public static ReturnInfoResp updateCapital(int userId, int groupId,
			int capital) {
		ReturnInfoResp resp = new ReturnInfoResp();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "update " + GroupUserTable.GROUPUSERTABLE + " set "
					+ GroupUserTable.CAPITAL + "=? where "
					+ GroupUserTable.USERID + "=? and "
					+ GroupUserTable.GROUPID + "=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, capital);
			ps.setInt(2, userId);
			ps.setInt(3, groupId);
			if (ps.executeUpdate() > 0) {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_USER_CAPITAL_SUCCESS);
				resp.setDescription("���³ɹ�");
			} else {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_USER_CAPITAL_FAIL);
				resp.setDescription("����ʧ�ܣ����Ժ�����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �˳�Ⱥ
	 * 
	 * @return ��������ɹ����򷵻�true�����򷵻�false
	 */
	public static boolean exitGroup(int groupId, int userId) {

		boolean result = false;

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		/**
		 * ��������ڣ���ֱ�ӷ���
		 */
		if (!isExit(groupId, userId)) {
			return result;
		}

		try {
			String sql = "delete from " + GroupUserTable.GROUPUSERTABLE
					+ " where " + GroupUserTable.GROUPID + " = ? and "
					+ GroupUserTable.USERID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			ps.setInt(2, userId);
			if (ps.executeUpdate() > 0) { // ִ��ɾ������
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return result;
	}

	/**
	 * ����Ⱥid�����ֵ����ȡ��Ӧ���û�id��Ⱥ�����߹���Ա��
	 */
	public static List<Integer> getCapital_0_WithGroupId(int groupId) {
		List<Integer> userIdList = new ArrayList<Integer>();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select " + GroupUserTable.USERID + " from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.GROUPID + " = ? and ("
					+ GroupUserTable.CAPITAL + " = ? or "
					+ GroupUserTable.CAPITAL + " = ?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			ps.setInt(2, Constant.TYPE_GROUP_CAPITAL_ADMIN);
			ps.setInt(3, Constant.TYPE_GROUP_CAPITAL_OWNER);
			rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				userIdList.add(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return userIdList;
	}

	/**
	 * �����û�id������ֵ
	 * 
	 * @param userId
	 *            �û�id
	 * @return ���ֵ
	 */
	public static int getCapitalWithUserIdAndGroupId(int groupId, int userId) {
		int capital = -1;

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select " + GroupUserTable.CAPITAL + " from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.USERID + " = ? and "
					+ GroupUserTable.GROUPID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, groupId);
			rs = ps.executeQuery();
			if (rs.next()) {
				capital = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return capital;
	}

	/**
	 * ����Ⱥidɾ�������������
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean removeAllUserWithGroupId(int groupId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean result = false;

		try {
			String sql = "delete from " + GroupUserTable.GROUPUSERTABLE
					+ " where " + GroupUserTable.GROUPID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			if (ps.executeUpdate() > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return result;
	}

	/**
	 * ����Ⱥid�õ������û���id
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @return ��ӦȺ�û�id����
	 */
	public static List<Integer> getAllUserIdWithGroupId(int groupId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Integer> userIdList = new ArrayList<Integer>();

		try {
			String sql = "select " + GroupUserTable.USERID + " from "
					+ GroupUserTable.GROUPUSERTABLE + " where "
					+ GroupUserTable.GROUPID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				userIdList.add(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return userIdList;
	}

}
