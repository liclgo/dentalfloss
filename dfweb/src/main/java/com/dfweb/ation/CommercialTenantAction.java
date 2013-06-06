package com.dfweb.ation;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.df.bean.CommercialTenant;
import com.df.bean.Page;
import com.df.bean.Service;
import com.dfweb.bo.inf.CommercialTenantBo;
import com.dfweb.bo.inf.PostionBo;
import com.dfweb.ex.InfoTipException;

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

	@RequestMapping(value = "/list.do")
	public String list(Integer pageNo, ModelMap model) throws IOException,
			JSONException, InfoTipException {
		Image img = ImageIO.read(new URL(
				"http://avatar.csdn.net/D/0/4/1_chenweionline.jpg"));
		img.getHeight(null);
		Page page = ctBo.list(pageNo);
		model.addAttribute("page", page);
		return "ct/list";
	}

	@RequestMapping(value = "/saveCt.do")
	public String saveCt(CommercialTenant ct) throws IOException,
			JSONException, InfoTipException {
		ctBo.saveCt(ct);
		return "redirect:/ct/list.do";
	}

	@RequestMapping(value = "/addCt.do")
	public String addCt(CommercialTenant ct) throws IOException, JSONException,
			InfoTipException {
		return "ct/addct";
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
	public String getNearCt(Float lat, Float lng, Integer num, Double distance)
			throws IOException, InfoTipException {
		if (num == null || num <= 0) {
			num = 10;
		}
		if (distance == null || distance <= 0) {
			distance = 2.0;
		}
		GeoResults<CommercialTenant> list = ctBo.getNearCt(lat, lng, num,
				distance);
		return new JSONObject(list).toString();

	}

	@ResponseBody
	@RequestMapping(value = "/addService.do")
	public String getNearCt(String ctId) throws IOException, InfoTipException {
		Service service = new Service();
		ctBo.addService(ctId, service);
		return "";
	}

}
