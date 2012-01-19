<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <meta name="keywords" content="lazy loading, loading content while scrolling, pagination">
    <meta name="description" content="lazy loading,Loading content while scrolling, pagination">
    <title>Loading Content While Scrolling</title>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'scrollPagination.js')}"></script>
</head>

<body>

<div id="posts">
    <g:each in="${feedEntries}" var="feedEntry" status="i">
        <div class="box">
            <div class="heading"><a href="${feedEntry.link}">${feedEntry.title}</a></div>

            <div class="description">${feedEntry.description} ...</div>
        </div>
    </g:each>
</div>

<div id="templateToShow">
    <g:render template="record"/>
</div>

<ig:scroll totalRecords="${totalCount}" link="${createLink(controller:'scroll',action:'showMore')}"
           templateId="templateToShow" blockId="posts" loadingElementURL="${resource(dir:'images',file:'ajaxLoader.gif')}"/>
</body>
</html>