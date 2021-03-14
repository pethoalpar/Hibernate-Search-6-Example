package com.pethoalpar.service.api;

import java.util.List;

import com.pethoalpar.entity.Continent;

/**
 * @author alpar.petho
 *
 */
public interface IContinentService {
	
	List<Continent> elasticSearch(String searcText);

}
