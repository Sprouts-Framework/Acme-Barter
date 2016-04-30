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
 
<%@ attribute name="twitter" required="false" %>
<%@ attribute name="google" required="false" %>

<jstl:if test="${twitter == null}">
	<jstl:set var="twitter" value="true"/>
</jstl:if>

<jstl:if test="${google == null}">
	<jstl:set var="google" value="true"/>
</jstl:if>

<jstl:choose>
	<jstl:when test="${(twitter == 'true') && (google == 'true') }">
		<div class="row">
		<div class="col-xs-12 col-md-3 col-sm-4">
		<spring:message code="authorize.twitter.add" var="twitter"/>
			<form id="tw_signin" action="<jstl:url value="/signin/twitter.do"/>" method="POST">
				  <button type="submit" class="btn btn-twitter">
				    <i class="fa fa-twitter"></i> | <jstl:out value="${twitter}"/>
				  </button>
			</form>
		</div>
		
		<div class="col-xs-12 col-md-3 col-sm-4">
			<spring:message code="authorize.google.add" var="google"/>
			<form id="google_signin" action="<jstl:url value="/signin/google.do"/>" method="POST">
				  <button type="submit" class="btn btn-google-plus">
				    <i class="fa fa-google"></i> | <jstl:out value="${google}"/>
				  </button>
			</form>
		</div>
		</div>
	</jstl:when>
	<jstl:when test="${twitter == 'true'}">
		<div class="row">
		<div class="col-xs-12 col-md-3 col-sm-4">
		<spring:message code="authorize.twitter.add" var="twitter"/>
			<form id="tw_signin" action="<jstl:url value="/signin/twitter.do"/>" method="POST">
				  <button type="submit" class="btn btn-twitter">
				    <i class="fa fa-twitter"></i> | <jstl:out value="${twitter}"/>
				  </button>
			</form>
		</div>
		</div>
	</jstl:when>
	<jstl:when test="${google == 'true'}">
		<div class="row">
		<div class="col-xs-12 col-md-3 col-sm-4">
			<spring:message code="authorize.google.add" var="google"/>
			<form id="google_signin" action="<jstl:url value="/signin/google.do"/>" method="POST">
				  <button type="submit" class="btn btn-google-plus">
				    <i class="fa fa-google"></i> | <jstl:out value="${google}"/>
				  </button>
			</form>
		</div>
		</div>
	</jstl:when>
</jstl:choose>