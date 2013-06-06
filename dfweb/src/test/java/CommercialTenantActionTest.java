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
import com.df.bean.CommercialTenant;

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
		mockMvc.perform((get("/ct/getpostion.do")).param("address", "北京网易"))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testa() throws Exception {
		mockMvc.perform(
				(get("/ct/addService.do")).param("address", "北京网易").param(
						"ctName", "beijingwangyi")).andExpect(status().isOk())
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testc() throws Exception {
		mockMvc.perform(
				(get("/ct/findnear.do")).param("lng", "116").param("lat", "40")
						.param("num", "1").param("distance", "35"))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testd() throws Exception {
		mockMvc.perform(
				(get("/ct/findnear.do")).param("lng", "116").param("lat", "40")
						.param("num", "1").param("distance", "35"))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void teste() throws Exception {
		mockMvc.perform(
				(get("/ct/addService.do")).param("ctId",
						"5199d732b197519470dcaf17")).andExpect(status().isOk())
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testlist() throws Exception {
		mockMvc.perform((get("/ct/list.do"))).andExpect(status().isOk())
				.andDo(print()).andExpect(status().isOk());
	}
}
