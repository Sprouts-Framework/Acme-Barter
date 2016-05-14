<%--
 * follow.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp"%>

<spring:message code="user.follow.user-to-follow" var="userToFollowMsg"/>
<spring:message code="user.follow.user-to-unfollow" var="userToUnfollowMsg"/>
<spring:message code="user.username" var="usernameMsg" />

<div class="row fieldset-btm-margin">
	<div class="col-md-12">
	<jstl:if test="${isFollowing == false }">
		<sprouts:display-column title="${userToFollowMsg }" data="${toFollow.name } ${toFollow.surname }"/>
	</jstl:if>
	<jstl:if test="${isFollowing == true }">
		<sprouts:display-column title="${userToUnfollowMsg }" data="${toFollow.name } ${toFollow.surname }"/>
	</jstl:if>
		<sprouts:display-column title="${usernameMsg }" data="${toFollow.userAccount.username}"/>
	</div>
</div>

<form:form action="user/${toFollow.id}/follow.do">

	<div class="row">
		<div class="col-md-12">
		<jstl:choose>
			<jstl:when test="${isFollowing==false }">
				<sprouts:submit-or-cancel
					submitCode="user.follow.button"
					backUrl="home/user/list.do" />
			</jstl:when>
			<jstl:otherwise>
				<sprouts:submit-or-cancel
					submitCode="user.unfollow.button"
					backUrl="home/user/list.do" />
			</jstl:otherwise>
		</jstl:choose>
		</div>
	</div>

</form:form>