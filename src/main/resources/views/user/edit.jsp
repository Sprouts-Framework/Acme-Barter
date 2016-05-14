<%--
 * edit.jsp
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
	
	<sprouts:hidden-field path="id"/>
	<sprouts:hidden-field path="version"/>
	<sprouts:hidden-field path="followers"/>
	<sprouts:hidden-field path="followees"/>
	<sprouts:hidden-field path="userAccount"/>
	
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	<sprouts:protected path="followers"/>
	<sprouts:protected path="followees"/>
	<sprouts:protected path="userAccount"/>
	
	<div class="fieldset-btm-margin">
		
		<sprouts:textbox-input code="user.name" path="name" />
		<sprouts:textbox-input code="user.surname" path="surname" />
		<sprouts:textbox-input code="user.phone" path="phone" />
		
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="profile/user/show.do" />

</sprouts:form>

