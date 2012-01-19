package com.intelligrape.grailsscroll

import com.sun.syndication.feed.synd.SyndFeed
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import com.intelligrape.grailsScroll.FeedEntry
import com.sun.syndication.feed.synd.SyndEntryImpl
import com.intelligrape.grailsScroll.Feed

class FeedEntryService {
    static transactional = false

    void saveFeedEntries() {
        try {
            Feed.list().each {feed ->
                URL feedUrl = new URL(feed.url)
                SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(feedUrl))
                for (syndFeedEntry in syndFeed?.entries) {
                    if (!FeedEntry.countByLink(syndFeedEntry.link)) {
                        FeedEntry feedEntry = new FeedEntry(syndFeedEntry as SyndEntryImpl)
                        feedEntry.seoURL = FeedEntry.countByLink(feedEntry.link) ? FeedEntry.findByLink(feedEntry.link).seoURL : FeedEntry.convertToUniqueUrl(feedEntry.title)
                        feedEntry.feed = feed
                        if (feedEntry.validate()) {
                            feedEntry.save(flush: true, failOnError: true)
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}
