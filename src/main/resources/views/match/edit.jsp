<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cancelled"/>
	<form:hidden path="moment"/>
	
	<sprouts:protected path="cancelled"/>
	<sprouts:protected path="moment"/>
	
	<sprouts:select items="${offereds}" itemLabel="title" code="match.barter.offered" path="offered"/>
	<sprouts:select items="${requesteds}" itemLabel="title" code="match.barter.requested" path="requested"/>	
	<sprouts:select id="legalTextSelect" items="${legalTexts}" itemLabel="id" code="match.legalText" path="legalText" onchange="javascript: loadLegalTextInfo()"/>
	
	<spring:message code="match.legalText.info" var="legalTextInfo"/>
	
	<div id="legalPanel">
	</div>

	<sprouts:submit-button code="${action}" name="${action}"/>
	<sprouts:cancel-button code="return.button" url="home/match/list.do" />

</sprouts:form>

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