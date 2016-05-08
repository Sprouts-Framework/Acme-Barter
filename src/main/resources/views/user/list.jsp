<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp"%>


<acme:data-table i18n="datatables.language">
	<acme:data-column code="user.name" path="name" sortable="true"/>
	<acme:data-column code="user.surname" path="surname"/>
	<acme:data-column code="user.username" path="userAccount.username"/>
	
	<security:authorize access="hasRole('User')">
		<acme:action-button url="user/{0}/follow.do" code="user.follow-unfollow"/>
	</security:authorize>
	<acme:action-button url="home/user/profile/{0}/show.do" code="user.profile.viewDetails"/>
	<acme:action-button url="home/user/socialIdentity/{0}/list.do" code="user.socialIdentity.list"/>
	<acme:action-button url="home/user/barter/{0}/list.do" code="user.barter.list"/>
	<acme:action-button url="home/user/match/{0}/list.do" code="user.matches.list"/>
</acme:data-table>

<%-- Para dashboard --%>
<br/>
<jstl:if test="${option=='1' }">
	<strong>
		<spring:message code="administrator.quantityOfMatchesAudited"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='9' }">
	<strong>
		<spring:message code="administrator.maxNumberOfBartersPerUser"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='10' }">
	<strong>
		<spring:message code="administrator.numberCancelledBarters"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='11' }">
	<strong>
		<spring:message code="administrator.quantityMatches"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>