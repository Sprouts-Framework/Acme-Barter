<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />

	<jstl:if test="${crudAction != 'showing' }">
		<sprouts:hidden-field path="moment" />
		<sprouts:hidden-field path="createdAt" />
		<sprouts:hidden-field path="updatedAt" />
		<sprouts:protected path="moment"/>
		<sprouts:protected path="createdAt" />
		<sprouts:protected path="updatedAt" />
	</jstl:if>

	
	<jstl:if test="${crudAction == 'showing' }">
		<sprouts:textbox-input code="message.recipient" path="recipient.userAccount.username"/>
		<sprouts:textbox-input code="message.sender" path="sender.userAccount.username"/>
		<sprouts:textbox-input code="message.moment" path="moment"/>
	</jstl:if>
	<div class="form-group">
			<div class="row">
				<form:label class="col-sm-2 control-label" path="priority"><spring:message code="message.priority"/></form:label>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<spring:message var="lowpriority" code="message.lowPriority"/>
					<spring:message var="normalpriority" code="message.normalPriority"/>
					<spring:message var="highpriority" code="message.highPriority"/>
					<jstl:if test="${crudAction == 'showing' }">
						<fieldset disabled>
					</jstl:if>
						<form:select class="form-control" path="priority">
							<form:option label="${lowpriority}" value="-1"/>
							<form:option label="${normalpriority}" value="0"/>
							<form:option label="${highpriority}" value="1"/>
						</form:select>
					<jstl:if test="${crudAction == 'showing' }">
						</fieldset>
					</jstl:if>
				</div>
			</div>
			<form:errors class="alert alert-danger col-sm-3" path="priority"/>
			<br/>
	</div>
	<sprouts:textbox-input code="message.subject" path="subject"/>
	<sprouts:textarea-input code="message.body" path="body"/>
	<jstl:if test="${crudAction == 'creating'}">
		<sprouts:select items="${actors}" itemLabel="userAccount.username" code="message.recipient" path="recipient"/>
	</jstl:if>
	<jstl:if test="${crudAction != 'showing' }">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	
	<sprouts:cancel-button code="return.button" url="folder/${actor}/list.do" />
	

</sprouts:form>