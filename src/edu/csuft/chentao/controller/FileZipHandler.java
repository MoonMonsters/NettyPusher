/**
 * 
 */
package edu.csuft.chentao.controller;

import edu.csuft.chentao.dao.GroupFileZipTable;
import edu.csuft.chentao.dao.GroupFileZipTableOperation;
import edu.csuft.chentao.pojo.req.FileZip;
import edu.csuft.chentao.util.OperationUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 * 
 *         2017��4��23�� ����12:03:13
 */
public class FileZipHandler implements Handler {

	public void handle(ChannelHandlerContext chc, Object object) {
		FileZip fz = (FileZip) object;

		OperationUtil.saveGroupFile(fz.getGroupId(), fz.getSerialNumber(),
				fz.getZip());

		// �������ݱ���
		GroupFileZipTable table = GroupFileZipTable.copyToGroupFileZipTable(fz);
		GroupFileZipTableOperation.insert(table);

		/*
		 * �ϴ���Ϻ󣬽����ļ���Ϣ���͵��ͻ���ȥ
		 */
		// �ļ���С
		fz.setFileSize(String.valueOf(fz.getZip().length));
		// �������ļ�����
		fz.setZip(null);

		// ����
		chc.writeAndFlush(fz);
	}
}
