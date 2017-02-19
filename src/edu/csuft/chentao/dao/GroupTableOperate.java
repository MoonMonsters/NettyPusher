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
				// ����ݱ�ʶ���û�id�������
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

	/**
	 * ����Ⱥid��ȡȺ����
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @return GroupInfoResp����
	 */
	public static GroupInfoResp getGroupInfoWithId(int groupId) {
		GroupInfoResp resp = null;

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select groupid,groupname,tag,number from "
					+ GroupTable.GROUPTABLE + " where " + GroupTable.GROUPID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			if (rs.next()) {
				resp = new GroupInfoResp();
				// Ⱥid
				int id = rs.getInt(1);
				// Ⱥ����
				String groupName = rs.getString(2);
				// Ⱥ��ǩ
				String tag = rs.getString(3);
				// Ⱥ������
				int number = rs.getInt(4);

				// ���ļ��а�ͷ���ȡ����
				byte[] buf = OperationUtil.getHeadImage(id);

				resp.setGroupid(groupId);
				resp.setGroupname(groupName);
				resp.setHeadImage(buf);
				resp.setNumber(number);
				resp.setTag(tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return resp;
	}

	/**
	 * ����Ⱥ��������ݼ���
	 * 
	 * @param groupName
	 *            Ⱥ���ؼ���
	 * @return Ⱥ���ݼ���
	 */
	public static List<GroupInfoResp> getGroupInfoListWithGroupName(
			String groupName) {
		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<GroupInfoResp> list = new ArrayList<GroupInfoResp>();

		try {
			String sql = "select groupid,groupname,tag,number from "
					+ GroupTable.GROUPTABLE + " where " + GroupTable.GROUPNAME
					+ " like '%" + groupName + "%' order by rand() limit 0,9";
			ps = connection.prepareStatement(sql);
			// ps.setString(1, groupName);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Ⱥid
				int id = rs.getInt(1);
				// Ⱥ����
				String name = rs.getString(2);
				// Ⱥ��ǩ
				String tag = rs.getString(3);
				// Ⱥ������
				int number = rs.getInt(4);

				// ���ļ��а�ͷ���ȡ����
				byte[] buf = OperationUtil.getHeadImage(id);

				GroupInfoResp resp = new GroupInfoResp();
				resp.setGroupid(id);
				resp.setGroupname(name);
				resp.setHeadImage(buf);
				resp.setNumber(number);
				resp.setTag(tag);
				list.add(resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OperationUtil.closeDataConnection(ps, rs);
		}

		return list;
	}

	/**
	 * ����Ⱥ��ǩ�õ�Ⱥ���ݼ���
	 * 
	 * @param tag
	 *            Ⱥ��ǩ
	 * @return Ⱥ���ݼ���
	 */
	public static List<GroupInfoResp> getGroupInfoListWithGroupTag(String tag) {
		List<GroupInfoResp> list = new ArrayList<GroupInfoResp>();

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select groupid,groupname,tag,number from "
					+ GroupTable.GROUPTABLE + " where " + GroupTable.TAG
					+ " like '%" + tag + "%' order by rand() limit 0,9";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Ⱥid
				int id = rs.getInt(1);
				// Ⱥ����
				String name = rs.getString(2);
				// Ⱥ��ǩ
				String tag2 = rs.getString(3);
				// Ⱥ������
				int number = rs.getInt(4);

				// ���ļ��а�ͷ���ȡ����
				byte[] buf = OperationUtil.getHeadImage(id);

				GroupInfoResp resp = new GroupInfoResp();
				resp.setGroupid(id);
				resp.setGroupname(name);
				resp.setHeadImage(buf);
				resp.setNumber(number);
				resp.setTag(tag2);
				list.add(resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * �ж�Ⱥ�Ƿ����
	 * 
	 * @param groupId
	 *            Ҫ���ҵ�Ⱥ
	 * @return ����Ϊtrue��������Ϊfalse
	 */
	public static boolean isExitGroupWithGroupId(int groupId) {
		boolean result = false;

		Connection connection = DaoConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select " + GroupTable.GROUPNAME + " from "
					+ GroupTable.GROUPTABLE + " where " + GroupTable.GROUPID
					+ " = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String groupName = rs.getString(1);
				if (groupName != null) {
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
}
