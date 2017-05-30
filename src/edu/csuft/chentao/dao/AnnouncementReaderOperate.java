/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 *
 *         2017��4��16�� ����4:44:07
 */
public class AnnouncementReaderOperate {

	/**
	 * ��������
	 * 
	 * @return �Ƿ�������ݳɹ�
	 */
	public static boolean insert(AnnouncementReader reader) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "insert into " + AnnouncementReader.TABLE_ALL_FIELD
					+ " values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, reader.getSerialnumber());
			ps.setInt(2, reader.getUserid());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return false;
	}

	/**
	 * ɾ��,����ȫ����ȡ��ϣ�ɾ��
	 * 
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean delete(int userId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "delete from " + AnnouncementReader.TABLE_NAME
					+ " where " + AnnouncementReader.USER_ID + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return false;
	}

	public static List<String> queryByUserId(int userId) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<String> serialNumberList = new ArrayList<String>();

		String sql = "select " + AnnouncementReader.SERIAL_NUMBER + " from "
				+ AnnouncementReader.TABLE_NAME + " where "
				+ AnnouncementReader.USER_ID + " = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				String serailNumber = rs.getString(1);
				serialNumberList.add(serailNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return serialNumberList;
	}
	
}
