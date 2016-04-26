<%--
 * textbox.tag
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="labelSize" required="false" %>
<%@ attribute name="boxSize" required="false" %>

<jstl:if test="${labelSize == null}">
	<jstl:set var="labelSize" value="2" />
</jstl:if>
<jstl:if test="${boxSize == null}">
	<jstl:set var="boxSize" value="1" />
</jstl:if>

<%-- Definition --%>

<div class="form-group">
			<div>
				<form:checkbox class="col-sm-${boxSize} " path="${path}"/>
			</div>
			<form:label class="col-${labelSize} control-label" path="${path}">
				<spring:message code="${code}"/>
			</form:label>
			<br/>
			<form:errors path="${path}" class="text-danger" />
</div>
