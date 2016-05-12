<%@ include file="../template/libraries.jsp" %>


<!-- TODO: Menu must be changed, here's an example -->

<!-- Links that are shown to everybody -->

<ul class="nav navbar-nav">
	<security:authorize access="permitAll()">
		<li><a href="home/barter/list.do"><spring:message code="master.barter.list" /></a></li>
		<li><a href="home/user/list.do"><spring:message code="master.user.list" /></a></li>
		<li><a href="home/match/list.do"><spring:message code="master.match.list" /></a></li>
	</security:authorize>
</ul>

<!-- Links that are shown to Users -->

<ul class="nav navbar-nav">
	<security:authorize access="hasRole('User')">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.user" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="barter/user/bulletin/list.do"><spring:message code="master.barter.bulletin.list" /></a></li>
				<li><a href="barter/user/create.do"><spring:message code="master.user.create" /></a></li>
				<li><a href="match/user/bulletin/list.do"><spring:message code="master.match.bulletin" /></a></li>
				<li><a href="match/user/create.do"><spring:message code="master.match.create" /></a></li>
				<li><a href="match/user/list.do"><spring:message code="master.match.user.list" /></a></li>
				<li><a href="barter/user/list.do"><spring:message code="master.barter.user.list" /></a></li>
				<li><a href="user/user/followers/list.do"><spring:message code="master.user.followers" /></a></li>
				<li><a href="user/user/following/list.do"><spring:message code="master.user.following" /></a></li>
			</ul></li>
	</security:authorize>
</ul>

<!-- Links that are shown to Administrator -->

<ul class="nav navbar-nav">
	<security:authorize access="hasRole('Administrator')">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.administrator" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="barter/administrator/list.do"><spring:message code="master.barter.list" /></a></li>
				<li><a href="match/administrator/list.do"><spring:message code="master.match.list" /></a></li>
				<li><a href="barter/administrator/cancel-procedure.do"><spring:message code="master.barter.cancel-procedure" /></a></li>
				<li><a href="legalText/administrator/list.do"><spring:message code="master.legalText.list" /></a></li>
				<li><a href="auditor/administrator/create.do"><spring:message code="master.registerAuditor.list" /></a></li>
				<li><a href="dashboard/administrator/list.do"><spring:message code="master.dashboard.list" /></a></li>
			</ul></li>
	</security:authorize>
</ul>

<!-- Links that are shown to Auditor -->

<ul class="nav navbar-nav">
	<security:authorize access="hasRole('Auditor')">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.auditor" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="match/auditor/list.do"><spring:message code="master.auditor.list" /></a></li>
			</ul></li>
	</security:authorize>
</ul>



<!-- Links that are shown to Anonymous users -->

<security:authorize access="isAnonymous()">
	
	<ul class="nav navbar-nav navbar-right">
		<li><a href="home/sign-in.do"><spring:message
					code="master.sign-in" /></a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.sign-up" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="home/user/create.do"><spring:message
							code="master.sign-up.as-User" /></a></li>
			</ul></li>
	</ul>
</security:authorize>

<!-- Links that are shown to Authenticated users (PROFILE SECTION) -->

<security:authorize access="isAuthenticated()">
	<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
			<a href="#" class="dropdown-toggle"	data-toggle="dropdown"> <spring:message	code="master.profile" /> <span class="caret"></span></a>
				<security:authorize access="hasRole('Administrator')">
				<ul class="dropdown-menu">
					<li><a href="folder/administrator/list.do"><spring:message code="master.folder" /></a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('User')">
				<ul class="dropdown-menu">
					<li><a href="profile/user/show.do"><spring:message code="master.profile.data" /></a></li>
					<li><a href="folder/user/list.do"><spring:message code="master.folder" /></a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('Auditor')">
				<ul class="dropdown-menu">
					<li><a href="folder/auditor/list.do"><spring:message code="master.folder" /></a></li>
				</ul>
				</security:authorize>
			</li>
		<li><a href="home/sign-out.do"> <spring:message
					code="master.sign-out" /> (<security:authentication
					property="principal.username" />)
					
		</a></li>
		<%-- <security:authorize access="hasRole('User')">
			<li>
				<security:authentication property="principal.actors" var="actors"/>

<%-- 				<jstl:forEach items="${actors}" var="actor">
				<%-- <jstl:forEach items="${actors}" var="actor">
						<jstl:if test="${actor.socialIdentity != null }">
	        				<a href="${actor.socialIdentity.homePage}" target="_blank"><img alt="brand" src="${actor.socialIdentity.picture}" width="32px" height="32px" class="img-rounded"></a>
						</jstl:if>
				</jstl:forEach> --%>
			</ul>
</security:authorize>
	