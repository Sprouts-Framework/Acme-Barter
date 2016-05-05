<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cancelled"/>
	<form:hidden path="moment"/>
	
	<acme:protected path="cancelled"/>
	<acme:protected path="moment"/>
	
	<acme:select items="${offereds}" itemLabel="title" code="match.barter.offered" path="offered"/>
	<acme:select items="${requesteds}" itemLabel="title" code="match.barter.requested" path="requested"/>	
	<acme:select id="legalTextSelect" items="${legalTexts}" itemLabel="id" code="match.legalText" path="legalText" onchange="javascript: loadLegalTextInfo()"/>
	
	<spring:message code="match.legalText.info" var="legalTextInfo"/>
	
	<div id="legalPanel">
	</div>

	<acme:submit-button code="${action}" name="${action}"/>
	<acme:cancel-button code="return.button" url="home/match/list.do" />

</acme:form>

<script type="text/javascript">
		function loadLegalTextInfo() {
			var keyword = $('#legalTextSelect').val();
			var placeholder = $('#legalPanel');
			if(keyword == 0){
				placeholder.hide();
			} else {
				placeholder.show();
				placeholder.load("home/legalText/"+keyword+"/show.do");
			}
		}
</script>