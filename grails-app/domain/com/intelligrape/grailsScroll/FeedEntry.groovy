package com.intelligrape.grailsScroll

import com.sun.syndication.feed.synd.SyndFeedImpl
import groovy.xml.StreamingMarkupBuilder
import groovy.util.slurpersupport.GPathResult
import org.ccil.cowan.tagsoup.Parser
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.sun.syndication.feed.synd.SyndEntryImpl

class FeedEntry {

    String title
    String description
    String link
    String author
    Date publishedDate
    String seoURL

    static belongsTo = [feed: Feed]

    static constraints = {
        description(size: 1..20000)
        title(blank: false, nullable: false, size: 1..1000)
        link(blank: false, nullable: false, unique: true, url: true, maxSize: 255)
        author(nullable: true, blank: true)
        publishedDate(nullable: true)
        seoURL(blank: false, nullable: false, unique: true)
    }

    FeedEntry(SyndEntryImpl syndEntry) {
        link = syndEntry.link
        author = syndEntry.author
        title = syndEntry.title
        publishedDate = syndEntry.publishedDate
        setDescription(syndEntry.contents ? syndEntry.contents[0].value : syndEntry.description?.value)
    }

    void setDescription(String description) {
        if (!description) { description = title }
        description = parseDescriptionHTML(description);
        Integer descriptionLength = description.length() > 300 ? 300 : description.length();
        this.description = description.substring(0, descriptionLength)
    }

    public static String parseDescriptionHTML(String htmlString) {
        if (!htmlString) {
            return ''
        }
        Parser tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
        XmlSlurper slurper = new XmlSlurper(tagsoupParser)
        GPathResult parsedHTML = slurper.parseText(htmlString)
        parsedHTML.depthFirst().grep { it.name() == 'script'; }.each { it.replaceNode({}); }
        parsedHTML.depthFirst().grep { it.name() == 'a'; }.each { it.@rel = 'nofollow'; }

        List<String> htmlTagAttributeListToRemove = ConfigurationHolder.config.htmlTagAttributesToRemoveForFeedEntryDescription
        htmlTagAttributeListToRemove.each {tagAttribute ->
            tagAttribute = '@' + tagAttribute
            parsedHTML.depthFirst().grep { it."${tagAttribute}" != ''; }.each { it."${tagAttribute}" = ''; }
        }

        def cleanHtmlWriter = new StreamingMarkupBuilder().bind({
            mkp.declareNamespace('': 'http://www.w3.org/1999/xhtml');
            mkp.yield(parsedHTML);
        });
        String parsedHTMLString = cleanHtmlWriter.toString().trim();
        String bodyStartTag = "<body>"
        String bodyEndTag = "</body>"
        Integer lastIndexOfBodyStartTag = parsedHTMLString.indexOf(bodyStartTag) + bodyStartTag.length()
        if (lastIndexOfBodyStartTag >= 0) {
            parsedHTMLString = parsedHTMLString.substring(lastIndexOfBodyStartTag)
        }
        Integer lastIndexOfBodyEndTag = parsedHTMLString.lastIndexOf(bodyEndTag)
        if (lastIndexOfBodyEndTag >= 0) {
            parsedHTMLString = parsedHTMLString.substring(0, lastIndexOfBodyEndTag)
        }
        return parsedHTMLString
    }

    public static String convertToUniqueUrl(String title) {
        if (!title?.trim()) {return null}
        String uniqueUrl = title.replaceAll(/(\W|_)+/, '-').toLowerCase()
        if (uniqueUrl[0] == '-') {
            uniqueUrl = uniqueUrl.substring(1)
        }
        if ((uniqueUrl) && (uniqueUrl[-1] == '-')) {
            uniqueUrl = uniqueUrl.substring(0, uniqueUrl.length() - 1)
        }
        if (uniqueUrl) {
            Integer feedsWithSimilarUrlCount = FeedEntry.countBySeoURLLike("${uniqueUrl}%")
            while (FeedEntry.countBySeoURLLike("${uniqueUrl}-${feedsWithSimilarUrlCount}")) {
                feedsWithSimilarUrlCount++
            }
            uniqueUrl = feedsWithSimilarUrlCount ? "${uniqueUrl}-${feedsWithSimilarUrlCount}" : uniqueUrl
        } else {
            uniqueUrl = 'uuid-' + UUID.randomUUID().toString()
        }
        return uniqueUrl
    }
}
