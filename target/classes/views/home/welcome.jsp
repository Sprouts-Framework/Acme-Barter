<%--
 * welcome.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<!-- TODO: Welcome message and list must be changed -->

<div class="jumbotron">
	<p><spring:message code="welcome.message" /></p>
	<p><a href="home/gym/list.do" class="btn btn-primary"><spring:message code="welcome.gyms" /> &raquo;</a></p>
</div>







