/**
 * 
 */
package com.skc.multitanent;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author chaudhsi
 *
 */

public abstract class RedisCommonDao<K extends MyRedisEntity> {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	private String _key;
	
	private HashOperations<String, String, K> hashOperations;
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	public void save(K redisEntity) {
		hashOperations.put(_key, redisEntity.getId(), redisEntity);
	}
	
	public K findOne(String id) {
		return hashOperations.get(_key, id);
	}
	
	public Map<String, K> findAll(){
		return hashOperations.entries(_key);
	}
	
	public void update(K myRedisEntity) {
		hashOperations.put(_key, myRedisEntity.getId(), myRedisEntity);
	}
	
	public void delete(String id) {
		hashOperations.delete(_key, id);
	}
	
	
	/**
	 * @return the _key
	 */
	public String getKey() {
		return _key;
	}

	/**
	 * @param _key the _key to set
	 */
	public void setKey(String _key) {
		this._key = _key;
	}
	
	
}
