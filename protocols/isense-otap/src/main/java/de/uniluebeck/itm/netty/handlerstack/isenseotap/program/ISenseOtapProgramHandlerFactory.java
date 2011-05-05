/**
 * Copyright (c) 2010, Daniel Bimschas and Dennis Pfisterer, Institute of Telematics, University of Luebeck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 	- Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * 	  disclaimer.
 * 	- Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * 	  following disclaimer in the documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the University of Luebeck nor the names of its contributors may be used to endorse or promote
 * 	  products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.uniluebeck.itm.netty.handlerstack.isenseotap.program;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.channel.ChannelHandler;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Multimap;

import de.uniluebeck.itm.netty.handlerstack.HandlerFactory;

public class ISenseOtapProgramHandlerFactory implements HandlerFactory {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ISenseOtapProgramHandlerFactory.class);

    private static final String MAX_REREQUESTS = "maxRerequests";

    private static final String TIMEOUT_MULTIPLIER = "timeoutMultiplier";

    @Override
    public String getName() {
        return "isense-otap-program-handler";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public ChannelHandler create(Multimap<String, String> properties) throws Exception {
        return create(null, properties);
    }

    @Override
    public ChannelHandler create(String instanceName, Multimap<String, String> properties) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        short settingMaxRerequests = 30; // TODO
        short settingTimeoutMultiplier = 1000; // TODO

        if (properties.containsKey(MAX_REREQUESTS))
            settingMaxRerequests = Short.parseShort(properties.get(MAX_REREQUESTS).iterator().next());

        if (properties.containsKey(TIMEOUT_MULTIPLIER))
            settingTimeoutMultiplier = Short.parseShort(properties.get(TIMEOUT_MULTIPLIER).iterator().next());

        log.debug("Creating new Otap Program Handler, settingMaxRerequests: {}, settingTimeoutMultiplier: {}",
                settingMaxRerequests, settingTimeoutMultiplier);

        return new ISenseOtapProgramHandler(instanceName, executorService, settingMaxRerequests,
                settingTimeoutMultiplier);
    }
}
