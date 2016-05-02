<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<acme:protected path="id"/>
	<acme:protected path="version"/>
	
	
	<acme:textbox-input code="folder.name" path="name"/>

	<acme:submit-button code="${action}" name="${action}" />
	
	
		<acme:cancel-button code="return.button" url="folder/${actor}/list.do" />


</acme:form>