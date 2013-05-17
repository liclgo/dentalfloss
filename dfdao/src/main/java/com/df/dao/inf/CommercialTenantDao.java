package com.df.dao.inf;

import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoResults;

import com.df.bean.CommercialTenant;

public interface CommercialTenantDao {

	void addCt(CommercialTenant ct);

	CommercialTenant getCt(String id);

	GeoResults<CommercialTenant> getNearCt(Float lng, Float lag, Integer num,
			Double distance);

}
