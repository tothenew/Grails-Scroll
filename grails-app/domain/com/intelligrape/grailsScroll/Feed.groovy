package com.intelligrape.grailsScroll

class Feed {

    String url
    String title
    String description

    static hasMany = [feedEntries: FeedEntry]

    static constraints = {
        url(blank: false, unique: true, url: true)
        title(blank: true, nullable: true)
        description(nullable: true, size: 0..5000)
    }
}
