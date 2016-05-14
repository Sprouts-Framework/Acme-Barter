<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:hidden-field path="id"/>
	<sprouts:hidden-field path="version"/>
	<sprouts:hidden-field path="moment"/>
	<sprouts:hidden-field path="requestSignedDate"/>
	<sprouts:hidden-field path="offerSignedDate"/>
	<sprouts:hidden-field path="cancelled"/>
	<sprouts:hidden-field path="auditor"/>
	<sprouts:hidden-field path="legalText"/>
	<sprouts:hidden-field path="offered"/>
	<sprouts:hidden-field path="requested"/>
	
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	<sprouts:protected path="moment"/>
	<sprouts:protected path="requestSignedDate"/>
	<sprouts:protected path="offerSignedDate"/>
	<sprouts:protected path="cancelled"/>
	<sprouts:protected path="auditor"/>
	<sprouts:protected path="legalText"/>
	<sprouts:protected path="offered"/>
	<sprouts:protected path="requested"/>
	
	<sprouts:textarea-input code="match.report" path="report"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="match/auditor/list.do" />

</sprouts:form>