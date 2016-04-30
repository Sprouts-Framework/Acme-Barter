package forms;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import cz.jirutka.validator.collection.constraints.EachURL;

import es.us.lsi.dp.domain.DomainForm;


public class BarterForm implements DomainForm{
	
	private String title;
	private String offeredName;
	private String offeredDescription;
	private List<String> offeredPictures;
	private String requestedName;
	private String requestedDescription;
	private List<String> requestedPictures;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getOfferedName() {
		return offeredName;
	}

	
	public void setOfferedName(String offeredName) {
		this.offeredName = offeredName;
	}

	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getOfferedDescription() {
		return offeredDescription;
	}

	
	public void setOfferedDescription(String offeredDescription) {
		this.offeredDescription = offeredDescription;
	}

	@EachURL
	@ElementCollection
	@NotNull
	public List<String> getOfferedPictures() {
		return offeredPictures;
	}

	
	public void setOfferedPictures(List<String> offeredPictures) {
		this.offeredPictures = offeredPictures;
	}

	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getRequestedName() {
		return requestedName;
	}

	
	public void setRequestedName(String requestedName) {
		this.requestedName = requestedName;
	}

	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getRequestedDescription() {
		return requestedDescription;
	}

	
	public void setRequestedDescription(String requestedDescription) {
		this.requestedDescription = requestedDescription;
	}

	@EachURL
	@ElementCollection
	@NotNull
	public List<String> getRequestedPictures() {
		return requestedPictures;
	}

	
	public void setRequestedPictures(List<String> requestedPictures) {
		this.requestedPictures = requestedPictures;
	}

	
	
}
