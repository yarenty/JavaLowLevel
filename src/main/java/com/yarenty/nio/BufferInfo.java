/**
 * 
 */
package com.yarenty.nio;

import java.nio.ByteBuffer;

/**
 * @author yarenty
 *
 */
public class BufferInfo {

	public static String getStats(ByteBuffer buffer) {
		StringBuffer sb = new StringBuffer(200);
		sb.append("BUFFER::");
		sb.append(buffer);
		sb.append("[");
		sb.append("position:").append( buffer.position()).append(";");
		sb.append("limit:").append( buffer.limit()).append(";");		
		sb.append("remaining:").append( buffer.remaining()).append(";");
		sb.append("capacity:").append( buffer.capacity()).append(";");
		sb.append("]");
		return sb.toString();
	}

}
