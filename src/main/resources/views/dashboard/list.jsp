<%--
 * list.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>


<h4><spring:message code="administrator.select"/></h4>

<h4>
	<a href="dashboard/administrator/auditor/0/list.do">
		<spring:message code="administrator.auditorsWithMoreMatchesAudited"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/1/list.do">
		<spring:message code="administrator.usersWithMoreMatchesAudited"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/2/list.do">
		<spring:message code="administrator.radioOfBarter"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/3/list.do">
		<spring:message code="administrator.totalNumberOfUsers"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/4/list.do">
		<spring:message code="administrator.totalNumberOfBarters"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/5/list.do">
		<spring:message code="administrator.totalNumberOfCancelledBarters"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/6/list.do">
		<spring:message code="administrator.usersWhoCreatedMaxNumOfBarters"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/7/list.do">
		<spring:message code="administrator.usersWhoAreNotActive"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/8/list.do">
		<spring:message code="administrator.maxMinAverageBartersPerUser"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/9/list.do">
		<spring:message code="administrator.theUsersWhoHaveRegisteredMoreBarters"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/10/list.do">
		<spring:message code="administrator.theUsersWhoHaveCancelledMoreBarters"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/user/11/list.do">
		<spring:message code="administrator.theUsersWhoHaveMoreMatches"/>
	</a>
</h4>

<jstl:if test="${option=='2' }">
	<strong><spring:message code="administrator.radioOfBarter"/>:</strong> <jstl:out value="${radioOfBarter}"/>
</jstl:if>

<jstl:if test="${option=='3' }">
	<strong><spring:message code="administrator.totalNumberOfUsers"/>:</strong> <jstl:out value="${totalNumberOfUsers}"/>
</jstl:if>

<jstl:if test="${option=='4' }">
	<strong><spring:message code="administrator.totalNumberOfBarters"/>:</strong> <jstl:out value="${totalNumberOfBarters}"/>
</jstl:if>

<jstl:if test="${option=='5' }">
	<strong><spring:message code="administrator.totalNumberOfCancelledBarters"/>:</strong> <jstl:out value="${totalNumberOfCancelledBarters}"/>
</jstl:if>

<jstl:if test="${option=='8' }">
	<strong><spring:message code="administrator.minNumberOfBartersPerUser"/>:</strong> <jstl:out value="${minNumberOfBartersPerUser}"/>
	<br />
	<strong><spring:message code="administrator.maxNumberOfBartersPerUser"/>:</strong> <jstl:out value="${maxNumberOfBartersPerUser}"/>
	<br />
	<strong><spring:message code="administrator.averageNumberOfBartersPerUser"/>:</strong> <jstl:out value="${averageNumberOfBartersPerUser}"/>
</jstl:if>