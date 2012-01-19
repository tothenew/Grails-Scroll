package com.intelligrape.grailsscroll

class AddArticlesJob {
    def feedEntryService
    def concurrent = false
    def cronExpression = "0 0/1 * * * ?" //everyhour

    def execute() {
        println "Executing job at ${new Date()}"
        feedEntryService.saveFeedEntries()
    }
}
