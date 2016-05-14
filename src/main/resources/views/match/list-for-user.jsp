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
	<sprouts:data-column code="match.moment" path="moment" sortable="true" format="date"/>
	<sprouts:data-column code="match.requestSignedDate" path="requestSignedDate" format="date" nullCode="match.nullCode"/>
	<sprouts:data-column code="match.offerSignedDate" path="offerSignedDate" format="date" nullCode="match.nullCode"/>
	<sprouts:data-column code="match.cancelled.column" path="cancelled" falseCode="match.cancelled.false" trueCode="match.cancelled.true"/>
	<sprouts:data-column code="match.barter.offered" path="offered.title"/>
	<sprouts:data-column code="match.barter.requested" path="requested.title"/>
	
	<sprouts:action-button url="home/match/{0}/show.do" code="show.button"/>
	<security:authorize access="hasRole('User')">
		<sprouts:action-button url="match/user/cancel/{0}/update.do" code="cancel.button" />
		<sprouts:action-button url="match/user/sign/{0}/update.do" code="sign.button" />
	</security:authorize>
	<security:authorize access="hasRole('Auditor')">
		<sprouts:action-button url="match/auditor/{0}/update.do" code="report.button"/>
	</security:authorize>
</sprouts:data-table>