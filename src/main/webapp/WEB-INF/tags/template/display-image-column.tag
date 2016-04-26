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
 
<%@ attribute name="src" required="true" %>

<%@ attribute name="width" required="false" %>
<%@ attribute name="height" required="false" %>

<jstl:if test="${width == null}">
	<jstl:set var="width" value="200" />
</jstl:if>
<jstl:if test="${height == null}">
	<jstl:set var="height" value="200" />
</jstl:if>

<%-- Definition --%>

<div class="row text-center">
	<div class="col-xs-12 col-xs-offset-6 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4"><img src="${src}" width="${width}px" height="${height}px" class="img-circle"/></div>
</div>
<br/>
