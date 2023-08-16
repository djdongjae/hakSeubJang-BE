$('input[type=radio]').on('click', function(){
    var chkValue = $('input[type=radio]:checked').val();

    if (chkValue == '강좌소개') {
        $('#sec_introduce').css('display', 'block');
        $('#sec_review').css('display', 'none');
    }
    else if (chkValue == '후기모음') {
        $('#sec_introduce').css('display', 'none');
        $('#sec_review').css('display', 'block');
    }
})