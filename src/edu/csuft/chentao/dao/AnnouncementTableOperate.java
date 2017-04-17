/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.csuft.chentao.pojo.req.Announcement;
import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 * 
 *         2017��4��16�� ����3:53:07
 */
public class AnnouncementTableOperate {

	/**
	 * ��������
	 * @param announcement �������ݶ���
	 * @return �Ƿ����ɹ�
	 */
	public static boolean insert(Announcement announcement) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "insert into " + AnnouncementTable.TABLENAME
					+ " values(?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, announcement.getSerialnumber());
			ps.setString(2, announcement.getTitle());
			ps.setString(3, announcement.getContent());
			ps.setString(4, announcement.getUsername());
			ps.setString(5, announcement.getTime());
			ps.setInt(6, announcement.getUserid());
			ps.setInt(7, announcement.getGroupid());

			if (ps.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}
		
		return false;
	}

	public static void delete() {

	}

	public static void query() {

	}

}
