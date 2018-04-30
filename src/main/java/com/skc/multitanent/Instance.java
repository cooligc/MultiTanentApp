/**
 * 
 */
package com.skc.multitanent;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sitakant
 *
 */

public class Instance extends MyRedisEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String instanceId;
	private String tanentId;
	private Boolean isActive;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String lastAccessBy;
	/**
	 * @return the instanceId
	 */
	public String getInstanceId() {
		return instanceId;
	}
	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	/**
	 * @return the tanentId
	 */
	public String getTanentId() {
		return tanentId;
	}
	/**
	 * @param tanentId the tanentId to set
	 */
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return the lastAccessBy
	 */
	public String getLastAccessBy() {
		return lastAccessBy;
	}
	/**
	 * @param lastAccessBy the lastAccessBy to set
	 */
	public void setLastAccessBy(String lastAccessBy) {
		this.lastAccessBy = lastAccessBy;
	}
	
	static class InstanceBuilder {
		private static Instance instance;
		private InstanceBuilder() {
			instance = new Instance();
		}
		
		public static InstanceBuilder init() {
			return new InstanceBuilder();
		}
		
		public InstanceBuilder withId(String id) {
			instance.setId(id);
			return this;
		}
		
		public InstanceBuilder withInstanceId(String instanceId) {
			instance.setInstanceId(instanceId);
			return this;
		}
		
		public InstanceBuilder withTanentId(String tanentId) {
			instance.setTanentId(tanentId);
			return this;
		}
		
		public InstanceBuilder withStatus(Boolean status) {
			instance.setIsActive(status);
			return this;
		}
		
		public InstanceBuilder withCreatedDate(Date createdDate) {
			instance.setCreatedDate(createdDate);
			return this;
		}
		
		public InstanceBuilder withUpdatedDate(Date updatedDate) {
			instance.setLastUpdatedDate(updatedDate);
			return this;
		}
		
		public InstanceBuilder withLastAccesBy(String updatedBy) {
			instance.setLastAccessBy(updatedBy);
			return this;
		}
		
		public Instance build() {
			return instance;
		}
	}
}
