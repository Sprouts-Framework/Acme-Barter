/*
 * DomainEntity.java Copyright (C) 2013 Universidad de Sevilla The use of this
 * project is hereby constrained to the conditions of the TDG Licence, a copy of
 * which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.lsi.dp.RepositoryFactory;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class DomainEntity implements Serializable, DomainObject {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 868764066568947758L;

	// Identification ---------------------------------------------------------

	private int id;
	private int version;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	// Moments ----------------------------------------------------------------

	private Date createdAt;
	private Date updatedAt;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Equality ---------------------------------------------------------------

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object other) {
		boolean result;

		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (other instanceof Integer)
			result = (getId() == (Integer) other);
		else if (!this.getClass().isInstance(other))
			result = false;
		else
			result = (getId() == ((DomainEntity) other).getId());

		return result;
	}

	@Transient
	@SuppressWarnings("unchecked")
	public DomainEntity retrieveCurrentVersion() {
		DomainEntity result;
		SimpleJpaRepository<Object, Integer> repository;
		Class<? extends Object> cls;

		if (0 == id)
			result = this;
		else {
			cls = ((Object) this).getClass();
			repository = RepositoryFactory.getInstance((Class<Object>) cls);
			result = (DomainEntity) repository.findOne(id);
		}

		return result;
	}

	@Transient
	public boolean hasChanged() {
		boolean result;
		int thisVersion;
		int currentVersion;

		thisVersion = getVersion();
		currentVersion = retrieveCurrentVersion().getVersion();

		result = thisVersion != currentVersion;
		return result;
	}

	@Transient
	public boolean reflectionEquals(final Object other) {
		boolean result;

		result = EqualsBuilder.reflectionEquals(this, other);

		return result;
	}

	@Transient
	public DomainEntity reconstruct(final Set<String> fields) {

		try {
			DomainEntity result;
			Object value;
			ExpressionParser parser;
			Expression expression;

			result = null;
			result = retrieveCurrentVersion();

			parser = new SpelExpressionParser();

			for (String fieldName : fields) {
				expression = parser.parseExpression(fieldName);
				value = expression.getValue(this);
				expression.setValue(result, value);
			}

			return result;

		} catch (Throwable oops) {
			throw new RuntimeException();
		}
	}
}
