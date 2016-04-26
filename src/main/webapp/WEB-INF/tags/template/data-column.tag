<%--
 * data-column.tag
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
<%@ attribute name="path" required="false" %>
<%@ attribute name="format" required="false" %>
<%@ attribute name="sortable" required="false" type="java.lang.Boolean" %>
<%@ attribute name="width" required="false" %>
<%@ attribute name="sortBy" required="false" %>
<%@ attribute name="nullCode" required="false" %>
<%@ attribute name="trueCode" required="false" %>
<%@ attribute name="falseCode" required="false" %>
<%@ attribute name="outFormat" required="false" %>
<%@ attribute name="toShow" required="false" %>
<%@ attribute name="imgSize" required="false" %>

<jstl:if test="${format == null}">
	<jstl:set var="format" value="{0}" />
</jstl:if>

<jstl:if test="${sortable == null}">
	<jstl:set var="sortable" value="${true}" />
</jstl:if>

<jstl:if test="${width == null}">
	<jstl:set var="width" value="inherited" />
</jstl:if>

<jstl:if test="${outFormat == null}">
	<jstl:set var="outFormat" value="{0}" />
</jstl:if>

<jstl:if test="${toShow == null}">
	<jstl:set var="toShow" value="{0}" />
</jstl:if>

<jstl:if test="${trueCode == null}">
	<jstl:set var="trueCode" value="{0}" />
</jstl:if>

<jstl:if test="${falseCode == null}">
	<jstl:set var="falseCode" value="{0}" />
</jstl:if>

<jstl:if test="${imgSize == null}">
	<jstl:set var="imgSize" value="{0}" />
</jstl:if>

<%! @SuppressWarnings("unchecked") %>

<%
	Collection<Map<String, Object>> table;
	Map<String, Object> column;
	Boolean sortable;

	column = new HashMap<String, Object>(); 	
	column.put("type", "data-column");
	column.put("code", jspContext.getAttribute("code"));
	column.put("path", jspContext.getAttribute("path"));	
	column.put("format", jspContext.getAttribute("format"));
	column.put("sortable", jspContext.getAttribute("sortable"));
	column.put("width", jspContext.getAttribute("width"));
	column.put("outFormat", jspContext.getAttribute("outFormat"));
	column.put("toShow", jspContext.getAttribute("toShow"));
	column.put("trueCode", jspContext.getAttribute("trueCode"));
	column.put("falseCode", jspContext.getAttribute("falseCode"));
	column.put("imgSize", jspContext.getAttribute("imgSize"));
	
	table = (Collection<Map<String, Object>>)request.getAttribute("__data_table_columns");
	table.add(column);
 %>


