package com.intelligrape.grailsScroll

class FeedEntry {

    String title
    String description

    static constraints = {
        description(size:1..5000)
        title(size: 1..1000)
    }
}
