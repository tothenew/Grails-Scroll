<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<div id="posts">
    <div class="box">
        <div class="title">${feedEntry.title}</div>

        <div class="image"><img src="${resource(dir: 'images', file: 'index.jpeg')}"/></div>

        <div class="description">${feedEntry.description}</div>
    </div>
</div>
</body>
</html>