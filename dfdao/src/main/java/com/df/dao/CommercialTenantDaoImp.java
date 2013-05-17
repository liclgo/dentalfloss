package com.df.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metric;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.df.bean.CommercialTenant;
import com.df.dao.inf.CommercialTenantDao;

@Component
public class CommercialTenantDaoImp implements CommercialTenantDao {


	@Autowired
	MongoOperations mongo;

	public void addCt(CommercialTenant ct) {
		mongo.insert(ct);
	}

	public CommercialTenant getCt(String id) {
		return mongo.findById(id, CommercialTenant.class);
	}

	public GeoResults<CommercialTenant> getNearCt(Float lat, Float lng,
			Integer num, Double distance) {
		Point location = new Point(30, 120);
		NearQuery near = NearQuery.near(location);
		return mongo.geoNear(near, CommercialTenant.class);
	}

}
