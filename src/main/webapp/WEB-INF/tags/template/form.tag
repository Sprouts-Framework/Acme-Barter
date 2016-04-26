<%--
 * form.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java"
	import="java.util.Collection,java.util.ArrayList,java.util.Map,javax.servlet.jsp.tagext.JspFragment" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%@ attribute name="modelAttribute" required="true" %>
<%@ attribute name="action" required="false" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="readOnly" required="false" %>

<jstl:if test="${id == null}">
	<jstl:set var="id" value="${modelAttribute}-form" />
</jstl:if>

<jstl:if test="${readOnly == null}">
	<jstl:set var="readOnly" value="false" />
</jstl:if>

<form:form id="${id}" modelAttribute="${modelAttribute}" action="${action}" >

	<jstl:if test="${failureCode != null}">
		<acme:alert code="${failureCode}" arguments="${failureArgument}" type="alert-danger"/>		
	</jstl:if>
	
	<jsp:doBody />
	
</form:form>

<jstl:if test="${readOnly.equals('true')}">
	<script type="text/javascript">
	//<![CDATA[
		$(document).ready(function() {
			$('#${id} :input').attr('readonly', true);			
		});
	//]]>
	</script>
</jstl:if>

