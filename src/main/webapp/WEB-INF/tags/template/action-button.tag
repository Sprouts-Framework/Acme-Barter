<%--
 * action-button.tag
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
<%@ attribute name="url" required="true" %>

<%! @SuppressWarnings("unchecked") %>

<%
	Collection<Map<String, Object>> table;
	Map<String, Object> column;

	column = new HashMap<String, Object>(); 
	column.put("type", "action-button");	
	column.put("code", jspContext.getAttribute("code"));		
	column.put("url", jspContext.getAttribute("url"));
	
	table = (Collection<Map<String, Object>>)request.getAttribute("__data_table_columns");
	table.add(column);
 %>


