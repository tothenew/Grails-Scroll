(function($) {

    var settings;
    var template;

    $.fn.scrollPagination = function(options) {
        settings = $.extend({
            'max':10,
            'offset':0,
            'sort':'fieldName',
            'order':'asc',
            'url':'/index',
            'totalCount':100,
            'templateId':'template',
            'blockId':'block',
            'loadingElementURL':'spinner'
        }, options);
        $("<h1 id='spinner' align='center'><img src='" + settings.loadingElementURL + "'alt='spinner'/></h1>").insertAfter("#" + settings.blockId);
        $('#spinner').hide();
        template = $("#" + settings.templateId).clone().html();
        $("#" + settings.templateId).remove();
        return methods.pollLevel();
    };

    var _checkLevel = function() {
        if (!_levelReached()) {
            return methods.pollLevel();
        } else {
            $('#spinner').show();
            settings.offset = settings.offset + settings.max;
            if (settings.offset < settings.totalCount) {
                $.post(settings.url, {max:settings.max,offset:settings.offset,sort:settings.sort,order:settings.order}, function(data) {
                    for (var i in data) {
                        $("#" + settings.blockId).append(fillDetails(data[i]));
                    }
                    $('#spinner').hide();
                    methods.pollLevel();
                });
            } else {
                clearTimeout();
                $('#spinner').hide();
            }

        }
    };

    function fillDetails(jsonObject) {
        var returnedTemp = template;
        for (var key in jsonObject) {
            var regex = new RegExp("#" + key, "g");
            var regex2 = new RegExp("%23" + key, "g");
            returnedTemp = new String(returnedTemp).replace(regex, jsonObject[key]).replace(regex2, jsonObject[key])
        }
        return returnedTemp;
    }

    var _levelReached = function() {
        var pageHeight = Math.max(document.body.scrollHeight ||
                document.body.offsetHeight);
        var viewportHeight = window.innerHeight ||
                document.documentElement.clientHeight ||
                document.body.clientHeight || 0;
        var scrollHeight = window.pageYOffset ||
                document.documentElement.scrollTop ||
                document.body.scrollTop || 0;
        // Trigger for scrolls within 20 pixels from page bottom
        return pageHeight - viewportHeight - scrollHeight < 30;
    };

    var methods = {
        pollLevel : function() {
            setTimeout(_checkLevel, 100);
        }
    };

})(jQuery);