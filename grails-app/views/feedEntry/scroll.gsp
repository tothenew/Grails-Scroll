<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Loading Content While Scrolling</title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'scrollPagination.js')}"></script>
</head>

<body>
%{--<div style="margin: 10px 80px 40px 0;">
    <g:link controller="feedEntry" action="howItWorks" style="color: blue;text-decoration: none;float: right;font-size: 20px;font-weight: bold;">How It Works?</g:link>
</div>--}%

<div id="posts">
    <g:each in="${feedEntries}" var="feedEntry">
        <div class="box">
            <div class="title"><g:link controller="feedEntry" action="show" params="[id:feedEntry.id]">${feedEntry.title}</g:link></div>

            <div class="description">${feedEntry.description}</div>
        </div>
    </g:each>
</div>

<div id="templateToShow">
    <g:render template="record"/>
</div>

<ig:scroll totalRecords="${totalCount}" link="${createLink(controller:'feedEntry',action:'showMore')}"
           templateId="templateToShow" blockId="posts" loadingElementURL="${resource(dir:'images',file:'ajaxLoader.gif')}"/>
</body>
</html>