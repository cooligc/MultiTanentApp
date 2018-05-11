/**
 * 
 */
package com.skc.multitanent;

/**
 * @author chaudhsi
 *
 */
public class DataManager {
	
	//TODO Need to optimize object creation
	private static ThreadLocal<DataHolder> dataHolder = new ThreadLocal<DataHolder>();
	
	public static void setDataHolder(ThreadLocal<DataHolder> dataHolder) {
		DataManager.dataHolder = dataHolder;
	}
	
	public static DataHolder getDataHolder() {
		return DataManager.dataHolder.get();
	}
	
	public static void clean() {
		dataHolder.remove();
	}
}
