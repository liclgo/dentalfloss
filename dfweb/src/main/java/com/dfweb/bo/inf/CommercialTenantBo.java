package com.dfweb.bo.inf;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.GeoResults;

import com.dbweb.ex.InfoTipException;
import com.df.bean.CommercialTenant;


public interface CommercialTenantBo {

	void addCt(CommercialTenant ct) throws ClientProtocolException, IOException, JSONException, InfoTipException;

	CommercialTenant getCt(String id);

	GeoResults<CommercialTenant> getNearCt(Float lng, Float lag, Integer num, Double distance);
}
