/**
 * Ⱥchatgroup��ز���
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.csuft.chentao.pojo.req.CreateGrourpReq;
import edu.csuft.chentao.pojo.resp.CreateGroupResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:39:20
 */
public class GroupTableOperate {

	@SuppressWarnings("resource")
	public static synchronized CreateGroupResp insert(CreateGrourpReq req) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		CreateGroupResp resp = new CreateGroupResp();

		try {
			// �õ���ǰ����Ⱥid
			ps = connection.prepareStatement("select max(groupid) from "
					+ GroupTable.GROUPTABLE);
			rs = ps.executeQuery();
			// ����Ⱥid
			int groupid = Constant.DEFAULT_GROUPID;
			//ȡ�û������¿�ʼȺid
			if (rs.next()) {
				int id = rs.getInt(1);
				groupid = id == 0 ? groupid : id + 1;
			}

			// �޸�Ⱥͷ������
			String filename = req.getHeadImage().getFilename();
			filename = groupid + filename.substring(filename.lastIndexOf("."));
			req.getHeadImage().setFilename(filename);
			// ����Ⱥͷ��
			OperationUtil.saveHeadImage(req.getHeadImage());

			//����Ⱥ����
			ps = connection.prepareStatement("insert into "
					+ GroupTable.GROUPTABLE_ALL_FIELD + "values(?,?,?,?,?)");
			ps.setInt(1, groupid);
			ps.setString(2, req.getGroupname());
			ps.setString(3, req.getTag());
			ps.setInt(4, 0);
			ps.setString(5, filename);

			//�Ƿ�ִ�гɹ�
			if (ps.execute()) {
				resp.setType(Constant.CREATE_GROUP_SUCCESS);
				resp.setDescription(req.getGroupname() + "�����ɹ�");
				resp.setGroupid(groupid);
			} else {
				resp.setType(Constant.CREATE_GROUP_FAIL);
				resp.setDescription(req.getGroupname() + "����ʧ�ܣ��Ժ�����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resp;
	}

}
