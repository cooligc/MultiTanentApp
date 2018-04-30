/**
 * 
 */
package com.skc.multitanent;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaudhsi
 *
 */
@RestController
@RequestMapping("/instances")
public class MasterResource {

	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired InstanceRepository instanceRepository;

	@PostConstruct
	private void createMaster() {
		Instance instance = Instance.InstanceBuilder.init()
										.withCreatedDate(new Date())
										.withId("master")
										.withInstanceId("master")
										.withLastAccesBy("ROOT")
										.withTanentId("master")
										.withUpdatedDate(new Date())
										.build();
		instanceRepository.save(instance);
	}
	
	
	@GetMapping
	public Map<String,Instance> getAll(){
		return instanceRepository.findAll();
	}
	
}
