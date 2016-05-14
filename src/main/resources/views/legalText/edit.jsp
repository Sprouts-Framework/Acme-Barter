<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:hidden-field path="id"/>
	<sprouts:hidden-field path="version"/>
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	
	<sprouts:textarea-input code="legalText.text" path="text"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="legalText/administrator/list.do" />

</sprouts:form>