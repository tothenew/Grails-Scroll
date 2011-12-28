package com.intelligrape.grailsScroll

class ScrollPaginationTagLib {
    static namespace = "ig"
    private static final Integer MAX = 10
    private static final Integer OFFSET = 0
    private static final Integer TOTALRECORDS = 10
    private static final String ORDER = "asc"

    def scroll = {attrs, body ->
        Integer max = attrs['max'] as Integer ?: MAX
        Integer offset = attrs['offset'] as Integer ?: OFFSET
        String fieldToSort = attrs['sort'] ?: ""
        String order = attrs['order'] ?: ORDER
        Integer totalRecords = attrs['totalRecords'] as Integer ?: TOTALRECORDS
        String link = attrs['link'] ?: ""
        String templateId = attrs['templateId'] ?: "" //Id of element than needs to be replicated
        String loadingElementURL = attrs['loadingElementURL'] ?: ""
        String blockId = attrs['blockId'] ?: ""
        out << g.render(template: "/javascriptForGrailsScroll", model: [max: max, offset: offset, sort: fieldToSort, loadingElementURL: loadingElementURL, order: order, totalRecords: totalRecords, link: link, templateId: templateId, blockId: blockId])
    }

}
