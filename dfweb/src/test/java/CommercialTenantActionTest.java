import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.dbweb.ex.InfoTipException;
import com.df.bean.CommercialTenant;
import com.df.bean.Postion;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CommercialTenantActionTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	MongoOperations mongo;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform((get("/ct/getpostion.do")).param("address", "杭州网易"))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testa() throws Exception {
		mockMvc.perform(
				(get("/ct/addCt.do")).param("address", "杭州市网易").param("ctName",
						"ctName")).andExpect(status().isOk()).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testc() throws Exception {
		mockMvc.perform(
				(get("/ct/findnear.do")).param("lng", "116").param("lat", "40")
						.param("num", "1").param("distance", "10000000"))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testd() throws Exception {
		CommercialTenant ct = new CommercialTenant();
		ct.setAddress("one");
		Postion p = new Postion();
		p.setLat(30.0f);
		p.setLng(120.0f);
		Float[] d = new Float[]{new Float(30f),new Float(120f)} ;
		ct.setLocation(d);
		// mongo.save(ct);
		Point location = new Point(40, 120);
		NearQuery near = NearQuery.near(location).num(1).spherical(false);
		GeoResults r = mongo.geoNear(near, CommercialTenant.class);
		System.out.println(new JSONObject(r).toString());

	}
}
