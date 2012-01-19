<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'IG.jpeg')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.6.2.min.js')}"></script>
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>
<div id="container">
    <div id="header">
        <div id="logo">
            <a href="http://www.intelligrape.com" target="_blank"><img border="0" alt="Intelligrape"
                                                                       title="Groovy and Grails Offshore and Outsourced Development Company in India"
                                                                       src="${resource(dir: 'images', file: 'logo__v254.png')}"></a>
        </div>

        <div id="right-header">
            <div id="follow-us">
                <ul>
                    <li>
                        <a target="_blank" href="http://www.intelligrape.com/blog/feed">
                            <img alt="RSS Feed" title="RSS" src="${resource(dir: 'images', file: 'rss.png')}">
                        </a>
                    </li>
                    <li>
                        <a target="_blank" href="http://www.linkedin.com/companies/246617/IntelliGrape%20Software">
                            <img alt="linkedin" title="Follow us on LinkedIn" src="${resource(dir: 'images', file: 'linkedin.png')}">
                        </a>
                    </li>
                    <li>
                        <a target="_blank" href="http://www.facebook.com/#!/pages/Delhi/IntelliGrape-Software/66949766692?ref=ts">
                            <img alt="Facebook" title="Follow Us on Facebook" src="${resource(dir: 'images', file: 'facebook.png')}">
                        </a>
                    </li>
                    <li>
                        <a target="_blank" href="http://twitter.com/IntelliGrape">
                            <img alt="twitter" title="Follow us on Twitter" src="${resource(dir: 'images', file: 'twitter.png')}">
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div id="wrapper">
        <div id="blockPanel">
            <g:layoutBody/>
        </div>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<g:javascript library="application"/>
<r:layoutResources/>
</body>
</html>