package de.uniluebeck.itm.netty.isense.otap.program;

import de.uniluebeck.itm.netty.util.HeaderAndJavaBeansXMLDecoderEncoder;
import de.uniluebeck.itm.netty.util.OneToOneUpstreamEncoder;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

public class ISenseOtapProgramResultUpstreamEncoder extends OneToOneUpstreamEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		if (!(msg instanceof ISenseOtapProgramResult)) {
			return msg;
		}

		return HeaderAndJavaBeansXMLDecoderEncoder.encode(ISenseOtapProgramResult.SERIALIZATION_HEADER, msg);
	}

}
