<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="true">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="requesteds"/>
	<form:hidden path="offereds"/>
	<form:hidden path="user"/>
	
	<sprouts:protected path="requesteds"/>
	<sprouts:protected path="offereds"/>
	<sprouts:protected path="user"/>
	
	
	<sprouts:textbox-input code="barter.title" path="title"/>
	<sprouts:textbox-input code="barter.moment" path="moment"/>
	<sprouts:textbox-input code="barter.cancelled" path="cancelled"/>
	
	
	<spring:message code="barter.offered.name" var="offeredItem"/>
	<h3><jstl:out value="${offeredItem}"/></h3>
	
	<sprouts:textbox-input code="barter.item.name" path="offered.name"/>
	<sprouts:textarea-input code="barter.item.description" path="offered.description"/>
	<sprouts:textarea-input code="barter.item.pictures" path="offered.pictures"/>
	
	
	<spring:message code="barter.requested.name" var="requestedItem"/>
	<h3><jstl:out value="${requestedItem}"/></h3>
	
	<sprouts:textbox-input code="barter.item.name" path="requested.name"/>
	<sprouts:textarea-input code="barter.item.description" path="requested.description"/>
	<sprouts:textarea-input code="barter.item.pictures" path="requested.pictures"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="barter/administrator/list.do" />

</sprouts:form>