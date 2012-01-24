import com.intelligrape.grailsScroll.Feed

class BootStrap {

    def init = { servletContext ->
        if (!Feed.count()) {
            new Feed(url: "http://www.intelligrape.com/blog/feed/").save(flush: true)
            new Feed(url: "http://developers.sun.com/rss/java.xml").save(flush: true)
            new Feed(url: "http://feeds.feedburner.com/JavaCodeGeeks?format=xml").save(flush: true)
            new Feed(url: "http://www.java.net/blog/37999/feed").save(flush: true)
            new Feed(url: "http://feeds.feedburner.com/JavabeatTips").save(flush: true);
            new Feed(url: "http://www.javaworld.com/features/index.xml").save(flush: true);
            new Feed(url: "http://www.ibm.com/developerworks/views/java/rss/libraryview.jsp?site_id=1&contentarea_by=Java&topic_by=&product_by=&type_by=All%20Types&search_by=&industry_by=&sort_by=Date").save(flush: true);
            new Feed(url: "http://feeds.feedburner.com/BeTheCoder?format=xml").save(flush: true);
            Feed feed
        }
    }
    def destroy = {
    }
}
