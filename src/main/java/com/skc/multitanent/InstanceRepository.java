/**
 * 
 */
package com.skc.multitanent;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author chaudhsi
 *
 */
@Component
public class InstanceRepository extends RedisCommonDao<Instance>{

	private static final String KEY = "instance";
	
	public InstanceRepository(){
		setKey(KEY);
	}
	
	@Override
	public void save(Instance redisEntity) {
		super.save(redisEntity);
	}
	
	@Override
	public Map<String, Instance> findAll() {
		return super.findAll();
	}
	
	@Override
	public Instance findOne(String id) {
		return super.findOne(id);
	}
	
	public Instance findByName(String name) {
		return hashOperations.get(KEY, name);
	}
	
	@Override
	public void delete(String id) {
		super.delete(id);
	}
	
	@Override
	public void update(Instance myRedisEntity) {
		super.update(myRedisEntity);
	}
	
}
