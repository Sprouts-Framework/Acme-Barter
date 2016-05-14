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


<sprouts:data-table i18n="datatables.language" >
	<sprouts:data-column code="barter.username" path="user.userAccount.username" />
	<sprouts:data-column code="barter.moment" path="moment" sortable="true" format="date"/>
	<sprouts:data-column code="barter.title" path="title" />
	<sprouts:data-column code="barter.requested.name" path="requested.name" />
	<sprouts:data-column code="barter.offered.name" path="offered.name" />
	<security:authorize access="hasRole('Administrator')">
		<sprouts:data-column code="barter.cancelled.column" path="cancelled" falseCode="barter.cancelled.false" trueCode="barter.cancelled.true"/>
	</security:authorize>
	
	<sprouts:action-button url="home/barter/{0}/show.do" code="show.button"/>
	<security:authorize access="hasRole('User')">
		<sprouts:action-button url="match/user/{0}/create.do" code="match.create.title"/>
	</security:authorize>
	<security:authorize access="hasRole('Administrator')">
		<sprouts:action-button url="barter/administrator/relate/{0}/update.do" code="relate.button"/>
	</security:authorize>
	<security:authorize access="hasRole('Administrator')">
		<sprouts:action-button url="barter/administrator/{0}/update.do" code="cancel.button"/>
	</security:authorize>
</sprouts:data-table>