package com.intelligrape.grailsScroll

import grails.converters.JSON

class FeedEntryController {

    def index = {
        params.max = params.max ?: 10
        params.offset = params.offset ?: 0
        render(view: "scroll", model: [feedEntries: FeedEntry.list(max: params.max, offset: params.offset), totalCount: FeedEntry.count()])
    }

    def showMore = {
        render FeedEntry.list(max: params.max, offset: params.offset) as JSON
    }

    def show = {
        FeedEntry feedEntry = FeedEntry.get(params.id)
        render(view: "show", model: [feedEntry: feedEntry])
    }

}
