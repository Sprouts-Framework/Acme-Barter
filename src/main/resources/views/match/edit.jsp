<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<acme:hidden-field path="id"/>
	<acme:hidden-field path="version"/>
	<acme:hidden-field path="moment"/>
	<acme:hidden-field path="requestSignedDate"/>
	<acme:hidden-field path="offerSignedDate"/>
	<acme:hidden-field path="cancelled"/>
	<acme:hidden-field path="auditor"/>
	<acme:hidden-field path="legalText"/>
	<acme:hidden-field path="offered"/>
	<acme:hidden-field path="requested"/>
	
	<acme:protected path="id"/>
	<acme:protected path="version"/>
	<acme:protected path="moment"/>
	<acme:protected path="requestSignedDate"/>
	<acme:protected path="offerSignedDate"/>
	<acme:protected path="cancelled"/>
	<acme:protected path="auditor"/>
	<acme:protected path="legalText"/>
	<acme:protected path="offered"/>
	<acme:protected path="requested"/>
	
	<acme:textarea-input code="match.report" path="report"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="match/auditor/list.do" />

</acme:form>