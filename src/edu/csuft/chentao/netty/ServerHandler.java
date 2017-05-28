package edu.csuft.chentao.netty;

import edu.csuft.chentao.controller.AllMessageHandler;
import edu.csuft.chentao.util.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * ���ݴ���
 * 
 * @author cusft.chentao
 *
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void messageReceived(ChannelHandlerContext chc, Object object)
			throws Exception {
		Logger.log("ServerHandler-->messageReceived");
	}

	/** �ͻ������� */
	@Override
	public void channelActive(ChannelHandlerContext chc) throws Exception {
		Logger.log("ServerHandler-->channelActive");
	}

	/** ���յ��ͻ��˵���Ϣ����������Ӧ���� */
	@Override
	public void channelRead(ChannelHandlerContext chc, Object object)
			throws Exception {
		Logger.log("ServerHandler-->channelRead");
		AllMessageHandler.handleMessage(chc, object);
	}

	/** �ͻ��˶Ͽ������˵����� */
	@Override
	public void channelInactive(ChannelHandlerContext chc) throws Exception {
		//�ر�
		chc.close();
		// �Ͽ����ӣ��Ӽ������Ƴ���
		NettyCollections.removeWithCHC(chc);
		Logger.log("ServerHandler-->channelInactive");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
		Logger.log("SererHandler-->exceptionCaught---->"+cause.getMessage());
	}
}
