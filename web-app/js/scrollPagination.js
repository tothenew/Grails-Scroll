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
        methods.addAndHideSpinner();
        methods.cloneTemplate();
        return methods.doPolling();
    };

    var _checkLevel = function() {
        if (!methods.reachedAtBottom()) {
            return methods.doPolling();
        } else {
            methods.addSpinnerTextAndShowSpinner();
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
        addAndHideSpinner:function() {
            $("<h1 id='spinnerImage' style='position: absolute;bottom: 0;right: 50%' align='center'><img src='" + settings.loadingElementURL + "'alt='spinner'/><span id='totalRecordsWithOffset' style='width: 80px;text-align: center;position: absolute;bottom: 25px;left: 10px;font-size: 16px;font-weight: bold;color: #777777'></span></h1>").insertAfter("#" + settings.blockId);
            $('#spinnerImage').hide();
        },
        fillDetails:function(jsonObject) {
            var returnedTemp = template;
            for (var key in jsonObject) {
                var regex = new RegExp("#{" + key + "}", "g");
                var regex2 = new RegExp("#%7B" + key + "%7D", "g");
                returnedTemp = new String(returnedTemp).replace(regex, jsonObject[key]).replace(regex2, jsonObject[key])
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
        },
        addSpinnerTextAndShowSpinner:function() {
            $("#totalRecordsWithOffset").text(settings.offset + settings.max + " of " + settings.totalCount);
            $('#spinnerImage').show();
        }
    };
})(jQuery);