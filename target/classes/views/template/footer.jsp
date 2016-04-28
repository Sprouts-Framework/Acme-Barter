<%--
  * footer.jsp
  *
  * Copyright (C) 2013 Universidad de Sevilla
  * 
  * The use of this project is hereby constrained to the conditions of the 
  * TDG Licence, a copy of which you may download from 
  * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<div class="panel panel-footer">
	<jsp:useBean id="date" class="java.util.Date" />
	<h5>
		Copyright &copy;
		<fmt:formatDate value="${date}" pattern="yyyy" />
		ACME, Inc.
	</h5>

	<div class="row">
		<div class="col-md-3">
			<h5>
				<spring:message code="master.about" />
			</h5>
			<ul class="list-unstyled">
				<li><a href="home/contact-us.do"><spring:message code="master.about.contact-us" /></a></li>
			</ul>
		</div>
		<div class="col-md-3">
			<h5>
				<spring:message code="master.terms-use" />
			</h5>
			<ul class="list-unstyled">
				<li><a href="home/license.do"><spring:message code="master.terms-use.license" /></a></li>
				<li><a href="home/privacy.do"><spring:message code="master.terms-use.privacy" /></a></li>
			</ul>
		</div>
		<div class="col-md-3">
			<h5>
				<spring:message code="master.follow-us" />
			</h5>
			<ul class="list-unstyled">
				<li>
					<a href="http://www.linkedin.com/company/acme">
						<span class="icon-linked-in"></span> 
						<spring:message code="master.follow-us.linked-in" />
					</a>
				</li>
				<li>
					<a href="https://twitter.com/acme">
						<span class="icon-twitter"></span> 
						<spring:message code="master.follow-us.Twitter" />
					</a>
				</li>
			</ul>
		</div>
		<div class="col-md-3">
			<h5>
				<spring:message code="master.languages" />
			</h5>
			<ul class="list-unstyled">
				<li><a href="${requestScope['javax.servlet.forward.request_uri']}?language=en"><spring:message code="master.languages.english" /></a></li>
				<li><a href="${requestScope['javax.servlet.forward.request_uri']}?language=es"><spring:message code="master.languages.spanish" /></a></li>
			</ul>
		</div>
	</div>
</div>