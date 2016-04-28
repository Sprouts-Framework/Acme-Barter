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

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />

	<acme:protected path="id" />
	<acme:protected path="version" />

	<div class="fieldset-btm-margin">
			<acme:textbox-input code="customer.socialIdentity.nick" path="nick" />
			<acme:textbox-input code="customer.socialIdentity.socialNetwork" path="socialNetwork" />
			<acme:textbox-input code="customer.socialIdentity.homePage" path="homePage" />
			<acme:textbox-input code="customer.socialIdentity.picture" path="picture" />
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="profile/customer/show.do" />
 
</acme:form>

