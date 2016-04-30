<%--
 * show.jsp
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
	
	<acme:hidden-field path="id"/>
	<acme:hidden-field path="version"/>
	<acme:hidden-field path="followers"/>
	<acme:hidden-field path="followees"/>
	<acme:hidden-field path="userAccount"/>
	
	<acme:protected path="id"/>
	<acme:protected path="version"/>
	<acme:protected path="followers"/>
	<acme:protected path="followees"/>
	<acme:protected path="userAccount"/>
	
	<div class="fieldset-btm-margin">
		
		<acme:textbox-input code="user.name" path="name" />
		<acme:textbox-input code="user.surname" path="surname" />
		<acme:textbox-input code="user.phone" path="phone" />
		
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="profile/user/show.do" />

</acme:form>

