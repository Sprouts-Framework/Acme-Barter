<%--
  * body.jsp
  *
  * Copyright (C) 2013 Universidad de Sevilla
  * 
  * The use of this project is hereby constrained to the conditions of the 
  * TDG Licence, a copy of which you may download from 
  * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<jstl:set var="successCode" value="${paramValues['successCode'][0]}" />
<jstl:set var="successArgument" value="${paramValues['successArgument'][0]}" />

<jstl:if test="${successCode != null}">
	<sprouts:alert code="${successCode}" arguments="${successArgument}" type="alert-success" />		
</jstl:if>

<div class="panel">
	<div class="panel-heading">
		<h1>
			<tiles:importAttribute name="title" toName="title" />
			<spring:message code="${title}" />
		</h1>
	</div>
	<div class="panel-body">
		<div>
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</div>

