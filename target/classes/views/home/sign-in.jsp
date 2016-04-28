
<%--
 * sign-in.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<acme:form modelAttribute="credentials" action="j_spring_security_check" >

	<acme:textbox-input code="sign-in.username" path="username" />
	<acme:password-input code="sign-in.password" path="password" />

	<acme:submit-button code="sign-in.sign-in" />
	<acme:cancel-button code="sign-in.cancel" url="home/welcome.do" />

</acme:form>


<security:authorize access="isAnonymous()">
<br/>
<div class="row">
<div class="col-xs-12 col-md-3 col-sm-4">
<spring:message code="authorize.twitter" var="twitter"/>
	<form id="tw_signin" action="<jstl:url value="/signin/twitter.do"/>" method="POST">
		  <button type="submit" class="btn btn-twitter">
		    <i class="fa fa-twitter"></i> | <jstl:out value="${twitter}"/>
		  </button>
	</form>
</div>

<div class="col-xs-12 col-md-3 col-sm-4">
	<spring:message code="authorize.google" var="google"/>
	<form id="google_signin" action="<jstl:url value="/signin/google.do"/>" method="POST">
		  <button type="submit" class="btn btn-google-plus">
		    <i class="fa fa-google"></i> | <jstl:out value="${google}"/>
		  </button>
	</form>
</div>
</div>
</security:authorize>