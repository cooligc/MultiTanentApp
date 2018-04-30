/**
 * 
 */
package com.skc.multitanent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author sitakant
 *
 */
@Component
public class MultitanentInterceptor extends HandlerInterceptorAdapter {
	
	private static final String MULTI_TANENT_HEADER="X-Tanent-Id";
	private ThreadLocal<DataHolder> _currentData = new ThreadLocal<DataHolder>();
	
	@Autowired
	InstanceRepository instanceRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String tanentId = request.getHeader(MULTI_TANENT_HEADER);
		if(tanentId == null) {
			tanentId = "master";
		}
		
		DataHolder dataHolder = new DataHolder();
		dataHolder.setTanentId(tanentId);
		Map<String,Object> metaMap = new HashMap<>();
		metaMap.put("instance", instanceRepository.findOne(tanentId));
		dataHolder.setMetaData(metaMap);
		_currentData.set(dataHolder);
		
		DataManager.setDataHolder(_currentData);
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		DataManager.clean();
		super.postHandle(request, response, handler, modelAndView);
	}
}
