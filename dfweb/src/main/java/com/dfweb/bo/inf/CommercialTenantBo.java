package com.dfweb.bo.inf;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.GeoResults;
import com.df.bean.CommercialTenant;
import com.df.bean.Page;
import com.df.bean.Service;
import com.dfweb.ex.InfoTipException;


public interface CommercialTenantBo {

	void saveCt(CommercialTenant ct) throws ClientProtocolException, IOException, JSONException, InfoTipException;

	CommercialTenant getCt(String id);

	GeoResults<CommercialTenant> getNearCt(Float lng, Float lag, Integer num, Double distance);

	void addService(String ctId, Service service);

	Page list(Integer pageNo);

}
