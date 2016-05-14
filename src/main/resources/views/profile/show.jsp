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

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.account-info" />
			</legend>
			<sprouts:textbox-input code="user.name" path="name" />
			<sprouts:textbox-input code="user.surname" path="surname" />
			<sprouts:textbox-input code="user.contactPhone" path="phone" />
			<sprouts:textbox-input code="user.userAccount.username" path="userAccount.username" />
			
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
			<sprouts:data-table searcheable="false" i18n="datatables.language" source="home/user/socialIdentity/${modelObject.id}/list/data.do">
				<sprouts:data-column code="socialIdentity.nick" path="nick" sortable="true"/>
				<sprouts:data-column code="socialIdentity.network" path="socialNetwork" sortable="true"/>
				<sprouts:data-column code="socialIdentity.home" path="homePage" format="url"/>
				<sprouts:data-column code="socialIdentity.picture" path="picture" format="image" imgSize="100x100"/>	
				
				<sprouts:action-button url="socialIdentity/user/{0}/show.do" code="show.button"/>
				<sprouts:action-button url="socialIdentity/user/{0}/update.do" code="update.button"/>
				<sprouts:action-button url="socialIdentity/user/{0}/delete.do" code="delete.button"/>
				
			</sprouts:data-table>
			
			<br/>
			<br/>
			<sprouts:button url="socialIdentity/user/create.do" code="create.button"></sprouts:button>
			
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="" />

</sprouts:form>
<br/>

<sprouts:social-account-sign-in isSignIn="false"/>

