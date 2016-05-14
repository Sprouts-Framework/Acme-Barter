<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:select items="${barters}" itemLabel="title" code="barter.offered" path="offered"/>
	<sprouts:select items="${barters}" itemLabel="title" code="barter.requested" path="requested"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="home/barter/list.do" />

</sprouts:form>