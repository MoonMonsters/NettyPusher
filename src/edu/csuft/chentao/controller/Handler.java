/**
 * �������Դ����඼����ʵ�ָýӿ�
 * ����Ķ�����pojo���д�req����
 */
package edu.csuft.chentao.controller;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author csuft.chentao
 *
 *         2016��12��9�� ����8:47:32
 */
public interface Handler {

	/**
	 * �����������
	 * 
	 * @param chc
	 *            ����
	 * @param object
	 *            �������
	 */
	void handle(ChannelHandlerContext chc, Object object);

}
