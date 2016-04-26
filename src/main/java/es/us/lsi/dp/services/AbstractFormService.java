package es.us.lsi.dp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.DomainForm;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.FormService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.services.contracts.forms.UpdateFormService;
import es.us.lsi.dp.utilities.Moment;

public abstract class AbstractFormService<E extends DomainEntity, F extends DomainForm, R extends PagingAndSortingRepository<E, Integer>> implements
		FormService<F> {

	protected R repository;

	@Autowired
	public void setRepository(final R repository) {
		this.repository = repository;
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public F create() {
		F result = null;
		if (this instanceof CreateService) {
			CreateService createService = (CreateService) this;
			try {
				result = (F) createService.getEntityClass().newInstance();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}

		return result;
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public int save(final F form) {
		Assert.notNull(form);

		E result;
		E entity;
		if (this instanceof CreateFormService) {
			CreateFormService createFormService = (CreateFormService) this;

			entity = (E) createFormService.convertToEntity(form);
			Assert.notNull(entity);

			entity.setCreatedAt(Moment.now());

			result = repository.save(entity);

			return result.getId();
		} else
			return 0;
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public int update(final F form) {
		Assert.notNull(form);

		E entity;
		E result;

		if (this instanceof UpdateFormService) {
			UpdateFormService updateFormService = (UpdateFormService) this;

			entity = (E) updateFormService.convertToEntity(form);

			entity.setUpdatedAt(Moment.now());

			result = repository.save(entity);

			return result.getId();
		} else
			return 0;
	}

	@SuppressWarnings({
			"rawtypes", "unchecked"
	})
	public F findById(int id) {
		E entity;
		F result = null;

		entity = repository.findOne(id);
		Assert.notNull(entity);

		if (this instanceof UpdateFormService) {
			UpdateFormService updateFormService = (UpdateFormService) this;

			result = (F) updateFormService.convertToForm(entity);

			Assert.notNull(result);

			return result;
		} else
			return result;
	}

	@Override
	public long count() {
		return repository.count();
	}

}
