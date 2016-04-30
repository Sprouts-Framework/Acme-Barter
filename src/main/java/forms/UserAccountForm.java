package forms;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.DomainForm;

public class UserAccountForm implements DomainForm{

	private String username;
	private String oldPassword;
	private String password;
	private String password2;

	@Size(min = 5, max = 32)
	@Column(unique = true)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
			
	
}
