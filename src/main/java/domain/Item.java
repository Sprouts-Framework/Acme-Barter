package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import cz.jirutka.validator.collection.constraints.EachURL;
import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity {

	private static final long serialVersionUID = 1113293724788670122L;

	// Constructors -----------------------------------------------------------

	public Item() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private String name;
	private String description;
	private List<String> pictures;
	
	//Getters y setters
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	@Analyzer(definition = "customanalyzer")
	@Field
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	@Analyzer(definition = "customanalyzer")
	@Field
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@EachURL
	@ElementCollection
	@NotNull
	public List<String> getPictures() {
		return pictures;
	}

	
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	// Relationships ----------------------------------------------------------
	

}

