package com.lzp.home.PowerMockitoTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzp.home.Pool.Pool;
import com.lzp.home.Pool.RedisUtil;
import com.lzp.home.Pool.RoleCacheProcess;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RedisUtil.class)
public class TestRedisPoolMock {

	@Test
	public void TestMockRedisPool() throws Exception {

		PowerMockito.spy(RedisUtil.class);
		InputStream inputStream = new FileInputStream("./target/test-classes/mock.json");
		String text = IOUtils.toString(inputStream, "utf8");
		JSONArray mockArray = JSONObject.parseObject(text).getJSONArray("case_mock");

		JedisPoolUtil poolDelegation = new JedisPoolUtil(mockArray);

		RedisUtil.init(Mockito.anyString());
		RoleCacheProcess roleCache = new RoleCacheProcess();
		
		roleCache.getRole("System_all_0");
		roleCache.getRole("Custom_123456789_0");
	}

}
