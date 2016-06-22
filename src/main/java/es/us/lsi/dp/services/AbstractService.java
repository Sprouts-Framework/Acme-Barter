package es.us.lsi.dp.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.DomainEntity;
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

	@SuppressWarnings("unchecked")
	public Page<E> fullTextSearch(Pageable pageable, String searchCriteria, String luceneQueryStr, Class<?> clazz, String... fields) {
		Page<E> result = null;
		String trim = searchCriteria.trim();
		try {
			if (!trim.equals("")) {

				org.apache.lucene.search.Query luceneQuery = null;
				org.apache.lucene.search.Query luceneQueryToSearch = null;
				org.apache.lucene.search.Query luceneQueryFilter = null;

				Session session;
				session = em.unwrap(Session.class);
				FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
				SearchFactory searchFactory = fullTextSession.getSearchFactory();
				MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,searchFactory.getAnalyzer(clazz));

				
				FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
				fullTextEntityManager.createIndexer().startAndWait();
				
				QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
				TermMatchingContext luceneQueryAux = qb.keyword().onFields(fields);

				if(!luceneQueryStr.equals("")){
					try {
						luceneQueryFilter = parser.parse(luceneQueryStr);
					} catch (Throwable e) {
						throw new RuntimeException(e);
					}
	
					luceneQueryToSearch = luceneQueryAux.matching(searchCriteria).createQuery();
					luceneQuery = qb.bool().must(luceneQueryFilter).must(luceneQueryToSearch).createQuery();
				}else{
					luceneQuery = luceneQueryAux.matching(searchCriteria).createQuery();
				}
				FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, clazz);

				int totalNumber = fullTextQuery.getResultSize();
				fullTextQuery.setFirstResult(pageable.getOffset());
				fullTextQuery.setMaxResults(pageable.getPageSize());

				List<E> resultList = fullTextQuery.getResultList();
				result = new PageImpl<E>(resultList, pageable, new Long(totalNumber));
				Assert.notNull(result);
			} else {
				result = this.findAllDefaultFullText(pageable);
				Assert.notNull(result);
			}

		} catch (Throwable oops) {
			new RuntimeException(oops);
		}

		return result;
	}

	public Page<E> fullTextSearch(Pageable pageable, String searchCriteria, Class<?> clazz, String... fields) {
		return fullTextSearch(pageable, searchCriteria, "", clazz, fields);
	}

	public Page<E> findAllDefaultFullText(Pageable page) {
		return repository.findAll(page);
	}
}
