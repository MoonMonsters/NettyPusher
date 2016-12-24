/**
 * 
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import edu.csuft.chentao.util.Logger;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:40:34
 */
public class DaoConnection {

	private static Connection connection = null;
	/** �û��� */
	private static final String USERNAME = "root";
	/** ���� */
	private static final String PASSWORD = "root";

	private DaoConnection() {
	}

	/** ������ݿ�����Ӷ��� */
	public static Connection getConnection() {

		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// ������룬������ݿ�������������
				String url = "jdbc:mysql://localhost:3306/nettypusher?useUnicode=true&characterEncoding=utf8";
				connection = DriverManager.getConnection(url, USERNAME,
						PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.log("���ݿ����Ӵ���...");
			}
		}

		return connection;
	}

}
