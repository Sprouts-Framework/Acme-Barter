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
	<acme:hidden-field path="user"/>
	
	<acme:protected path="id"/>
	<acme:protected path="version"/>
	<acme:protected path="user"/>
	
	<div class="fieldset-btm-margin">
		
		<acme:textbox-input code="socialIdentity.nick" path="nick" />
		<acme:textbox-input code="socialIdentity.network" path="socialNetwork" />
		<acme:textbox-input code="socialIdentity.home" path="homePage" />
		
		<jstl:if test="${crudAction != 'showing' }">
			<acme:textbox-input code="socialIdentity.picture" path="picture" />
		</jstl:if>
	</div>

	<jstl:if test="${crudAction == 'showing' }">
		<acme:display-image-column src="${modelObject.picture}"/>
	</jstl:if>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="profile/user/show.do" />

</acme:form>