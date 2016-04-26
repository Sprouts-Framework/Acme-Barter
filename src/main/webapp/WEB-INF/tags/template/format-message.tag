<%--
 * format-message.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"
	   import="
	   		java.util.Collection, java.util.Map, java.util.HashMap, java.text.MessageFormat
	   	"
 %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>
 
<%@ attribute name="format" required="true" %>
<%@ attribute name="value" required="true" type="java.lang.Object" %>

<%
	MessageFormat formatter;
	String format;
	Object value;

	format = (String) jspContext.getAttribute("format");
	value = jspContext.getAttribute("value");
	formatter = new MessageFormat(format, response.getLocale());
	value = formatter.format(new Object[] {value});	
	
	jspContext.setAttribute("value", value);
 %>

<jstl:out value="${value}" />
