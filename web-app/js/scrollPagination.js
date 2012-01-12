(function($) {
    var settings;
    var template;

    $.fn.scrollPagination = function(options) {
        settings = $.extend({
            'max':10,
            'offset':10,
            'sort':'fieldName',
            'order':'asc',
            'url':'/index',
            'totalCount':100,
            'templateId':'template',
            'blockId':'block',
            'loadingElementURL':'spinner'
        }, options);
        methods.addAndShowSpinner();
        methods.cloneTemplate();
        return methods.doPolling();
    };

    var _checkLevel = function() {
        if (!methods.reachedAtBottom()) {
            return methods.doPolling();
        } else {
            $('#spinnerImage').show();
            if (settings.offset < settings.totalCount) {
                $.post(settings.url, {max:settings.max,offset:settings.offset,sort:settings.sort,order:settings.order}, function(data) {
                    methods.makeTemplateForEveryRecord(data);
                    $('#spinnerImage').hide();
                    methods.doPolling();
                    settings.offset = settings.offset + settings.max;
                });
            } else {
                clearTimeout();
                $('#spinnerImage').hide();
            }

        }
    };

    var methods = {
        doPolling : function() {
            setTimeout(_checkLevel, 100);
        },
        cloneTemplate:function() {
            template = $("#" + settings.templateId).clone().html();
            $("#" + settings.templateId).remove();
        },
        addAndShowSpinner:function() {
            $("<h1 id='spinnerImage' align='center'><img src='" + settings.loadingElementURL + "'alt='spinner'/></h1>").insertAfter("#" + settings.blockId);
            $('#spinnerImage').hide();
        },
        fillDetails:function(jsonObject) {
            var returnedTemp = template;
            for (var key in jsonObject) {
                var regex = new RegExp("#{" + key + "}", "g");
                returnedTemp = new String(returnedTemp).replace(regex, jsonObject[key])
            }
            return returnedTemp;
        },
        reachedAtBottom : function() {
            var totalHeight = $(document).height();
            var viewPortHeight = window.innerHeight ||
                    document.documentElement.clientHeight ||
                    document.body.clientHeight || 0;
            var scrollHeight = window.pageYOffset ||
                    document.documentElement.scrollTop ||
                    document.body.scrollTop || 0;
            return totalHeight - viewPortHeight - scrollHeight < 30;
        },
        makeTemplateForEveryRecord:function(data) {
            for (var i in data) {
                $("#" + settings.blockId).append(methods.fillDetails(data[i]));
            }
        }
    };
})(jQuery);