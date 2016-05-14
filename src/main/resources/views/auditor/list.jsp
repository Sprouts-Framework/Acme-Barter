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


<sprouts:data-table i18n="datatables.language">
	<sprouts:data-column code="user.name" path="name" sortable="true"/>
	<sprouts:data-column code="user.surname" path="surname"/>
	<sprouts:data-column code="user.username" path="userAccount.username"/>
</sprouts:data-table>

<%-- Para dashboard --%>
<br/>
<jstl:if test="${option=='0' }">
	<strong>
		<spring:message code="administrator.quantityOfMatchesAudited"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>