<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<liferay-theme:defineObjects/>
<portlet:defineObjects/>
<div class="container-fluid container-fluid-max-xl sidenav-content">
<ol class="breadcrumb ">
    <li aria-current="page" class="active breadcrumb-item">
        <span class="breadcrumb-text-truncate">Control Panel Portlet</span>
    </li>
</ol>
<div class="container-fluid container-fluid-max-xl sidenav-content">
    <div class="table-responsive">
        <table class="show-quick-actions-on-hover table table-autofit table-head-bordered table-heading-nowrap table-hover table-list table-striped">
            <thead>
            <tr>
                <th class="lfr-name-column table-cell-expand table-cell-minw-200 table-title"
                    id="<portlet:namespace/>_col-name"> Name
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr aria-label="" class="   " data-qa-id="row" data-selectable="false" data-actions="" tabindex="">
                    <td class="table-cell-expand-smaller table-cell-ws-nowrap lfr-children-column" colspan="1"><c:out
                            value="${item}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>