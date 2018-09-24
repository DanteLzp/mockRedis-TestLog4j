package com.lzp.home.PowerMockitoTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzp.home.Pool.Pool;

import redis.clients.jedis.Jedis;

public class JedisPoolUtil {

	private final String Method_Hgetall = "hgetAll";
	private final String Method_Hget = "hget";
	
	private Answer<Object> jedisaswser = new Answer<Object>() {
		public Object answer(InvocationOnMock invocation) {
			String methodName = invocation.getMethod().getName();
			Object[] methodArgs = invocation.getArguments(); 
			if(methodName.equals(Method_Hgetall)) {
				String HgetKey = (String) methodArgs[0];
				return jedisMap.get(HgetKey);
			}else if(methodName.equals(Method_Hget)) {
				String HgetKey = (String) methodArgs[0];
				String HgetField = (String) methodArgs[1];
				return jedisMap.get(HgetKey).get(HgetField);
			}
			return null;
		}
	};

	private Jedis jedis = PowerMockito.mock(Jedis.class, jedisaswser);

	private Map<String, Map<String, String>> jedisMap = new HashMap();

	public void mockInit() throws Exception {
		Pool pool = PowerMockito.mock(Pool.class);
		PowerMockito.whenNew(Pool.class).withAnyArguments().thenReturn(pool);
		PowerMockito.when(pool.getResource()).thenReturn(jedis);
	}

	public JedisPoolUtil(JSONArray mockArray) throws Exception {
		mockInit();
		int size = mockArray.size();
		for (int i = 0; i < size; i++) {
			JSONObject mockObject = mockArray.getJSONObject(i);
			Iterator<String> keyIterator = mockObject.keySet().iterator();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				JSONObject mockMap = mockObject.getJSONObject(key);
				Iterator<String> fieldIterator = mockMap.keySet().iterator();
				while (fieldIterator.hasNext()) {
					Map<String, String> fieldMap = new HashMap();
					String fieldKey = fieldIterator.next();
					String fieldValue = mockMap.getString(fieldKey);
					fieldMap.put(fieldKey, fieldValue);
					jedisMap.put(key, fieldMap);
				}
			}
		}
	}
}
