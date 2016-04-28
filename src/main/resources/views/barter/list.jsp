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
	<acme:data-column code="barter.moment" path="moment" sortable="true" format="date"/>
	<acme:data-column code="barter.title" path="title" />
	<acme:data-column code="barter.requested.name" path="requested.name" />
	<acme:data-column code="barter.offered.name" path="requested.name" />
	
	<acme:action-button url="barter/{0}/display.do" code="show.button"/>
</acme:data-table>