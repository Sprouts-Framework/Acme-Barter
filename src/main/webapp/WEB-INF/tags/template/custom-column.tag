<%--
 * custom-column.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@tag import="org.hibernate.annotations.Columns"%>
<%@ tag language="java"
	   import="java.util.Collection, java.util.Map, java.util.HashMap" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%! @SuppressWarnings("unchecked") %>
 
<%@ attribute name="code" required="false" %>
<%@ attribute name="definition" required="false" fragment="true" %>
<%@ attribute name="width" required="false" %>

<jstl:if test="${width == null}">
	<jstl:set var="width" value="inherited" />
</jstl:if>

<jsp:doBody var="body" />

<%! @SuppressWarnings("unchecked") %>

<%
	Collection<Map<String, Object>> table;
	Map<String, Object> column;

	column = new HashMap<String, Object>();
	column.put("type", "custom-column");	
	column.put("code", jspContext.getAttribute("code"));		
	column.put("definition", jspContext.getAttribute("definition"));
	column.put("body", jspContext.getAttribute("body"));
	column.put("sortable", false);
	column.put("width", jspContext.getAttribute("width"));
	
	table = (Collection<Map<String, Object>>)request.getAttribute("__data_table_columns");
	table.add(column);
 %>


