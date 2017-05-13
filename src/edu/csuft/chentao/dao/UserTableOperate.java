/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.csuft.chentao.pojo.req.RegisterReq;
import edu.csuft.chentao.pojo.resp.RegisterResp;
import edu.csuft.chentao.pojo.resp.ReturnInfoResp;
import edu.csuft.chentao.pojo.resp.UserInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:39:50
 */
public class UserTableOperate {

	/**
	 * ����ע����Ϣ
	 * 
	 * @param registerReq
	 *            ע���û���Ϣ
	 * @return �Ƿ�ע��ɹ�
	 */
	@SuppressWarnings("resource")
	public static synchronized RegisterResp insert(RegisterReq req) {
		RegisterResp resp = new RegisterResp();

		Connection connection = DaoConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			/*
			 * �ж��û����Ƿ����
			 */
			ps = connection.prepareStatement("select " + UserTable.USERNAME
					+ " from " + UserTable.TABLE_NAME + " where "
					+ UserTable.USERNAME + " = ?");
			ps.setString(1, req.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				Logger.log("�û����Ѿ�����");
				resp.setType(Constant.TYPE_REGISTER_REPEAT_USERNAME);
				resp.setDescription(req.getUsername() + "ע��ʧ��");
				return resp;
			}

			Logger.log("�û��������ڣ�����ע��");

			// �û���idֵ�������ݿ���ȡ�����ֵ��Ȼ��+1
			int userid = Constant.DEFAULT_USERID;
			ps = null;
			rs = null;
			ps = connection.prepareStatement("select max(" + UserTable.USER_ID
					+ ") from " + UserTable.TABLE_NAME);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				userid = id == 0 ? userid : id + 1;
			}

			// ����ͷ��
			OperationUtil.saveHeadImage(req.getHeadImage(), userid);

			ps = null;
			rs = null;
			ps = connection.prepareStatement("insert into "
					+ UserTable.TABLE_ALL_FIELD + " values(?,?,?,?,?)");
			ps.setInt(1, userid);
			ps.setString(2, req.getUsername());
			ps.setString(3, req.getPassword());
			ps.setString(4, req.getNickname());
			ps.setString(5, req.getSignature());

