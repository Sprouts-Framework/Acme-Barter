package es.us.lsi.dp;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {

	public static SimpleJpaRepository<Object, Integer> getInstance(final Class<Object> cls) {

		EntityManagerFactory factory;

		factory = (EntityManagerFactory) AppContext.getApplicationContext().getBean("entityManagerFactory");

		return new SimpleJpaRepository<Object, Integer>(cls, factory.createEntityManager());
	}

}
