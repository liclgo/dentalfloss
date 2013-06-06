package com.dfweb.bo.inf;


import java.util.List;

import com.df.bean.Para;
import com.dfweb.ex.InfoTipException;

public interface ServiceBo {

	void save(String ctId, String serviceName, List<Para> list);

}
