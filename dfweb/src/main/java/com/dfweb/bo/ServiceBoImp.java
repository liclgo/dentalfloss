package com.dfweb.bo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.df.bean.Para;
import com.df.bean.Service;
import com.df.dao.inf.ServiceDao;
import com.dfweb.bo.inf.ServiceBo;
import com.dfweb.ex.InfoTipException;

@Component
public class ServiceBoImp implements ServiceBo {

	@Autowired
	ServiceDao svDao;

	@Override
	public void save(String ctId, String serviceName, List<Para> list) {
		Service sv = new Service();
		sv.setServiceName(serviceName);
		sv.setPara(list);
		sv.setCtId(ctId);
		svDao.save(sv);
	}
}
