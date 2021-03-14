package com.pethoalpar.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import com.pethoalpar.entity.Continent;
import com.pethoalpar.service.api.IContinentService;

import lombok.extern.log4j.Log4j2;

/**
 * @author alpar.petho
 *
 */
@Service
@Log4j2
public class ContinentService implements IContinentService {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<Continent> elasticSearch(String searcText) {

		SearchSession searchSession = Search.session(entityManager);

		SearchResult<Continent> result = searchSession.search(Continent.class).extension(ElasticsearchExtension.get())
				.where(f -> f.simpleQueryString().fields("name","animals.name").matching(searcText)).fetch(1000);

		List<Continent> results = result.hits();
		long totalHitCount = result.total().hitCount();
		log.info("Total hit count:" + totalHitCount);
		return results;
	}

}
