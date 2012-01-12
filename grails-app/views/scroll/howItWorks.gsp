<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <meta name="keywords" content="lazy loading, loading content while scrolling, pagination">
    <meta name="description" content="lazy loading,Loading content while scrolling, pagination, ajax load, virtual loading">
    <title>Tutorial on Loading Content While Scrolling</title>
</head>

<body>
<div id="lazyContent">

    We generally have large amount of data to show but we cannot load all at once as it may take too much time. In order to improve performance of web page, we can load data

    from server while scrolling down the web page using ajax. It would increase atleast initial loading of page and rest of the loading will take place as user will scroll

    down to web page. When we reach at the end of web page, the code recognizes that we are at the bottom of web page and requests for more data using ajax.<br/><br/>

    <h3>When to use?</h3>

    Instead of using pagination on a page we can use this technique to show more data because it does not interrupt user's flow of viewing data.

    <br/><br/>

    <p>

    <h3>Steps to use Lazy loading technique</h3>
    <b>Note : This technique requires jquery to be pre-installed.</b>

    <br/><br/>PART A : SETTING UP REQUIRED FILES

    <br/><br/> Copy scrollPagination.js file from <a href="${createLink(controller: "scroll", action: "downloadFile")}"
                                                     style="font-size: 24px;text-decoration: underline">here</a>.

    <br/><br/>PART B : USING IT

    <br/><br/>STEP 1. Add &lt;script type="text/javascript" src="scrollPagination.js"&gt;&lt;/script&gt; in your head tag.
    <br/><br/>STEP 2. Add following javascript in your html file <br/>
    <span style="margin-left: 60px;font-size: 16px;font-weight: bold">&lt;script type="text/javascript"&gt;</span><br/>
    <span style="margin-left: 90px">$(document).ready(function() {</span><br/>
    <span style="margin-left: 120px">jQuery(&lt;blockId&gt;).scrollPagination({</span><br/>
    <span style="margin-left: 140px">'max':&lt;max&gt;,</span><br/>
    <span style="margin-left: 140px">'offset':&lt;offset&gt;,</span><br/>
    <span style="margin-left: 140px">'sort':&lt;sort&gt;,</span><br/>
    <span style="margin-left: 140px">'order':&lt;order&gt;,</span><br/>
    <span style="margin-left: 140px">'totalCount': &lt;totalRecords&gt;,</span><br/>
    <span style="margin-left: 140px">'url':&lt;link&gt;,</span><br/>
    <span style="margin-left: 140px">'templateId':&lt;templateId&gt;,</span><br/>
    <span style="margin-left: 140px">'blockId':&lt;blockId&gt;,</span><br/>
    <span style="margin-left: 140px">'loadingElementURL':&lt;loadingElementURL&gt;</span><br/>
    <span style="margin-left: 120px">});</span><br/>
    <span style="margin-left: 90px">});</span> <br/>
    <span style="margin-left: 60px;font-size: 16px;font-weight: bold">&lt;/script&gt;</span>
    <br/><br/>

    <div class="bullets" style="margin-left: 70px">
        <ul>
            <li>blockId (required) : id of the block where to append new records.</li>
            <li>max : Maximum number of records to show at one ajax request.By default it's value is 10.</li>
            <li>offset (required with max) : index from which you start the listing for the first time in Lazy loading process.Normally it should be index of the first record that need to be shown after initial loaded record.By default it starts from 10.</li>
            <li>sort : Field on which you want to perform sort operation.</li>
            <li>order (asc or desc) : sorting in ascending or descending order.By default it's value is "asc"</li>
            <li>totalRecords (required) : Total number of records that user needs to show on web page.</li>
            <li>link (required): Link of URL from where user needs to load more data in form of json.</li>
            <li>templateId (required) : id of the template that needs to be shown for every record.</li>
            <li>loadingElementURL (required) : url of the loading image.</li>
        </ul>
    </div>

    <br/><br/> STEP 3. Setting up the template: We use template to render the data received in form of JSON using AJAX request.This template can be present on your page anywhere. The ID of parent element in the template should be supplied as "templateId" in the tag.

    <br/><br/>The data recieved in form of JSON would replace the placeholder specified in form of "#{&lt;key&gt;}" in template.

    <br/><br/>e.g.
If JSON is something like {title:"LOREM IPSUM",description:"Hello World"}

    <br/><br/>Then template should be like:

    <div style="margin-left: 50px">
        &lt;div id="template"&gt; <br/>
        <span style="padding-left: 40px">&lt;div id="anyID"&gt;#{title}&lt;/div&gt; // where title in "#{}" placeholder is key of JSON returned</span><br/>
        <span style="padding-left: 40px">&lt;div id="anyID2"&gt;#{description}&lt;/div&gt;//description in "#{}" placeholder is key of JSON</span><br/>
        &lt;/div&gt;
    </div>
</div>
</body>
</html>
