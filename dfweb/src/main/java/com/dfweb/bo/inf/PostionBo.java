package com.dfweb.bo.inf;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface PostionBo {
	public String getPostionByAddress(String address) throws ClientProtocolException, IOException;
}
