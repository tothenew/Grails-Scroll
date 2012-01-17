package grailsscroll

class ApplicationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                println "Logs >> ${params}"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
