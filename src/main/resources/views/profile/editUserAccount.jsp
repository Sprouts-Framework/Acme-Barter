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

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<div class="fieldset-btm-margin">
		
		<sprouts:textbox-input code="user.userAccount.username" path="username" />
		
		<sprouts:password-input code="user.userAccount.password.old" path="oldPassword" />
		
		<sprouts:password-input code="user.userAccount.password" path="password" />
		
		<sprouts:password-input code="user.userAccount.password2" path="password2" />
			
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="profile/user/show.do" />

</sprouts:form>

