package com.dfweb.bo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.stereotype.Component;

import com.dbweb.ex.InfoTipException;
import com.df.bean.CommercialTenant;
import com.df.dao.inf.CommercialTenantDao;
import com.dfweb.bo.inf.CommercialTenantBo;
import com.dfweb.bo.inf.PostionBo;

@Component
public class CommercialTenantBoImp implements CommercialTenantBo {

	@Autowired
	CommercialTenantDao commercialTenantDao;

	@Autowired
	PostionBo postionBo;

	public void addCt(CommercialTenant ct) throws ClientProtocolException,
			IOException, JSONException, InfoTipException {
		String s = postionBo.getPostionByAddress(ct.getAddress());
		// {"status":0,"result":{"location":{"lng":116.30814954222,"lat":40.056885091681},"precise":1,"confidence":80,"level":"\u5546\u52a1\u5927\u53a6"}}
		JSONObject o = new JSONObject(s);
		if (o.getInt("status") != 0 || o.getString("result").length() < 3) {
			throw new InfoTipException("the location" + ct.getAddress()
					+ "may be not right");
		}
		o = new JSONObject(o.getString("result"));
		o = new JSONObject(o.getString("location"));
		float[] f = new float[] { new Float(o.getString("lat")),
				new Float(o.getString("lng")) };
		ct.setLocation(f);
		commercialTenantDao.addCt(ct);
	}

	public CommercialTenant getCt(String id) {
		return commercialTenantDao.getCt(id);
	}

	public GeoResults<CommercialTenant> getNearCt(Float lat, Float lng,
			Integer num, Double distance) {
		return commercialTenantDao.getNearCt(lat, lng, num, distance);
	}

}
