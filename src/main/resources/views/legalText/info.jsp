<%@ include file="../template/libraries.jsp" %>

<spring:message code="match.legalText.info" var="legalTextInfo"/>

<div class="panel panel-default">
		<div class="panel-heading"><jstl:out value="${legalTextInfo}"/></div>
  		<div id="panelContent" class="panel-body">
  			<p>
   				<jstl:out value="${legalText.text}"/>
   			</p>
	  	</div>
</div>