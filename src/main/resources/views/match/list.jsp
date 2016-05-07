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
	<acme:data-column code="match.moment" path="moment" sortable="true" format="date"/>
	<acme:data-column code="match.requestSignedDate" path="requestSignedDate" format="date" nullCode="match.nullCode"/>
	<acme:data-column code="match.offerSignedDate" path="offerSignedDate" format="date" nullCode="match.nullCode"/>
	<acme:data-column code="match.cancelled" path="cancelled" falseCode="match.cancelled.false" trueCode="match.cancelled.true"/>
	<acme:data-column code="match.barter.offered" path="offered.title"/>
	<acme:data-column code="match.barter.requested" path="requested.title"/>
	
	<acme:action-button url="home/match/{0}/show.do" code="show.button"/>
	<security:authorize access="hasRole('Auditor')">
		<acme:action-button url="match/auditor/{0}/update.do" code="report.button"/>
		<acme:action-button url="match/auditor/self-assign/{0}/update.do" code="self-assign.button"/>
	</security:authorize>
</acme:data-table>