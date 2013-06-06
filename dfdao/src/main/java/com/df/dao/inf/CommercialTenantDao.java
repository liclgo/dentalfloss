package com.df.dao.inf;

import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoResults;

import com.df.bean.CommercialTenant;
import com.df.bean.Page;
import com.df.bean.Service;

public interface CommercialTenantDao {

	void addCt(CommercialTenant ct);

	CommercialTenant getCt(String id);

	GeoResults<CommercialTenant> getNearCt(Float lng, Float lag, Integer num,
			Double distance);

	void getNearCt(String ctId, Service service);

	List<CommercialTenant> getList(Page page);

}
