package com.df.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.df.bean.Service;
import com.df.dao.inf.ServiceDao;

@Component
public class ServiceDaoImp implements ServiceDao{

	@Autowired
	MongoOperations mongo;

	public void save(Service sv) {
		mongo.save(sv);
	}

}
