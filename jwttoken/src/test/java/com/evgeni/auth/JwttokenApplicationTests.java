package com.evgeni.auth;

import com.evgeni.auth.model.vo.JwtPrincipal;
import com.evgeni.auth.model.vo.UserLoginVO;
import com.evgeni.auth.security.token.TokenUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwttokenApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void tokenUtilsTest() {
		UserLoginVO login = new UserLoginVO();
		login.setUsername("evgeni");
		login.setPassword("pass");
		String generateToken = TokenUtils.generateToken(login);
		JwtPrincipal jwtPrincipal = TokenUtils.parseToken(generateToken);
		Assert.assertTrue("evgeni".equals(jwtPrincipal.getUsername()));
	}
}
