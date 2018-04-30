/**
 * 
 */
package com.skc.multitanent;

/**
 * @author chaudhsi
 *
 */
public class DataManager {
	
	private static ThreadLocal<DataHolder> dataHolder;
	
	public static void setDataHolder(ThreadLocal<DataHolder> dataHolder) {
		DataManager.dataHolder = dataHolder;
	}
	
	public static DataHolder getDataHolder(ThreadLocal<DataHolder> dataHolder) {
		DataManager.dataHolder = dataHolder;
		return DataManager.dataHolder.get();
	}
	
	public static void clean() {
		dataHolder.remove();
	}
}
