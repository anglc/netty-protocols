/**********************************************************************************************************************
 * Copyright (c) 2010, coalesenses GmbH                                                                               *
 * All rights reserved.                                                                                               *
 *                                                                                                                    *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the   *
 * following conditions are met:                                                                                      *
 *                                                                                                                    *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following *
 *   disclaimer.                                                                                                      *
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the        *
 *   following disclaimer in the documentation and/or other materials provided with the distribution.                 *
 * - Neither the name of the coalesenses GmbH nor the names of its contributors may be used to endorse or promote     *
 *   products derived from this software without specific prior written permission.                                   *
 *                                                                                                                    *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, *
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE      *
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,         *
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE *
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF    *
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY   *
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.                                *
 **********************************************************************************************************************/

package de.uniluebeck.itm.nettyprotocols.isense.otap;


import de.uniluebeck.itm.util.TimeDiff;

/**
 * @author Dennis Pfisterer
 */
public class ISenseOtapPacket implements Comparable<ISenseOtapPacket> {

	private byte[] content = null;

	private int chunkNumber = 0;

	private int overallPacketNumber = 0;

	private int index = 0;

	private TimeDiff transmissionTime = new TimeDiff();

	ISenseOtapPacket(int startAddress, byte[] content, int offset, int length) {
		this.overallPacketNumber = startAddress / 64;
		setContent(content, offset, length);
	}

	public int compareTo(ISenseOtapPacket o) {

		if (o == null) {
			return 1;
		}

		if (o == this) {
			return 0;
		}

		return this.overallPacketNumber - o.overallPacketNumber;
	}

	@Override
	public String toString() {
		int bytes = content != null ? content.length : 0;
		return "Packet: chunkNumber " + chunkNumber + ", overallPacketNumber: " + overallPacketNumber + ", index " + index + ", " + bytes + " bytes";
	}

	public void setTransmissionTime() {
		this.transmissionTime.touch();
	}

	public TimeDiff getTransmissionTime() {
		return transmissionTime;
	}

	public int getChunkNumber() {
		return chunkNumber;
	}

	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content, int offset, int length) {
		this.content = new byte[length];
		System.arraycopy(content, offset, this.content, 0, length);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getOverallPacketNumber() {
		return overallPacketNumber;
	}

	public void setOverallPacketNumber(int overallPacketNumber) {
		this.overallPacketNumber = overallPacketNumber;
	}
}