			int count = ps.executeUpdate();
			// ע��ɹ�
			if (count > 0) {
				Logger.log("ע��ɹ�");
				resp.setType(Constant.TYPE_REGISTER_SUCCESS);
				resp.setDescription(req.getUsername() + "ע��ɹ�");
				resp.setUserid(userid);
			} else {
				Logger.log("ע��ʧ��");
				resp.setType(Constant.TYPE_REGISTER_REPEAT_USERNAME);
				resp.setDescription(req.getUsername() + "ע��ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �����û�id�����ǳ�
	 * 
	 * @param userid
	 *            �û�id
	 * @param nickname
	 *            �����µ��ǳ�
	 */
	public static synchronized ReturnInfoResp updateUserNickname(int userid,
			String nickname) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnInfoResp resp = new ReturnInfoResp();

		try {
			String sql = "update " + UserTable.TABLE_NAME + " set "
					+ UserTable.NICKNAME + " = ?" + " where "
					+ UserTable.USER_ID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setInt(2, userid);
			if (ps.executeUpdate() >= 1) {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_NICKNAME_SUCCESS);
				resp.setObj("�����ǳƳɹ�");
			} else {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_NICKNAME_FAIL);
				resp.setObj("�����ǳ�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �����û�id����ǩ��
	 * 
	 * @param userid
	 *            �û�id
	 * @param signature
	 *            ������ǩ��
	 */
	public static synchronized ReturnInfoResp updateUserSignature(int userid,
			String signature) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnInfoResp resp = new ReturnInfoResp();

		try {
			ps = connection.prepareStatement("update " + UserTable.TABLE_NAME
					+ " set " + UserTable.SIGNATURE + "= ?" + " where "
					+ UserTable.USER_ID + "=?");
			ps.setString(1, signature);
			ps.setInt(2, userid);
			if (ps.executeUpdate() >= 1) {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_SIGNATURE_SUCESS);
				resp.setObj("����ǩ���ɹ�");
			} else {
				resp.setType(Constant.TYPE_RETURN_INFO_UPDATE_SIGNATURE_FAIL);
				resp.setObj("����ǩ��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �����û���������ȡ���û���idֵ
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return �û���id
	 */
	public static int selectUserId(String username, String password) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int userid = -1;

		try {
			ps = connection.prepareStatement("select " + UserTable.USER_ID
					+ " from " + UserTable.TABLE_NAME + " where "
					+ UserTable.USERNAME + "=? and " + UserTable.PASSWORD
					+ "=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return userid;
	}

	/**
	 * ����û�����������
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
	 * @return �û�����
	 */
	public static UserInfoResp selectUserInfo(String username, String password) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserInfoResp resp = new UserInfoResp();

		try {
			ps = connection.prepareStatement("select " + UserTable.USER_ID
					+ "," + UserTable.NICKNAME + "," + UserTable.SIGNATURE
					+ "," + UserTable.USERNAME + " from "
					+ UserTable.TABLE_NAME + " where " + UserTable.USERNAME
					+ "=? and " + UserTable.PASSWORD + "=?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if (rs.next()) {
				int userid = rs.getInt(1);
				String nickname = rs.getString(2);
				byte[] headImage = OperationUtil.getHeadImage(userid);
				String signature = rs.getString(3);
				String name = rs.getString(4);
				resp.setNickname(nickname);
				resp.setHeadImage(headImage);
				resp.setSignature(signature);
				resp.setUserid(userid);
				resp.setUsername(name);
			} else {
				resp.setUserid(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �����û�id�õ��û���Ϣ
	 * 
	 * @param userId
	 *            �û�id
	 * @return �û���Ϣ
	 */
	public static UserInfoResp selectUserInfoWithUserId(int userId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserInfoResp resp = new UserInfoResp();
		try {
			String sql = "select " + UserTable.USER_ID + ","
					+ UserTable.NICKNAME + "," + UserTable.SIGNATURE + ","
					+ UserTable.USERNAME + " from " + UserTable.TABLE_NAME
					+ " where " + UserTable.USER_ID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				// �û�id
				resp.setUserid(rs.getInt(1));
				// �ǳ�
				resp.setNickname(rs.getString(2));
				// ǩ��
				resp.setSignature(rs.getString(3));
				// ͷ��
				resp.setHeadImage(OperationUtil.getHeadImage(resp.getUserid()));
				resp.setUsername(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * �����û�id�õ��û���
	 */
	public static String getUsernameWithUserId(int userId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String name = null;

		try {
			String sql = "select " + UserTable.USERNAME + " from "
					+ UserTable.TABLE_NAME + " where " + UserTable.USER_ID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) { // �����ѯ������
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return name;
	}

	/**
	 * �ж��û��������ݱ����Ƿ����
	 * 
	 * @param userId
	 *            �û�id
	 * @return �Ƿ����
	 */
	public static boolean isExitUserWithUserId(int userId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean result = false;

		try {
			String sql = "select " + UserTable.USERNAME + " from "
					+ UserTable.TABLE_NAME + " where " + UserTable.USER_ID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String username = rs.getString(1);
				Logger.log("isExitUserWithUserId-->" + username);
				if (username != null) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return result;
	}

	/**
	 * �ж��û�id�������Ƿ�ƥ��
	 * 
	 * @return ��ƥ���򷵻�true
	 */
	public static boolean isRightByUserIdAndPassword(int userId, String password) {
		boolean result = false;
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select * from " + UserTable.TABLE_NAME + " where "
					+ UserTable.USER_ID + " = ? and " + UserTable.PASSWORD
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
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
	 * �����û�����
	 * 
	 * @return
	 */
	public static boolean updateUserPassword(int userId, String newPassword) {
		boolean result = false;
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "update " + UserTable.TABLE_NAME + " set "
					+ UserTable.PASSWORD + " = ? where " + UserTable.USER_ID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, userId);
			if (ps.executeUpdate() > 0) { // ����
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return result;
	}

}
