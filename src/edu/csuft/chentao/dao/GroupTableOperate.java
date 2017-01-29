/**
 * Ⱥchatgroup��ز���
 */
package edu.csuft.chentao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.csuft.chentao.pojo.req.CreateGroupReq;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.OperationUtil;

/**
 * @author csuft.chentao
 *
 *         2016��12��8�� ����9:39:20
 */
public class GroupTableOperate {

	@SuppressWarnings("resource")
	public static synchronized int insert(CreateGroupReq req) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnGroupId = -1;

		try {
			// �õ���ǰ����Ⱥid
			ps = connection.prepareStatement("select max(groupid) from "
					+ GroupTable.GROUPTABLE);
			rs = ps.executeQuery();
			// ����Ⱥid
			int groupid = Constant.DEFAULT_GROUPID;
			// ȡ�û������¿�ʼȺid
			if (rs.next()) {
				int id = rs.getInt(1);
				groupid = id == 0 ? groupid : id + 1;
			}

			// ����Ⱥͷ��
			OperationUtil.saveHeadImage(req.getHeadImage(), groupid);

			// ����Ⱥ����
			ps = connection.prepareStatement("insert into "
					+ GroupTable.GROUPTABLE_ALL_FIELD + "values(?,?,?,?)");
			ps.setInt(1, groupid);
			ps.setString(2, req.getGroupname());
			ps.setString(3, req.getTag());
			ps.setInt(4, 0);

			// �Ƿ�ִ�гɹ�
			if (!ps.execute()) {
				returnGroupId = groupid;
				//����ݱ�ʶ���û�id�������
				GroupUserTableOperate.insert(groupid, req.getCreatorId(),
						Constant.TYPE_GROUP_CAPITAL_OWNER);
			} else {
				System.out.println("GroupTableOperation-->ִ��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}
		
		return returnGroupId;
	}

	/**
	 * ����Ⱥid���õ�����Ⱥ����Ϣ
	 * 
	 * @param groupIdList
	 *            Ⱥid����
	 * @return Ⱥ��Ϣ����
	 */
	public static List<GroupInfoResp> selectAllGroupInfosWithGroupIds(
			List<Integer> groupIdList) {
		List<GroupInfoResp> respList = new ArrayList<GroupInfoResp>();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select groupid,groupname,tag,number from "
					+ GroupTable.GROUPTABLE + " where " + GroupTable.GROUPID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			for (int id : groupIdList) {
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					// Ⱥid
					int groupId = rs.getInt(1);
					// Ⱥ����
					String groupName = rs.getString(2);
					// Ⱥ��ǩ
					String tag = rs.getString(3);
					// Ⱥ������
					int number = rs.getInt(4);

					// ���ļ��а�ͷ���ȡ����
					byte[] buf = OperationUtil.getHeadImage(groupId);

					GroupInfoResp resp = new GroupInfoResp();
					resp.setGroupid(groupId);
					resp.setGroupname(groupName);
					resp.setHeadImage(buf);
					resp.setNumber(number);
					resp.setTag(tag);

					respList.add(resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return respList;
	}

}
