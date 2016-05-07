<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="requestSignedDate"/>
	<form:hidden path="offerSignedDate"/>
	<form:hidden path="report"/>
	<form:hidden path="auditor"/>
	<form:hidden path="legalText"/>
	<form:hidden path="offered"/>
	<form:hidden path="requested"/>
	<form:hidden path="cancelled" />
	
	<jstl:choose>
		<jstl:when test="${type == 'cancel' }">
			<div class="row fieldset-btm-margin">
				<div class="col-md-12">
					<spring:message code="match.cancel.message"  />
				</div>
			</div>
		</jstl:when>
		<jstl:when test="${type == 'sign' }">
			<div class="row fieldset-btm-margin">
				<div class="col-md-12">
					<spring:message code="match.sign.message" />
				</div>
			</div>
		</jstl:when>
	</jstl:choose>
	
	<acme:protected path="moment"/>
	<acme:protected path="id"/>
	<acme:protected path="version"/>

	<acme:submit-button code="${action}" name="${action}"/>
	<acme:cancel-button code="return.button" url="match/user/list.do" />

</acme:form>