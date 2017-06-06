package edu.csuft.chentao.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * ������
 * 
 * @author cusft.chentao
 *
 */
public class Server {

	/**
	 * ��������
	 * 
	 * @param host
	 *            ��ַ
	 * @param port
	 *            �˿�
	 */
	public void runServer(int port) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();

		try {
			/*
			 * һϵ�е�����
			 */
			serverBootstrap.group(boss, worker);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			serverBootstrap.childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {

					ch.pipeline().addLast(
							new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers
									.weakCachingConcurrentResolver(null)));
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new StringEncoder());
					ch.pipeline().addLast("idleStateHandler",new IdleStateHandler(5, 10, 15));
//					ch.pipeline().addLast(new MyIdleHandler());
					ch.pipeline().addLast(new ServerHandler());
				}
			});
			// �󶨽ӿ�
			ChannelFuture channelFuture = serverBootstrap.bind(10101).sync();
			// �ȴ��ر�
			Channel channel = channelFuture.channel();
			runForever(channel);
			channel.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
	
	private void runForever(final Channel channel){
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channel.writeAndFlush("None");
				channel.read();
			}
		}).start();
	}
	
}
