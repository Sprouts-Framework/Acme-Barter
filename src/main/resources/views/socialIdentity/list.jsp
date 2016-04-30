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
	<acme:data-column code="socialIdentity.nick" path="nick" sortable="true"/>
	<acme:data-column code="socialIdentity.network" path="socialNetwork" sortable="true"/>
	<acme:data-column code="socialIdentity.home" path="homePage" format="url"/>
	<acme:data-column code="socialIdentity.picture" path="picture" format="image" imgSize="100x100"/>	
	
	<acme:action-button url="home/user/socialIdentity/{0}/show.do" code="user.socialIdentity.show"/>
</acme:data-table>