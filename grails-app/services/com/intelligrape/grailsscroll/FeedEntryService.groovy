package com.intelligrape.grailsscroll

import com.sun.syndication.feed.synd.SyndFeed
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import com.intelligrape.grailsScroll.FeedEntry
import com.sun.syndication.feed.synd.SyndEntryImpl

class FeedEntryService {

    void saveFeedEntries() {
        URL feedUrl = new URL(feedUrlString)
        SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(feedUrl))
        for (syndFeedEntry in syndFeed?.entries) {
            FeedEntry feedEntry = new FeedEntry(syndFeedEntry as SyndEntryImpl)
            feedEntry.isTopicRelated = feedEntryService.isTopicRelated(feedEntry.title, resourceCenter)
            feedEntry.description = feedEntry.description.replaceAll('<[^>]+>', '').trimLength(150)
            feedEntry.seoUrl = FeedEntry.findByLink(feedEntry.link) ? FeedEntry.findByLink(feedEntry.link).seoUrl : FeedEntry.convertToUniqueUrl(feedEntry.title)
            feedEntries.add(feedEntry)
        }
    }

}
}
