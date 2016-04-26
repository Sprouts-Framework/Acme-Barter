<%--
 * data-table.tag
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

<%@ attribute name="data" required="false" type="java.util.Collection"%>
<%@ attribute name="tableId" required="false"%>
<%@ attribute name="idProperty" required="false"%>
<%@ attribute name="exportable" required="false"
	type="java.lang.Boolean"%>
<%@ attribute name="i18n" required="false"%>
<%@ attribute name="source" required="false"%>

<%@ variable name-given="cursor"%>

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
	
	request.setAttribute("__data_table_columns", dataTableColumns);
 %>

<jstl:if test="${tableId == null}">
	<jstl:set var="randomId"><%= java.util.UUID.randomUUID().toString() %></jstl:set>
	<jstl:set var="tableId" value="${randomId}" />
</jstl:if>

<jstl:if test="${idProperty == null}">
	<jstl:set var="idProperty" value="id" />
</jstl:if>

<jstl:if test="${exportable == null}">
	<jstl:set var="exportable" value="true" />
</jstl:if>


<table id="${tableId}" class="table table-striped table-bordered" cellspacing="0" width="100%">
	<jsp:doBody var="body" />

	<thead>
		<tr>
			<jstl:set var="aoColumns" value="'aoColumns': [" />
			<jstl:set var="actionButtons" value="" />
			<jstl:forEach var="column" items="${__data_table_columns}">
				<jstl:if
					test="${column.type == 'data-column' || column.type == 'custom-column' || column.type == 'image-column'}">
					<th style="width: ${column.width}"><jstl:if
							test="${column.code != null}">
							<spring:message code="${column.code}" />
						</jstl:if> <jstl:set var="aoColumns"
							value="${aoColumns} {'bSortable': ${column.sortable}}," /></th>
				</jstl:if>
				<jstl:if test="${column.type == 'action-button'}">
					<spring:message var="message" code="${column.code}" />
					<jstl:set var="button"
						value="
 						{  
 							sExtends: 'text',  
 							sButtonText: '${message}',  
 						  	fnClick: function(b, c, f) {
 						  		var url = '${column.url}'; 
 						  		var row = $('#${tableId} tr.row_selected');
 								if (row.length == 1 || row.length == 0 && url.indexOf('{0}') == -1) { 
 									var id = row.attr('data-item-id'); 
 									var url = url.replace('{0}', id); 
 									redirect(url); 
 								} 
 						  	} 
 						} " />
					<jstl:set var="actionButtons" value="${actionButtons}${button}," />
				</jstl:if>
			</jstl:forEach>
			<jstl:set var="aoColumns" value="${aoColumns} ]" />
		</tr>
	</thead>

	<tbody>
		<jstl:forEach var="item" items="${data}">
			<tr data-item-id="${item[idProperty]}">
				<jstl:forEach var="column" items="${__data_table_columns}">
					<jstl:if test="${column.type == 'custom-column'}">
						<td><jstl:set var="definition" value="${column.definition}" />
							<%
								JspFragment definition;
	
								definition = ((JspFragment)jspContext.getAttribute("definition"));
								setTemplate(definition);
							 %> <jstl:set var="cursor" value="${item}" /> <jsp:invoke
								var="value" fragment="template" /> ${value} ${column.body}</td>
					</jstl:if>
					<jstl:if test="${column.type == 'data-column'}">
						<td><jstl:set var="path" value="${column.path}" /> <jstl:set
								var="format" value="${column.format}" /> <%
								Object item;
								String path;
								Object value;
								
								item = jspContext.getAttribute("item");
								path = (String)jspContext.getAttribute("path");
								value = PropertyUtils.getNestedProperty(item, path);
								jspContext.setAttribute("value", value);
							%><acme:format-message format="${format}" value="${value}"/></td>
					</jstl:if>
					<jstl:if test="${column.type == 'image-column'}">
						<td><jstl:set var="path" value="${column.path}" /> <jstl:set
								var="format" value="${column.format}" /> <%
								Object item;
								String path;
								Object value;
								
								item = jspContext.getAttribute("item");
								path = (String)jspContext.getAttribute("path");
								value = PropertyUtils.getNestedProperty(item, path);
								jspContext.setAttribute("value", new String ("<img src='"+value+"' width='70px' height='70px'/>"));
							%> <img src="${value}" width="70px" height="70px"/></td>
					</jstl:if>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
	</tbody>
</table>

<jstl:if test="${i18n != null}">
	<spring:message var="i18nFileUri" code="${i18n}" />
	<jstl:set var="oLanguage"
		value="'oLanguage': { 'sUrl': '${i18nFileUri}' }," />
</jstl:if>

<jstl:if test="${exportable}">
	<spring:message var="i18nFileUri" code="${i18n}" />
<%-- 	<jstl:set var="exportButtons" value="'xls', 'pdf', " /> --%>
</jstl:if>

<jstl:choose>
	<jstl:when test="${source == null}">
		<jstl:set var="source"
			value="${fn:replace(requestScope['javax.servlet.forward.request_uri'], 'list.do', 'list/data.do')}" />
	</jstl:when>
	<jstl:otherwise>
		<jstl:set var="source"
			value="${pageContext.request.contextPath}/${source}" />
	</jstl:otherwise>
</jstl:choose>



<script type="text/javascript">
//<![CDATA[
    var dataTable;
    
	$(document).ready(function() {
			
		var currentTable = $('#${tableId}');
		var tableIndex = $('table').index(currentTable);
		
		dataTable = $('#${tableId}').dataTable({
			sPaginationType : "full_numbers",				
			bStateSave: true,		
			iDisplayLength: 5, 
			aLengthMenu: [5, 10, 25, 50],
			sDom: 'T<"clear">lfrtip',
			
			bServerSide : true,
			sAjaxSource : '<jstl:out value="${source}" />' + '?_viewName=<jstl:out value="${_viewName}" />&_tableIndex=' + tableIndex,

			fnDrawCallback : function (){
				$("#${tableId} tbody tr").click(function(e) {			
			        if ($(this).hasClass('row_selected')) {
			            $(this).removeClass('row_selected');
			        }
			        else {
			        	$('#${tableId} tr').removeClass('row_selected');
			            $(this).addClass('row_selected');
			        }
			    });
			},
			
			fnCreatedRow: function (nRow, aData) {
				var id = aData[aData.length - 1];
		        $(nRow).attr('data-item-id', id);
		    },
			
	        oTableTools: {
	            sSwfPath: "resources/tabletools.swf",
	            aButtons: [
					<jstl:out value="${exportButtons}" escapeXml="false" />
					<jstl:out value="${actionButtons}" escapeXml="false" />
	            ]
	        },
	        <jstl:out value="${oLanguage}" escapeXml="false" />
			<jstl:out value="${aoColumns}" escapeXml="false" />
			
	    });			

	});	
//]]>
</script>
