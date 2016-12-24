package edu.csuft.chentao.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����ͻ�������
 * 
 * @author cusft.chentao
 *
 */
public class NettyCollections {

	/** �ܵ����ϣ�������ͻ��˽��й�ͨ */
	private static final Map<Integer, ChannelHandlerContext> chcMap = new HashMap<Integer, ChannelHandlerContext>();

	/**
	 * ���������������ӵĿͻ��˱�������
	 * 
	 * @param username
	 *            ���û�����ס�ؼ���
	 * @param chc
	 *            ChannelHandlerContext����
	 */
	public static void add(Integer userid, ChannelHandlerContext chc) {
		chcMap.put(userid, chc);
	}

	/**
	 * �����û����Ƴ�
	 * 
	 * @param username
	 *            �û���
	 */
	public static void removeWithUserId(int userid) {
		chcMap.remove(userid);
	}

	/**
	 * ����ChannelHandlerContext�����Ƴ�
	 * 
	 * @param chc
	 */
	public static void removeWithCHC(ChannelHandlerContext chc) {
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

		Collection<ChannelHandlerContext> collection = chcMap.values();
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
				ChannelHandlerContext chc = chcMap.get(userid);
				if(chc != null){
					chc.writeAndFlush(object);
				}
			}
		}
	}
}