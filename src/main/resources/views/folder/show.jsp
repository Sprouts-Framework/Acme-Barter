<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<acme:hidden-field path="actor"/>
	<acme:hidden-field path="createdAt"/>
	<acme:hidden-field path="updatedAt"/>
	<acme:hidden-field path="system"/>

	<acme:protected path="id"/>
	<acme:protected path="version"/>
	<acme:protected path="actor"/>
	<acme:protected path="createdAt"/>
	<acme:protected path="updatedAt"/>
	<acme:protected path="system"/>
	
	<acme:textbox-input code="folder.name" path="name"/>
	
	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="folder/${actor}/list.do" />
</acme:form>