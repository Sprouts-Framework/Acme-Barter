<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<acme:textbox-input code="barter.title" path="title"/>
	
	<spring:message code="barter.offered.name" var="offeredItem"/>
	<h3><jstl:out value="${offeredItem}"/></h3>
	
	<acme:textbox-input code="barter.item.name" path="offeredName"/>
	<acme:textarea-input code="barter.item.description" path="offeredDescription"/>
	<acme:textarea-input code="barter.item.pictures" path="offeredPictures"/>
	
	
	<spring:message code="barter.requested.name" var="requestedItem"/>
	<h3><jstl:out value="${requestedItem}"/></h3>
	
	<acme:textbox-input code="barter.item.name" path="requestedName"/>
	<acme:textarea-input code="barter.item.description" path="requestedDescription"/>
	<acme:textarea-input code="barter.item.pictures" path="requestedPictures"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/barter/list.do" />

</acme:form>