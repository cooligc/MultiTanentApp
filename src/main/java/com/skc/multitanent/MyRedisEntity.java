/**
 * 
 */
package com.skc.multitanent;

import java.io.Serializable;

/**
 * @author chaudhsi
 *
 */
public abstract class MyRedisEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
}
