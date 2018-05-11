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
	
	private static final String INSTANCES = "instances";
	private static final String MASTER = "master";
	private static final String MULTI_TANENT_HEADER="X-Tanent-Id";
	private ThreadLocal<DataHolder> _currentData = new ThreadLocal<DataHolder>();
	
	@Autowired
	InstanceRepository instanceRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String tanentId = request.getHeader(MULTI_TANENT_HEADER);
		if(tanentId == null) {
			tanentId = MASTER;
		}
		
		if(checkResponse(request,tanentId)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Requested resource cannot be accessed with this tanent="+tanentId);
			return false;
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
	
	private boolean checkResponse(HttpServletRequest request,String tanentId) {
		String requestPath = request.getRequestURI();
		
		//TODO RBAC Can be implemented here to check the user is authorized to resource or not
		if(requestPath.contains(INSTANCES) && !tanentId.equalsIgnoreCase(MASTER)) {
			return true;
		}
		
		if(!requestPath.contains(INSTANCES) && tanentId.equalsIgnoreCase(MASTER)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.setHeader(MULTI_TANENT_HEADER, DataManager.getDataHolder().getTanentId());
		DataManager.clean();
		super.postHandle(request, response, handler, modelAndView);
	}
}
