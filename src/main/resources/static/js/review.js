document.getElementById("click_review").addEventListener('click', reviewDetail);
const tog_content = document.getElementById("p_content");
const tog_title = document.getElementById("p_title");

let clicked = 0;
function reviewDetail() {
    if(clicked) {
        tog_title.className = "review_title_tg";
        tog_content.className = "review_content_tg";
        clicked = 0; 
    } else {
        tog_title.className = "review_title";
        tog_content.className = "review_content";
        clicked = 1;
    }
}