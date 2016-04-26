<%--
 * moment-input.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>
<%@ attribute name="format" required="false" %>
<%@ attribute name="readonly" required="false" %>

<jstl:set var="path" value="${path.replace('.', '_')}" />
<jstl:set var="genericLocale" value="${pageContext.response.locale.language}" />
<jstl:set var="lang_es" value="es" />
<jstl:set var="lang_en" value="en" />
<spring:message code="datepicker.format" var="dateFormatProp"/>
<jstl:choose>
	<jstl:when test="${format == null}">
		<jstl:set var="format" value="format: '${dateFormatProp }'" />
	</jstl:when>
	<jstl:otherwise>
		<jstl:set var="format" value="format: '${format}'" />
	</jstl:otherwise>
</jstl:choose>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<div class="form-group">
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>
	<div id="${path}-div" class="input-group input-append date" data-date-language="${genericLocale}">		
		<form:input id="${path}" path="${path}" readonly="${readonly}" class="form-control input-append date form_datetime" />
		<span class="input-group-addon add-on"><i class="icon-calendar"></i></span>
	</div>
	<form:errors path="${path}" class="text-danger" />
</div>

<script type="text/javascript">
//<![CDATA[
   	$(document).ready(function() {
   		var readonly = $('#${path}').attr('readonly');
   		if (readonly != 'readonly') {
   			$('#${path}-div').datetimepicker({
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left", 
				<jstl:out value="${format}" escapeXml="false" />
			});
   		}
   	});
//]]>
</script>

