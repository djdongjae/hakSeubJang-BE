$(function() {

    $('tr.inherited-list').click(function() {
        var url = $(this).attr("href");
        location.href = url;
    })

    $("[data-confirm-delete]").click(function() {
        return confirm("삭제하시겠습니까?");
    })

})