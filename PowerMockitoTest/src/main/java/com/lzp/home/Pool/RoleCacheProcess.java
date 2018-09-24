package com.lzp.home.Pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.lzp.home.model.IamRole;

public class RoleCacheProcess {

	public void getRole(String URLInput) throws IOException {
		Map<String, String> roleMap = RedisUtil.hgetall(RedisUtil.getDefaultJedisId(), URLInput);
		for (Entry<String, String> entry : roleMap.entrySet()) {
			entry.getKey();
			entry.getValue();
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
	}
}
