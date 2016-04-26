<%--
  * header.jsp
  *
  * Copyright (C) 2013 Universidad de Sevilla
  * 
  * The use of this project is hereby constrained to the conditions of the 
  * TDG Licence, a copy of which you may download from 
  * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<div class="navbar">
	<div>
		<img src="images/header.png" alt="Acme, Inc. - Your job mate!"
			class="img-rounded img-responsive" />
	</div>
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#"><spring:message
				code="master.home" /></a>
	</div>
	<div class="navbar-collapse collapse">
		<!-- The following sentence includes menu in the header -->
		<jsp:include page="menu.jsp" />
	</div>
</div>
