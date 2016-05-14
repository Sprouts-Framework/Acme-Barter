<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@ include file="../template/libraries.jsp" %>


<sprouts:button code="create.button" url="message/${actor}/create.do"/>
<br />

<sprouts:data-table i18n="datatables.language">
		<sprouts:action-button code="move.button" url="message/${actor}/{0}/update.do" />
		<sprouts:action-button code="delete.button" url="message/${actor}/{0}/delete.do" />
		<sprouts:action-button code="details.button" url="message/${actor}/{0}/show.do" />

	<sprouts:data-column code="message.sender" path="sender.userAccount.username" sortable="true" width="10%"/>
	<sprouts:data-column code="message.recipient" path="recipient.userAccount.username" sortable="true" width="10%"/>
	<sprouts:data-column code="message.priority" path="priority" sortable="true" width="10%"/>
	<sprouts:data-column code="message.moment" path="moment" sortable="true" width="20%"/>
	<sprouts:data-column code="message.subject" path="subject" sortable="true" width="50%"/>
	
</sprouts:data-table>
<br/>

		<sprouts:cancel-button url="folder/${actor}/list.do" code="return.button"/>
	
