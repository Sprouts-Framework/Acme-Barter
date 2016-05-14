
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

<sprouts:form modelAttribute="credentials" action="j_spring_security_check" >

	<sprouts:textbox-input code="sign-in.username" path="username" />
	<sprouts:password-input code="sign-in.password" path="password" />

	<sprouts:submit-button code="sign-in.sign-in" />
	<sprouts:cancel-button code="sign-in.cancel" url="home/welcome.do" />

</sprouts:form>


<security:authorize access="isAnonymous()">
<br/>
	<sprouts:social-account-sign-in/>
</security:authorize>