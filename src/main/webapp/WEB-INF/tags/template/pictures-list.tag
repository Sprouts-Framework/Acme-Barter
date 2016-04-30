<%--
 * action-button.tag
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java"
	import="java.util.Collection,java.util.ArrayList,java.util.Map,javax.servlet.jsp.tagext.JspFragment,org.apache.commons.beanutils.PropertyUtils"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template"%>

<%@ attribute name="code" required="false"%>
<%@ attribute name="url" required="false"%>


<%@ attribute name="pictures" required="true"%>
<%@ attribute name="size" required="true"%>
<%@ attribute name="width" required="false"%>
<%@ attribute name="height" required="false"%>

<%!JspFragment template;

	public JspFragment getTemplate() {
		return this.template;
	}

	public void setTemplate(JspFragment template) {
		this.template = template;
	}%>

<%
	Collection<Map<String, Object>> dataTableColumns;
	String mapName;

	dataTableColumns = new ArrayList<Map<String, Object>>();

	request.setAttribute("pictures", pictures);
%>

<jstl:if test="${width == null}">
	<jstl:set var="width" value="242px" />
</jstl:if>

<jstl:if test="${height == null}">
	<jstl:set var="height" value="200px" />
</jstl:if>

<jstl:set var="isEmpty" value="false" />
<jstl:forEach var="picture" items="${pictures}" end="0">
	<jstl:set var="picture" value="${fn:replace(picture, '[', '')}" />
	<jstl:set var="picture" value="${fn:replace(picture, ']', '')}" />
	<jstl:if test="${fn:length(picture) <= 1}">
		<jstl:set var="isEmpty" value="true" />
	</jstl:if>
</jstl:forEach>

<jstl:if test="${isEmpty == false}">
	<jstl:set var="count" value="0" />

	<jstl:choose>
		<jstl:when test="${size == 1 }">
			<jstl:forEach items="${pictures}" var="picture">
				<jstl:set var="picture" value="${fn:replace(picture, '[', '')}" />
				<jstl:set var="picture" value="${fn:replace(picture, ']', '')}" />
				<div class="row">
					<div class="col-sm-6 col-sm-offset-6 col-md-4 col-md-offset-4">
						<div class="thumbnail">
							<img src="${picture}" class="img-rounded" width="${width }"
								height="${height }">
							<div class="caption text-center">
								<p>
									<a href="${picture}" target="_blank" class="btn btn-primary"><spring:message
											code="picture.see.button" /></a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</jstl:forEach>
		</jstl:when>
		<jstl:otherwise>

			<jstl:forEach items="${pictures}" var="picture">

				<jstl:set var="picture" value="${fn:replace(picture, '[', '')}" />
				<jstl:set var="picture" value="${fn:replace(picture, ']', '')}" />

				<jstl:if test="${count % 3 == 0}">
					<div class="row">
				</jstl:if>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="${picture}" class="img-rounded" width="${width }"
							height="${height }">
						<div class="caption text-center">
							<p>
								<a href="${picture}" target="_blank" class="btn btn-primary"><spring:message
										code="picture.see.button" /></a>
							</p>
						</div>
					</div>
				</div>
				<jstl:set var="count" value="${count + 1}" />
				<jstl:if test="${count % 3 == 0}">
					</div>
				</jstl:if>
			</jstl:forEach>

			<jstl:if test="${count % 3 != 0}">
				</div>
			</jstl:if>
			</div>
		</jstl:otherwise>
	</jstl:choose>
</jstl:if>