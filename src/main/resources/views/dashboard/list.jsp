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

<jstl:if test="${option=='2' }">
	<strong><spring:message code="administrator.radioOfBarter"/>:</strong> <jstl:out value="${radioOfBarter}"/>
</jstl:if>

<jstl:if test="${option=='3' }">
	<strong><spring:message code="administrator.totalNumberOfUsers"/>:</strong> <jstl:out value="${totalNumberOfUsers}"/>
</jstl:if>