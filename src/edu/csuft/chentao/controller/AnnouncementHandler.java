/**
 * 
 */
package edu.csuft.chentao.controller;

import java.util.List;
import java.util.Set;

import edu.csuft.chentao.dao.AnnouncementReader;
import edu.csuft.chentao.dao.AnnouncementReaderOperate;
import edu.csuft.chentao.dao.AnnouncementTableOperate;
import edu.csuft.chentao.dao.GroupUserTableOperate;
import edu.csuft.chentao.netty.NettyCollections;
import edu.csuft.chentao.pojo.req.Announcement;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 * 2017��4��16�� ����3:38:52
 */
public class AnnouncementHandler implements Handler{

	public void handle(ChannelHandlerContext chc, Object object) {
		//1.���Announcement����
		Announcement announcement = (Announcement) object;
		//2.���浽���ݿ���
		AnnouncementTableOperate.insert(announcement);
		//2.����Ⱥid���õ�Ⱥ�������û�id
		int groupId = announcement.getGroupid();
		List<Integer> userIdList = GroupUserTableOperate.getAllUserIdWithGroupId(groupId);
		//3.�����û�id����Announcement����ת���������û�
		NettyCollections.traverse(groupId, announcement);
		//4.����û������ߣ����������ݴ洢�����ݿ���
		Set<Integer> userIdSet = NettyCollections.getConnectionUerIdList();
		userIdSet.add(announcement.getUserid());
		for(int userId : userIdList){
			if(!userIdSet.contains(userId)){
				AnnouncementReader reader = new AnnouncementReader();
				reader.setUserid(userId);
				reader.setSerialnumber(announcement.getSerialnumber());
				AnnouncementReaderOperate.insert(reader);
			}
		}
	}
}
