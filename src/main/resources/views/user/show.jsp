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

	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.account-info" />
			</legend>
			
			<spring:message var="name" code="user.name"/>
			<spring:message var="surname" code="user.surname"/>
			
			<sprouts:display-column title="${name}" data="${modelObject.name}"/>
			<sprouts:display-column title="${surname}" data="${modelObject.surname}"/>
			
			<sprouts:button url="user/${modelObject.id }/follow.do" code="user.follow-unfollow"/>
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.socialIdentity-info" />
			</legend>
			<sprouts:data-table i18n="datatables.language" searcheable="false" source="home/user/socialIdentity/${modelObject.id}/list/data.do">
				<sprouts:data-column code="socialIdentity.nick" path="nick" sortable="true"/>
				<sprouts:data-column code="socialIdentity.network" path="socialNetwork" sortable="true"/>
				<sprouts:data-column code="socialIdentity.home" path="homePage" format="url"/>
				<sprouts:data-column code="socialIdentity.picture" path="picture" format="image" imgSize="100x100"/>	
				
				<sprouts:action-button url="home/user/socialIdentity/{0}/show.do" code="user.socialIdentity.show"/>
				
			</sprouts:data-table>
			
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.barter.list" />
			</legend>
			<sprouts:data-table i18n="datatables.language" searcheable="false" source="home/user/barter/${modelObject.id}/list/data.do">
				<sprouts:data-column code="barter.moment" path="moment" sortable="true" format="date"/>
				<sprouts:data-column code="barter.title" path="title" />
				<sprouts:data-column code="barter.requested.name" path="requested.name" />
				<sprouts:data-column code="barter.offered.name" path="offered.name" />
				
				<sprouts:action-button url="home/barter/{0}/show.do" code="show.button"/>
				
			</sprouts:data-table>
			
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="user.matches.list" />
			</legend>
			<sprouts:data-table i18n="datatables.language" searcheable="false" source="home/user/match/${modelObject.id}/list/data.do">
				<sprouts:data-column code="match.moment" path="moment" sortable="true" format="date"/>
				<sprouts:data-column code="match.requestSignedDate" path="requestSignedDate" format="date" nullCode="match.nullCode"/>
				<sprouts:data-column code="match.offerSignedDate" path="offerSignedDate" format="date" nullCode="match.nullCode"/>
				<sprouts:data-column code="match.cancelled" path="cancelled" falseCode="match.cancelled.false" trueCode="match.cancelled.true"/>
				<sprouts:data-column code="match.barter.offered" path="offered.title"/>
				<sprouts:data-column code="match.barter.requested" path="requested.title"/>
				
				<sprouts:action-button url="home/match/{0}/show.do" code="show.button"/>
				
			</sprouts:data-table>
			
		</fieldset>
	</div>

