/**
 * 
 */
package com.skc.multitanent;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaudhsi
 *
 */
@RestController
@RequestMapping("/instances")
public class MasterResource {
	
	private static final Logger LOG = LogManager.getLogger();
	
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
										.withStatus(Boolean.TRUE)
										.withName("master")
										.withUpdatedDate(new Date())
										.build();
		LOG.info("Master Instance Details : "+instance);
		instanceRepository.save(instance);
	}
	
	
	@PostMapping
	public Instance createInstance(@Valid @RequestBody Instance instance) {
		Instance instance2 = Instance.InstanceBuilder.init(instance)
													.withId(UUID.nameUUIDFromBytes(instance.getName().getBytes()).toString())
													.withTanentId(UUID.nameUUIDFromBytes(instance.getName().getBytes()).toString())
													.withInstanceId(UUID.nameUUIDFromBytes(instance.getName().getBytes()).toString())
													.withStatus(true)
													.withCreatedDate(new Date())
													.withUpdatedDate(new Date())
													.withLastAccesBy("ROOT")
													.build();
		instanceRepository.save(instance2);
		LOG.debug("Tanent Created");
		return instanceRepository.findOne(instance2.getId());
	}
	
	@PutMapping("/{id}")
	public Instance disableInstance(@PathVariable("id") String id) {
		Instance instance2 = instanceRepository.findOne(id);
		instance2.setIsActive(false);
		instance2.setLastUpdatedDate(new Date());
		instanceRepository.update(instance2);
		LOG.debug("Tanent Created");
		return instanceRepository.findByName(id);
	}
	
	@GetMapping
	public Map<String,Instance> getAll(){
		return instanceRepository.findAll();
	}
	
}
