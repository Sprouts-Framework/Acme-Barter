<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">	
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cancelled"/>
	<form:hidden path="report"/>
	<form:hidden path="legalText"/>
	<form:hidden path="offered"/>
	<form:hidden path="requested"/>
	<form:hidden path="auditor"/>
	
	<acme:textbox-input code="match.moment" path="moment" readonly="true"/>
	<acme:textbox-input code="match.requestSignedDate" path="requestSignedDate" readonly="true"/>
	<acme:textbox-input code="match.offerSignedDate" path="offerSignedDate" readonly="true"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/match/list.do"/>
</acme:form>

