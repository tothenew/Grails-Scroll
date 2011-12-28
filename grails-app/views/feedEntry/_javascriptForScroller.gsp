<script type="text/javascript">
    $(document).ready(function() {
        jQuery("#${blockId}").scrollPagination({
            'max':${max},
            'offset':${offset},
            'sort':"${sort}",
            'order':"${order}",
            'totalCount': ${totalRecords},
            'url':"${link}",
            'templateId':"${templateId}",
            'blockId':"${blockId}",
            'loadingElementURL':"${loadingElementURL}"
        });
    });
</script>
