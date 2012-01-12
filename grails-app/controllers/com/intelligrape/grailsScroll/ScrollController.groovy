package com.intelligrape.grailsScroll

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder

class ScrollController {

    def index = {
        /*vmc update grailsScroll from target folder after grails war*/
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

    def howItWorks = {
        render(view: "howItWorks")
    }

    def downloadFile = {
        String path = ApplicationHolder.application.parentContext.servletContext.getRealPath("js/scrollPagination.js")
        byte[] pdfBytes = new File(path).bytes
        response.setContentType("application/js")
        response.setHeader('Content-disposition', 'attachment;filename=scrollPagination.js')
        response.setHeader('Content-length', "${pdfBytes.length}")
        OutputStream out = new BufferedOutputStream(response.outputStream)
        try {
            out.write(pdfBytes)
        } finally {
            out.close()
            return false
        }
    }
}
