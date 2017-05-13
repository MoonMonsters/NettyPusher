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
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
		Logger.log("SererHandler-->exceptionCaught---->"+cause.getMessage());
	}
	
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
//    	super.userEventTriggered(ctx, evt);
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent e = (IdleStateEvent) evt;
// 
//            if (e.state() == IdleState.READER_IDLE) {
//                System.out.println("--- Reader Idle ---");
//                ctx.writeAndFlush(String.valueOf("Server Reader Idle"));
//                // ctx.close();
//            } else if (e.state() == IdleState.WRITER_IDLE) {
////                System.out.println("--- Write Idle ---");
////                ctx.writeAndFlush(String.valueOf("Server Write Idle"));
//                // ctx.close();
//            } else if (e.state() == IdleState.ALL_IDLE) {
////                System.out.println("--- All_IDLE ---");
////                ctx.writeAndFlush(String.valueOf("Server All Idle"));
//            }
//        }
    }

}
