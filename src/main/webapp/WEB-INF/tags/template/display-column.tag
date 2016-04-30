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
 
<%@ attribute name="title" required="false" %>
<%@ attribute name="data" required="false" %>
<%@ attribute name="path" required="false" %>
<%@ attribute name="message" required="false" %>
<%@ attribute name="url" required="false" %>

<jstl:if test="${message == null }">
	<jstl:set var="message" value=""/>
</jstl:if>

<jstl:if test="${formatted == null}">
	<jstl:set var="formatted" value="false" />
</jstl:if>

<%-- Definition --%>

<jstl:if test="${title != null}">
	<div class="row text-center">
		<div class="col-xs-12 col-xs-offset-6 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4"><strong><jstl:out value="${title}"/></strong></div>
	</div>
</jstl:if>

<jstl:if test="${url != null}">
		<div class="row text-center">
			<div class="col-xs-12 col-xs-offset-6 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4"><a href="${url}" target="_blank"><jstl:out value="${message}"/></a></div>
		</div>
		<br/>
</jstl:if>

<jstl:if test="${data != null}">
	<jstl:if test="${url == null}">
		<div class="row text-center">
			<div class="col-xs-12 col-xs-offset-6 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4"><jstl:out value="${data} ${message}"/></div>
		</div>
		<br/>
	</jstl:if>
</jstl:if>

<jstl:if test="${path != null }">
	<div class="row text-center">
		<div class="col-xs-12 col-xs-offset-6 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4"><spring:bind path="${path }">${status.value}</spring:bind></div>
	</div>
</jstl:if>
