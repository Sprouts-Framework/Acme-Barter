package es.us.lsi.dp.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.fulltext.FullTextConstraint;
import es.us.lsi.dp.fulltext.FullTextCustomQuery;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.EntityService;
import es.us.lsi.dp.utilities.Moment;

public abstract class AbstractService<E extends DomainEntity, R extends PagingAndSortingRepository<E, Integer>> implements EntityService<E> {

	protected R repository;

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	public void setRepository(final R repository) {
		this.repository = repository;
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public E create() {
		E result = null;
		if (this instanceof CreateService) {
			CreateService createService = (CreateService) this;
			try {
				result = (E) createService.getEntityClass().newInstance();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}

		return result;
	}

	@Override
	public int save(final E domainObject) {
		Assert.notNull(domainObject);

		E result;

		domainObject.setCreatedAt(Moment.now());

		result = repository.save(domainObject);

		return result.getId();
	}

	@Override
	public int update(final E domainObject) {
		Assert.notNull(domainObject);

		E result;

		domainObject.setUpdatedAt(Moment.now());

		result = repository.save(domainObject);

		return result.getId();
	}

	@Override
	public void delete(final E domainObject) {
		Assert.notNull(domainObject);

		repository.delete(domainObject);
	}

	@Override
	public E findById(final int id) {
		E result;

		result = repository.findOne(id);

		return result;
	}

	@Override
	public Collection<E> findAll() {
		Collection<E> result;

		result = (Collection<E>) repository.findAll();

		return result;
	}

	@Override
	public long count() {
		return repository.count();
	}

	// Auxiliar methods ----------------------------------------------

	public Page<E> fullTextSearch(Class<?> clazz, Pageable pageable, String searchCriteria, List<FullTextCustomQuery> customQueries, String... fields) {
		Page<E> result = null;
		try {
			if (!searchCriteria.equals("")) {
				FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

				fullTextEntityManager.createIndexer().startAndWait();

				QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();

				org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields(fields).matching(searchCriteria).createQuery();

				for (FullTextCustomQuery customQuery : customQueries) {
					luceneQuery = fullTextCustomQueryBuilder(customQuery, luceneQuery, qb);
				}

				FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, clazz);

				int totalNumber = fullTextQuery.getResultSize();
				fullTextQuery.setFirstResult(pageable.getOffset());
				fullTextQuery.setMaxResults(pageable.getPageSize());

				@SuppressWarnings("unchecked")
				List<E> resultList = fullTextQuery.getResultList();
				result = new PageImpl<E>(resultList, pageable, new Long(totalNumber));
				Assert.notNull(result);
			} else {
				result = repository.findAll(pageable);
				Assert.notNull(result);
			}

		} catch (Throwable oops) {
			new RuntimeException(oops);
		}

		return result;
	}

	public Page<E> fullTextSearch(Class<?> clazz, Pageable pageable, String searchCriteria, String... fields) {
		return fullTextSearch(clazz, pageable, searchCriteria, new ArrayList<FullTextCustomQuery>(), fields);
	}

	private org.apache.lucene.search.Query fullTextCustomQueryBuilder(FullTextCustomQuery customQuery, org.apache.lucene.search.Query query, QueryBuilder qb) {
		org.apache.lucene.search.Query result;
		result = query;
		if (customQuery.getConstraint().equals(FullTextConstraint.EQUALS)) {
			result = qb.bool().must(result).must(qb.keyword().onField(customQuery.getField()).ignoreAnalyzer().matching(customQuery.getObject()).createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.GREATER_OR_EQUALS_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).above(customQuery.getObject()).createQuery()).createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.GREATER_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).above(customQuery.getObject()).excludeLimit().createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.LOWER_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).below(customQuery.getObject()).excludeLimit().createQuery())
					.createQuery();

		} else if (customQuery.getConstraint().equals(FullTextConstraint.LOWER_OR_EQUALS_THAN)) {
			result = qb.bool().must(result).must(qb.range().onField(customQuery.getField()).below(customQuery.getObject()).createQuery()).createQuery();
		}
		return result;
	}

}
