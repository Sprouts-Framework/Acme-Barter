<%@ tag language="java" body-content="empty"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>


<%@ attribute name="submitCode" required="true"%>
<%@ attribute name="name" required="false"%>
<%@ attribute name="backUrl" required="false"%>


<button type="submit" name="${name}" class="btn btn-primary">
	<spring:message code="${submitCode}" />
</button>


<a style="margin-left: 15px;" href="${backUrl}"><spring:message
		code="sign-in.cancel" /></a>