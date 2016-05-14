<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp"%>


<sprouts:data-table searcheable="false" i18n="datatables.language" >
	<sprouts:data-column code="legalText.text" path="text" />
	
	<sprouts:action-button url="legalText/administrator/{0}/update.do" code="update.button"/>
	<sprouts:action-button url="legalText/administrator/{0}/delete.do" code="delete.button"/>
</sprouts:data-table>
<br/>
<br/>

<sprouts:button url="legalText/administrator/create.do" code="create.button"/>