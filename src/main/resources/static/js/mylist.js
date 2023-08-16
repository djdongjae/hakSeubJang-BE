$('input[type=radio]').on('click', function(){
    var chkValue = $('input[type=radio]:checked').val();

    if (chkValue == '찜 강좌 목록') {
        $('#list-1').css('display', 'grid');
        $('#list-2').css('display', 'none');
    }
    else if (chkValue == '작성한 후기') {
        $('#list-1').css('display', 'none');
        $('#list-2').css('display', 'flex');
    }
})