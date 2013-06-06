package com.df.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metric;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.df.bean.CommercialTenant;
import com.df.bean.Page;
import com.df.bean.Service;
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
		Point location = new Point(lng, lat);
		NearQuery near = NearQuery.near(location)
				.maxDistance(new Distance(distance, Metrics.KILOMETERS))
				.spherical(true).num(num);
		return mongo.geoNear(near, CommercialTenant.class);

	}

	public void getNearCt(String ctId, Service service) {
		mongo.findAndModify(Query.query(Criteria.where("_id").is(ctId)),
				new Update().addToSet("service", service),
				CommercialTenant.class);
	}

	public List<CommercialTenant> getList(Page page) {
		return mongo
				.find(new Query().skip(page.getStart()).limit(page.getPageSize()), CommercialTenant.class);
	}

}
