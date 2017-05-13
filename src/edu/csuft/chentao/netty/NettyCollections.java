package edu.csuft.chentao.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.csuft.chentao.dao.GroupOperationTable;
import edu.csuft.chentao.dao.GroupOperationTableOperate;
import edu.csuft.chentao.dao.GroupTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.pojo.req.Message;
import edu.csuft.chentao.pojo.resp.GroupInfoResp;
import edu.csuft.chentao.pojo.resp.GroupReminderResp;
import edu.csuft.chentao.util.Constant;
import edu.csuft.chentao.util.Logger;
import edu.csuft.chentao.util.OperationUtil;

/**
 * ����ͻ�������
 * 
 * @author cusft.chentao
 *
 */
public class NettyCollections {

	/** �ܵ����ϣ�������ͻ��˽��й�ͨ */
	private static final Map<Integer, ChannelHandlerContext> sCtxMap = new HashMap<Integer, ChannelHandlerContext>();
	private static final ExecutorService sThreadPool = Executors
			.newFixedThreadPool(100);
	/** keyֵΪȺid��valueֵΪȺ���û�id���� */
	private static final Map<Integer, List<Integer>> sUserIdListInMap = new HashMap<Integer, List<Integer>>();;

	/**
	 * ���������������ӵĿͻ��˱�������
	 * 
	 * @param username
	 *            ���û�����ס�ؼ���
	 * @param chc
	 *            ChannelHandlerContext����
	 */
	public static synchronized void add(Integer userid,
			ChannelHandlerContext chc) {
		sCtxMap.put(userid, chc);
		Logger.log("NettyCollections-->��ǰ��������" + sCtxMap.size());
		Logger.log("NettyCollections-->��ǰ����userId=" + userid);
	}

	/**
	 * �����û����Ƴ�
	 * 
	 * @param username
	 *            �û���
	 */
	public static synchronized void removeWithUserId(int userId) {
		Logger.log("NettyCollections.removeWithUserId--->�˳���¼-->" + userId);
		// �ر���
		ChannelHandlerContext chc = sCtxMap.get(userId);
		if (chc != null) {
			Logger.log("NettyCollections.removeWithUserId--->chc����Ϊ�գ��Ƴ���");
			chc.close();
			chc = null;
			// �Ƴ�
			sCtxMap.remove(userId);
			Logger.log("NettyCollections.removeWithUserId--->��ǰ��������->"
					+ sCtxMap.size());
		}
	}

	/**
	 * ����ChannelHandlerContext�����Ƴ�
	 * 
	 * @param chc
	 */
	public static synchronized void removeWithCHC(ChannelHandlerContext chc) {

		Logger.log("NettyCollections.removeWithCHC-->�ͻ��˶Ͽ�����");

		Collection<ChannelHandlerContext> collection = sCtxMap.values();
		if (collection.contains(chc)) {
			collection.remove(chc);
			Logger.log("NettyCollections.removeWithCHC--->��ǰ��������-->"
					+ sCtxMap.size());
		}
	}

	/**
	 * ��������
	 * 
	 * @param usernameList
	 *            ��Ҫ���͸��Ŀͻ���
	 * @param object
	 *            ���Ͷ���
	 */
	public static void traverse(int groupId, Object object) {
		// �õ�Ⱥid�õ�Ⱥ�û�id����
		List<Integer> userIdList = inputUserIdList(groupId);
		Logger.log("NettyCollections.traverse-->"+groupId+"�е��û�����:"+userIdList.size());
		//������͵�����Ϣ������Ҫ�Ƴ���������id
		if (object instanceof Message) {
			Message msg = (Message) object;
			int userId = msg.getUserid();
			//���list�е�ֵ����int���ͣ���ô�����±�Ϊ�������������Ҫ�ȵõ�ֵ���±�
			int index = userIdList.indexOf(userId);
			userIdList.remove(index);
		}
		if (userIdList.size() > 0) {
			for (int userid : userIdList) {
				ChannelHandlerContext chc = sCtxMap.get(userid);
				if (chc != null) {
					Logger.log("Ӧ���յ���Ϣ��userId->" + userid);
					chc.writeAndFlush(object);
				}
			}
		}
	}

	public static void readMessageFromDatabase() {

		new Thread(new Runnable() {

			public void run() {
				Iterator<Integer> it = sCtxMap.keySet().iterator();
				try {
					while (it.hasNext()) {
						final int userId = it.next();

						final GroupOperationTable table = GroupOperationTableOperate
								.queryByReaderId(userId);
						if (table != null) {
							Logger.log("table��Ϊ�գ�" + userId + "�����ݿ��Զ�ȡ");
							sThreadPool.execute(new Runnable() {

								public void run() {
									// ��������ʱΪ��
									GroupReminderResp resp = new GroupReminderResp();
									// �õ�Ⱥ����
									GroupInfoResp group = GroupTableOperate
											.getGroupInfoWithId2(table
													.getGroupId());

									resp.setType(table.getType());
									resp.setUserId(table.getUserId());
									resp.setGroupId(table.getGroupId());
									resp.setGroupName(group.getGroupname());
									resp.setDescription(table.getDescription());
									resp.setImage(group.getHeadImage());

									// ����Ϣ���͵������
									sCtxMap.get(userId).writeAndFlush(resp);

									if (resp.getType() == Constant.TYPE_GROUP_REMINDER_ADD_GROUP) {
										group.setType(Constant.TYPE_GROUP_INFO_OWNER);
										// ����Ⱥ����Ⱥ�������Ϣ���͵��ͻ���
										sCtxMap.get(userId)
												.writeAndFlush(group);
									}
								}
							});
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					// ����5��
					OperationUtil.sleepFor5000();
					// �ظ�ִ��
					readMessageFromDatabase();
				}
			}
		}).start();
	}

	/**
	 * �õ��������ӿͻ��˵��û�id����
	 */
	public static Set<Integer> getConnectionUerIdList() {
		return sCtxMap.keySet();
	}

	/**
	 * ���ChannelHandlerContext
	 * 
	 * @param userId
	 *            �û�id
	 */
	public static ChannelHandlerContext getChannelHandlerContextByUserId(
			int userId) {
		return sCtxMap.get(userId);
	}

	/**
	 * ����Ⱥid�õ���Ⱥ�������û���idֵ
	 * 
	 * @param groupId
	 *            Ⱥid
	 * @return �û�idֵ����
	 */
	private static List<Integer> inputUserIdList(int groupId) {

		// ����Ⱥid�õ�Ⱥ����
		int groupUserNumber = GroupTableOperate
				.getGroupUserNumberByGroupId(groupId);
		List<Integer> userIdList = null;
		// ���map��û�и�Ⱥ�û����ݼ���,���߼����е��û����������ݿ��е��û�������һ�£������»�ȡ
		if (sUserIdListInMap.get(groupId) == null
				|| sUserIdListInMap.get(groupId).size() != groupUserNumber) {
			// ȡ��
			userIdList = GroupUserTableOperate.getAllUserIdWithGroupId(groupId);
			// �����ȥ
			sUserIdListInMap.put(groupId, userIdList);
		} else {
			// ������ڣ���ֱ��ȡ��
			userIdList = sUserIdListInMap.get(groupId);
		}

		return userIdList;
	}
}