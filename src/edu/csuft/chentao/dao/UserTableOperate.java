/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.csuft.chentao.pojo.req.HeadImage;
import edu.csuft.chentao.pojo.req.RegisterReq;
import edu.csuft.chentao.pojo.resp.RegisterResp;
import edu.csuft.chentao.pojo.resp.ReturnMessageResp;
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
	public static synchronized RegisterResp insert(RegisterReq registerReq) {
		RegisterResp resp = new RegisterResp();

		Connection connection = DaoConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			/*
			 * �ж��û����Ƿ����
			 */
			ps = connection.prepareStatement("select " + UserTable.USERNAME
					+ " from " + UserTable.USERTABLE + " where username = ?");
			ps.setString(1, registerReq.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				Logger.log("�û����Ѿ�����");
				resp.setType(Constant.REGISTER_TYPE_REPEAT_USERNAME);
				resp.setDescription(registerReq.getUsername() + "ע��ʧ��");
				return resp;
			}

			// �û���idֵ�������ݿ���ȡ�����ֵ��Ȼ��+1
			int userid = Constant.DEFAULT_USERID;
			ps = connection.prepareStatement("select max(userid) from "
					+ UserTable.USERTABLE);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				userid = id == 0 ? userid : id + 1;
			}

			String filename = registerReq.getHeadImage().getFilename();
			// ����ƴ�����֣����û���idֵ��Ϊ�û�ͷ��ͼƬ����
			filename = userid + filename.substring(filename.lastIndexOf("."));
			registerReq.getHeadImage().setFilename(filename);

			// ���޸���ͼƬ���ƺ󣬱����ļ�
			OperationUtil.saveHeadImage(registerReq.getHeadImage());

			ps = connection.prepareStatement("insert into "
					+ UserTable.USERTABLE_ALL_FIELD + " values(?,?,?,?,?,?)");
			ps.setInt(1, userid);
			ps.setString(2, registerReq.getUsername());
			ps.setString(3, registerReq.getPassword());
			ps.setString(4, registerReq.getNickname());
			ps.setString(5, registerReq.getHeadImage().getFilename());
			ps.setString(6, registerReq.getSignature());

			// ע��ɹ�
			if (ps.execute()) {
				resp.setType(Constant.REGISTER_TYPE_SUCCESS);
				resp.setDescription(registerReq.getUsername() + "ע��ɹ�");
				resp.setUserid(userid);
			} else {
				resp.setType(Constant.REGISTER_TYPE_REPEAT_USERNAME);
				resp.setDescription(registerReq.getUsername() + "ע��ʧ��");
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
	public static synchronized ReturnMessageResp updateUserNickname(int userid,
			String nickname) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnMessageResp resp = new ReturnMessageResp();

		try {
			String sql = "update " + UserTable.USERTABLE + " set "
					+ UserTable.NICKNAME + " = ?" + " where "
					+ UserTable.USERID + " = ?";
			Logger.log(sql);
			ps = connection.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setInt(2, userid);
			if (ps.executeUpdate() >= 1) {
				resp.setType(Constant.TYPE_RETURN_MESSAGE_SUCCESS);
				resp.setDescription("�����ǳƳɹ�");
			} else {
				resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
				resp.setDescription("�����ǳ�ʧ��");
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
	public static synchronized ReturnMessageResp updateUserSignature(
			int userid, String signature) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ReturnMessageResp resp = new ReturnMessageResp();

		try {
			ps = connection.prepareStatement("update " + UserTable.USERTABLE
					+ " set " + UserTable.SIGNATURE + "= ?" + " where "
					+ UserTable.USERID + "=?");
			ps.setString(1, signature);
			ps.setInt(2, userid);
			if (ps.executeUpdate() >= 1) {
				resp.setType(Constant.TYPE_RETURN_MESSAGE_SUCCESS);
				resp.setDescription("����ǩ���ɹ�");
			} else {
				resp.setType(Constant.TYPE_RETURN_MESSAGE_FAIL);
				resp.setDescription("����ǩ��ʧ��");
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
			ps = connection.prepareStatement("select " + UserTable.USERID
					+ " from " + UserTable.USERTABLE + " where "
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
	 * @param username �û���
	 * @param password �û�����
	 * @return �û�����
	 */
	public static UserInfoResp selectUserInfo(String username, String password) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserInfoResp resp = new UserInfoResp();
		
		try {
			ps = connection.prepareStatement("select userid,nickname,headimage,signature from "
					+ UserTable.USERTABLE + " where " + UserTable.USERNAME
					+ "=? and " + UserTable.PASSWORD + "=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if(rs.next()){
				int userid = rs.getInt(1);
				String nickname = rs.getString(2);
				HeadImage headImage = OperationUtil.getHeadImage(rs.getString(3));
				String signature = rs.getString(4);
				resp.setNickname(nickname);
				resp.setHeadImage(headImage);
				resp.setSignature(signature);
				resp.setUserid(userid);
			}else{
				resp.setUserid(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			OperationUtil.closeDataConnection(ps, rs);
		}
		
		return resp;
	}

}
