/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	 * ɾ��
	 * 
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean delete(String serialnumber) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "delete from " + AnnouncementReader.TABLE_NAME
					+ " where " + AnnouncementReader.SERIAL_NUMBER + " = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, serialnumber);
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

}
