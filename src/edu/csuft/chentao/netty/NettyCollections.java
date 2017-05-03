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
	public static synchronized void removeWithUserId(int userid) {
		// �ر���
		ChannelHandlerContext chc = sCtxMap.get(userid);
		if (chc != null) {
			chc.close();
			chc = null;
		}
		//�Ƴ�
		sCtxMap.remove(userid);
	}

	/**
	 * ����ChannelHandlerContext�����Ƴ�
	 * 
	 * @param chc
	 */
	public static synchronized void removeWithCHC(ChannelHandlerContext chc) {
		// Set<String> set = chcMap.keySet();
		// Iterator<String> it = set.iterator();
		// String username = null;
		// while(it.hasNext()){
		// username = it.next();
		// if(chcMap.get(username).equals(chc)){
		// break;
		// }
		// }
		// chcMap.remove(username);

		Collection<ChannelHandlerContext> collection = sCtxMap.values();
		if (collection.contains(chc)) {
			collection.remove(chc);
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
	public static void traverse(List<Integer> useridList, Object object) {
		if (useridList.size() > 0) {
			for (int userid : useridList) {
				ChannelHandlerContext chc = sCtxMap.get(userid);
				if (chc != null) {
					Logger.log("Ӧ���յ���Ϣ��userId->" + userid);
					chc.writeAndFlush(object);
				}
			}
		}
	}

	/**
	 * ��
	 */
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

	public static Set<Integer> getConnectionUerIdList() {
		return sCtxMap.keySet();
	}

}