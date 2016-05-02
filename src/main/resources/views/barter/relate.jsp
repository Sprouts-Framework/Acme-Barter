<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<acme:select items="${barters}" itemLabel="title" code="barter.offered" path="offered"/>
	<acme:select items="${barters}" itemLabel="title" code="barter.requested" path="requested"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/barter/list.do" />

</acme:form>