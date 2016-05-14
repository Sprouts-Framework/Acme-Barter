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

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:hidden-field path="id"/>
	<sprouts:hidden-field path="version"/>
	<sprouts:hidden-field path="user"/>
	
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	<sprouts:protected path="user"/>
	
	<div class="fieldset-btm-margin">
	
		<jstl:if test="${crudAction == 'showing' }">
			<sprouts:display-image-column src="${modelObject.picture}"/>
		</jstl:if>
		
		<jstl:if test="${crudAction == 'showing' }">
			<sprouts:display-column url="${modelObject.homePage}" title="${homePage}" message="${modelObject.homePage}"/>
		</jstl:if>
		
		
		<sprouts:textbox-input code="socialIdentity.nick" path="nick" />
		<sprouts:textbox-input code="socialIdentity.network" path="socialNetwork" />
		
		<jstl:if test="${crudAction != 'showing' }">
			<sprouts:textbox-input code="socialIdentity.home" path="homePage" />
		</jstl:if>
		
		
		<jstl:if test="${crudAction != 'showing' }">
			<sprouts:textbox-input code="socialIdentity.picture" path="picture" />
		</jstl:if>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="profile/user/show.do" />

</sprouts:form>