<%--
 * select.tag
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
<%@ taglib prefix="acmeSpecific" tagdir="/WEB-INF/tags/template" %>

<%-- Attributes --%> 

<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>
<%@ attribute name="items" required="true" type="java.util.Collection" %>
<%@ attribute name="itemLabel" required="true" %>
<%@ attribute name="readonly" required="false" %>

<%@ attribute name="id" required="false" %>
<%@ attribute name="onchange" required="false" %>
<%@ attribute name="labelSize" required="false" %>
<%@ attribute name="boxSize" required="false" %>

<jstl:if test="${labelSize == null}">
	<jstl:set var="labelSize" value="2" />
</jstl:if>
<jstl:if test="${boxSize == null}">
	<jstl:set var="boxSize" value="6" />
</jstl:if>

<jstl:if test="${id == null}">
	<jstl:set var="id" value="${UUID.randomUUID().toString()}" />
</jstl:if>

<jstl:if test="${onchange == null}">
	<jstl:set var="onchange" value="javascript: return true;" />
</jstl:if>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<%-- Definition --%>

<div class="form-group">
	<div class="row">
		<form:label class="col-sm-${labelSize} col-xs-6 control-label" path="${path}"><spring:message code="${code}"/></form:label>
	</div>
	<div class="row">
			<div class="col-sm-${boxSize} col-xs-12">
				<form:select class="form-control" path="${path}" id="${id}" onchange="${onchange}"  >
					<form:option label="----"  value="0" />
					<form:options items="${items}" itemLabel="${itemLabel}" itemValue="id"/>
				</form:select>
			</div>
			<form:errors class="alert alert-danger col-sm-3" path="${path}"/>
			<br/>
	</div>
</div>
