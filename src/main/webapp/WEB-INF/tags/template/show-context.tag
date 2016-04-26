<%--
 * show-context.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" 
	   import="java.util.Enumeration, java.util.Map, java.util.List" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%@ attribute name="label" required="false" %>
<%@ attribute name="showSystem" required="false" type="java.lang.Boolean" %>
<%@ attribute name="scope" required="false" type="java.lang.Integer" %>

<jstl:if test="${label == null}">
	<jstl:set var="label" value="Current context:" />
</jstl:if>

<%
	Enumeration<?> names;
	Boolean showSystem;
	Integer scope;

	scope = (Integer)jspContext.getAttribute("scope");
	if (scope == null)
		scope = 2;
	else 
		scope = scope + 1;	
	
	names = jspContext.getAttributeNamesInScope(scope);
	showSystem = (Boolean) jspContext.getAttribute("showSystem"); 
	response.getWriter().format("<div class=\"alert alert-info\"><strong>%s</strong><ul>", jspContext.getAttribute("label"));
	while (names.hasMoreElements())
	{
		String name;
	    Object value;
	    
	    name = (String) names.nextElement();	    
	    value = jspContext.getAttribute(name, scope);
	    if (showSystem != null && showSystem || !name.matches("^.*(\\.|__|[Ss][Pp][Rr][Ii][Nn][Gg]).*$"))
	    	response.getWriter().format("<li>%s = %s</li>", name, value);	    
	}
    response.getWriter().println("</ul></div>");	
 %>

