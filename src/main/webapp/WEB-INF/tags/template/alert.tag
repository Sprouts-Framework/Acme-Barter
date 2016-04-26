<%--
 * alert.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"
	   import="java.util.Collection, java.util.Map, java.util.HashMap" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%@ attribute name="code" required="true" %>
<%@ attribute name="arguments" required="false" %> 
<%@ attribute name="type" required="false" %>

<jstl:if test="${type == null}">
	<jstl:set var="type" value="alert-danger" />
</jstl:if>

<div class="alert ${type}">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<spring:message code="${code}" arguments="${arguments}" var="message" />
	<%
		String message;
		
		message = (String)jspContext.getAttribute("message");	
		if (message == null || message.equals("*"))
			throw new RuntimeException((Throwable)jspContext.getAttribute("throwable"));
	%>	
	<jstl:out value="${message}" />	
</div>