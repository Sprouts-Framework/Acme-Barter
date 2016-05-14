
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

<sprouts:form modelAttribute="modelObject"
	action="${requestScope['javax.servlet.forward.request_uri']}">

	<tiles:insertAttribute name="extendedForm" />

	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="sign-up.legend.personalInfo" />
			</legend>
			<sprouts:textbox-input code="sign-up.name.field" path="name" />
			<sprouts:textbox-input code="sign-up.surname.field" path="surname" />
			<sprouts:textbox-input code="sign-up.contactPhone.field" path="phone" />
		</fieldset>
	</div>


	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="sign-up.legend.accountInfo" />
			</legend>
			<sprouts:textbox-input code="sign-in.username" path="username" />
			<sprouts:password-input code="sign-in.password" path="password" />
			<sprouts:password-input code="sign-in.password" path="password2" />
			<security:authorize access="isAnonymous()">
				<sprouts:checkbox labelSize="4" code="sign-up.check" path="checkBox"/>
			</security:authorize>
		</fieldset>
	</div>

	<sprouts:submit-or-cancel submitCode="sign-up.sign-up"
		backUrl="home/welcome.do" />
</sprouts:form>