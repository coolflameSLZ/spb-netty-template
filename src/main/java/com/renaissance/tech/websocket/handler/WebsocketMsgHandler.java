package com.renaissance.tech.websocket.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketCloseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import com.renaissance.tech.service.DiscardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 
 * @author Administrator
 *
 */
@Slf4j
@Sharable
@Component
public class WebsocketMsgHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
	

	@Autowired
	DiscardService discardService;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
		if (msg instanceof TextWebSocketFrame) {
			TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;

			// spring bean 依赖注入
			final Integer discard = this.discardService.discard(textWebSocketFrame.text());

			// 响应客户端
			ctx.channel().writeAndFlush(new TextWebSocketFrame("我收到了你的消息：this msg id = " + discard));


		} else {
			// 不接受文本以外的数据帧类型
			ctx.channel().writeAndFlush(WebSocketCloseStatus.INVALID_MESSAGE_TYPE).addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		log.info("链接断开：{}", ctx.channel().remoteAddress());
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		log.info("链接创建：{}", ctx.channel().remoteAddress());
	}
}
