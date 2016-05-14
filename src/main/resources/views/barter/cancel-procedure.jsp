<%@ include file="../template/libraries.jsp"%>

<div class="row fieldset-btm-margin">
	<div class="col-md-12">
		<spring:message
			code="administrator.cancel-barters-procedure.description" />
	</div>
</div>

<form:form action="barter/administrator/cancel-procedure.do">

	<div class="row">
		<div class="col-md-12">
			<sprouts:submit-or-cancel
				submitCode="administrator.cancel-barters-procedure"
				backUrl="home/welcome.do" />
		</div>
	</div>

</form:form>