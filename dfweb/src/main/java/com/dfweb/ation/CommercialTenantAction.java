package com.dfweb.ation;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbweb.ex.InfoTipException;
import com.df.bean.CommercialTenant;
import com.df.bean.Postion;
import com.dfweb.bo.inf.CommercialTenantBo;
import com.dfweb.bo.inf.PostionBo;

@Controller
@RequestMapping(value = "/ct")
public class CommercialTenantAction {
	private static Logger logger = Logger
			.getLogger(CommercialTenantAction.class);

	@Autowired
	CommercialTenantBo ctBo;

	@Autowired
	PostionBo postionBo;

	@ResponseBody
	@RequestMapping(value = "/getpostion.do")
	public String getPostionByAddress(String address) throws IOException {
		return postionBo.getPostionByAddress(address);
	}

	@RequestMapping(value = "/addCt.do")
	public void addCt(CommercialTenant ct) throws IOException, JSONException, InfoTipException {
		ctBo.addCt(ct);
	}

	@RequestMapping(value = "/getCt.do")
	public void getCt(String Id) throws IOException, InfoTipException {
		CommercialTenant ct = ctBo.getCt(Id);
		if (ct == null) {
			throw new InfoTipException("can't find ct whit id" + Id);
		}
		logger.info(new JSONObject(ct).toString());
	}
	@ResponseBody
	@RequestMapping(value = "/findnear.do")
	public String getNearCt(Float lat,Float lng,Integer num,Double distance)throws IOException, InfoTipException {
		if(num==null||num<=0){
			num=10;
		}
		if(distance==null||distance<=0){
			distance=2.0;
		}
		GeoResults<CommercialTenant> list = ctBo.getNearCt(lat,lng,num,distance);
		return new JSONObject(list).toString();
		
	}

}
