<%--
 * editUserAccount.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<div class="fieldset-btm-margin">
		
		<acme:textbox-input code="user.userAccount.username" path="username" />
		
		<acme:password-input code="user.userAccount.password.old" path="oldPassword" />
		
		<acme:password-input code="user.userAccount.password" path="password" />
		
		<acme:password-input code="user.userAccount.password" path="password2" />
			
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="profile/user/show.do" />

</acme:form>

