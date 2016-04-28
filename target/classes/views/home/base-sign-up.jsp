
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

<acme:form modelAttribute="modelObject"
	action="${requestScope['javax.servlet.forward.request_uri']}">

	<tiles:insertAttribute name="extendedForm" />

	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="sign-up.legend.personalInfo" />
			</legend>
			<acme:textbox-input code="sign-up.name.field" path="name" />
			<acme:textbox-input code="sign-up.surname.field" path="surname" />
			<acme:textbox-input code="sign-up.contactPhone.field" path="phone" />
		</fieldset>
	</div>


	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="sign-up.legend.accountInfo" />
			</legend>
			<acme:textbox-input code="sign-in.username" path="username" />
			<acme:password-input code="sign-in.password" path="password" />
			<acme:password-input code="sign-in.password" path="password2" />
			
			<acme:checkbox labelSize="4" code="sign-up.check" path="checkBox"/>
		</fieldset>
	</div>

	<acme:submit-or-cancel submitCode="sign-up.sign-up"
		backUrl="home/welcome.do" />
</acme:form>