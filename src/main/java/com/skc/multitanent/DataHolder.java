/**
 * 
 */
package com.skc.multitanent;

import java.util.Map;

/**
 * @author sitakant
 *
 */
public class DataHolder {
	private String tanentId;
	private Map<String,Object> _metaData;
	
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
	 * @return the _metaData
	 */
	public Map<String, Object> getMetaData() {
		return _metaData;
	}
	/**
	 * @param _metaData the _metaData to set
	 */
	public void setMetaData(Map<String, Object> _metaData) {
		this._metaData = _metaData;
	}
	
	
}
