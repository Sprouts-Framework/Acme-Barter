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


<acme:button code="createMessage.button" url="message/${actor}/create.do"/>
<acme:button code="folder.create" url="folder/${actor}/create.do"/>
<br />
<br />

<acme:data-table i18n="datatables.language">
		<acme:action-button code="update.button" url="folder/${actor}/{0}/update.do" />
		<acme:action-button code="delete.button" url="folder/${actor}/{0}/delete.do" />
		<acme:action-button code="details.button" url="message/${actor}/{0}/list.do" />
	
	<acme:data-column code="folder.name" path="name" sortable="true"/>

</acme:data-table>

