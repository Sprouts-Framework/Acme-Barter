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

<jstl:if test="${error!=null}">
	<spring:message code="profile.socialAccount.error" var="errorMessage"/>

	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<jstl:out value="${errorMessage}"/>
	</div>
</jstl:if>

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.account-info" />
			</legend>
			<acme:textbox-input code="user.name" path="name" />
			<acme:textbox-input code="user.surname" path="surname" />
			<acme:textbox-input code="user.contactPhone" path="phone" />
			<acme:textbox-input code="user.userAccount.username" path="userAccount.username" />
			
			<jstl:if test="${empty modelObject.userAccount.socialAccounts}">
				<a class="btn btn-default" href="profile/userAccount/user/update.do"><spring:message code="user.userAccount-update" /></a>
			</jstl:if>

			<a class="btn btn-default" href="user/update.do"><spring:message code="user.profile-update" /></a>
			
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.socialIdentity-info" />
			</legend>
			<acme:data-table i18n="datatables.language" source="home/user/socialIdentity/${modelObject.id}/list/data.do">
				<acme:data-column code="socialIdentity.nick" path="nick" sortable="true"/>
				<acme:data-column code="socialIdentity.network" path="socialNetwork" sortable="true"/>
				<acme:data-column code="socialIdentity.home" path="homePage" format="url"/>
				<acme:data-column code="socialIdentity.picture" path="picture"/>	
				
				<acme:action-button url="socialIdentity/user/{0}/show.do" code="show.button"/>
				<acme:action-button url="socialIdentity/user/create.do" code="create.button"/>
				<acme:action-button url="socialIdentity/user/{0}/update.do" code="update.button"/>
				<acme:action-button url="socialIdentity/user/{0}/delete.do" code="delete.button"/>
				
			</acme:data-table>
			
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="" />

</acme:form>
<br/>

<acme:social-account-sign-in/>

