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
				protected void initChannel(Channel channel) throws Exception {

					channel.pipeline().addLast(
							new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers
									.weakCachingConcurrentResolver(null)));
					channel.pipeline().addLast(new ObjectEncoder());
					
					channel.pipeline().addLast(new ServerHandler());
				}
			});
			// �󶨽ӿ�
			ChannelFuture channelFuture = serverBootstrap.bind(10101)
					.sync();
			// �ȴ��ر�
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
