<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:textbox-input code="barter.title" path="title"/>
	
	<spring:message code="barter.offered.name" var="offeredItem"/>
	<h3><jstl:out value="${offeredItem}"/></h3>
	
	<sprouts:textbox-input code="barter.item.name" path="offeredName"/>
	<sprouts:textarea-input code="barter.item.description" path="offeredDescription"/>
	<sprouts:textarea-input code="barter.item.pictures.info" path="offeredPictures"/>
	
	
	<spring:message code="barter.requested.name" var="requestedItem"/>
	<h3><jstl:out value="${requestedItem}"/></h3>
	
	<sprouts:textbox-input code="barter.item.name" path="requestedName"/>
	<sprouts:textarea-input code="barter.item.description" path="requestedDescription"/>
	<sprouts:textarea-input code="barter.item.pictures.info" path="requestedPictures"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="home/barter/list.do" />

</sprouts:form>