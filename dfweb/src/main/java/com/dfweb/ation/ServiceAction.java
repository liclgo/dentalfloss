package com.dfweb.ation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.df.bean.Para;
import com.df.bean.Service;
import com.dfweb.bo.inf.CommercialTenantBo;
import com.dfweb.bo.inf.PostionBo;
import com.dfweb.bo.inf.ServiceBo;
import com.dfweb.ex.InfoTipException;

@Controller
@RequestMapping(value = "/service")
public class ServiceAction {
	private static Logger logger = Logger.getLogger(ServiceAction.class);

	@Autowired
	ServiceBo svBo;

	@Autowired
	PostionBo postionBo;

	@RequestMapping(value = "/addService.do")
	public String addService(String ctId, ModelMap map) throws IOException {
		map.put("ctId", ctId);
		List para = Para.values();
		map.put("para", para);
		return "service/addservice";
	}

	@RequestMapping(value = "/saveService.do")
	public String saveService(String ctId, String serviceName,
			String[] filedName, String[] type, HttpServletRequest req,
			ModelMap model) throws IOException, InfoTipException {
		if (filedName.length == 0) {
			throw new InfoTipException("没添加字段啊");
		}
		if (filedName.length != type.length) {
			throw new InfoTipException("字段和属性不相等啊");
		}

		Map<String, String[]> map = req.getParameterMap();

		Map<String, Queue<String>> maplist = Para.analyse(map);

		int size = filedName.length;
		List<Para> listPara = new ArrayList<Para>();
		for (int i = 0; i < size; i++) {
			Para para = new Para();
			para.setFiledName(filedName[i]);
			para.setType(type[i]);
			if (para.getType().equals("radio")
					|| para.getType().equals("checkbox")
					|| para.getType().equals("select")) {
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put(para.getType() + "Values",
						maplist.get(para.getType() + "Values").poll());
				map2.put(para.getType() + "DefalutValue",
						maplist.get(para.getType() + "DefalutValue").poll());
				para.setParaMap(map2);
			}
			listPara.add(para);
		}
		svBo.save(ctId, serviceName, listPara);
		return "redirect:/service/addService.do";
	}

}
