import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ServiceActionTest {

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
		mockMvc.perform(
				(get("/service/saveService.do")).param("ctId", "dafaf")
						.param("serviceName", "测试一下").param("type", "select")
						.param("filedName", "字段二")
						.param("selectValues", "1,2,3,4")
						.param("selectDefalutValue", "1")
						.param("type", "radio")
						.param("radioValues", "11,22,33,44")
						.param("radioDefalutValue", "11")
						.param("filedName", "字段san").param("type", "checkbox")
						.param("checkboxValues", "111,2,3,4")
						.param("checkboxDefalutValue", "111")
						.param("filedName", "字段si").param("type", "radio")
						.param("radioValues", "1111,2,3,4")
						.param("radioDefalutValue", "1111")
						.param("filedName", "字段一")).andExpect(status().isOk())
				.andDo(print());
	}

}
