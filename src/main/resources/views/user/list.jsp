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


<acme:data-table i18n="datatables.language" >
	<acme:data-column code="user.name" path="name" sortable="true"/>
	<acme:data-column code="user.surname" path="surname"/>
	
	<acme:action-button url="home/user/socialIdentity/{0}/list.do" code="user.socialIdentity.list"/>
	<acme:action-button url="home/user/barter/{0}/list.do" code="user.barter.list"/>
	<acme:action-button url="home/user/match/{0}/list.do" code="user.matches.list"/>
</acme:data-table>