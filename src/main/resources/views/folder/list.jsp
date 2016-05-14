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


<sprouts:button code="createMessage.button" url="message/${actor}/create.do"/>
<sprouts:button code="folder.create" url="folder/${actor}/create.do"/>
<br />
<br />

<sprouts:data-table i18n="datatables.language">
		<sprouts:action-button code="update.button" url="folder/${actor}/{0}/update.do" />
		<sprouts:action-button code="delete.button" url="folder/${actor}/{0}/delete.do" />
		<sprouts:action-button code="details.button" url="message/${actor}/{0}/list.do" />
	
	<sprouts:data-column code="folder.name" path="name" sortable="true"/>

</sprouts:data-table>

