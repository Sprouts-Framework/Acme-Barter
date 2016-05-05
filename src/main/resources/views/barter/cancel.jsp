<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="requesteds"/>
	<form:hidden path="offereds"/>
	<form:hidden path="user"/>
	
	<acme:protected path="requesteds"/>
	<acme:protected path="offereds"/>
	<acme:protected path="user"/>
	
	
	<acme:textbox-input code="barter.title" path="title"/>
	<acme:textbox-input code="barter.moment" path="moment"/>
	<acme:textbox-input code="barter.cancelled" path="cancelled"/>
	
	
	<spring:message code="barter.offered.name" var="offeredItem"/>
	<h3><jstl:out value="${offeredItem}"/></h3>
	
	<acme:textbox-input code="barter.item.name" path="offered.name"/>
	<acme:textarea-input code="barter.item.description" path="offered.description"/>
	<acme:textarea-input code="barter.item.pictures" path="offered.pictures"/>
	
	
	<spring:message code="barter.requested.name" var="requestedItem"/>
	<h3><jstl:out value="${requestedItem}"/></h3>
	
	<acme:textbox-input code="barter.item.name" path="requested.name"/>
	<acme:textarea-input code="barter.item.description" path="requested.description"/>
	<acme:textarea-input code="barter.item.pictures" path="requested.pictures"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="barter/administrator/list.do" />

</acme:form>