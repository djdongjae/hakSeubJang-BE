// 모든 wrap_review 요소들을 가져옵니다
const reviewElements = document.querySelectorAll(".wrap_review");

// 각 요소에 대해 이벤트 리스너를 등록합니다
reviewElements.forEach(reviewElement => {
    const titleElement = reviewElement.querySelector(".review_title");
    const contentElement = reviewElement.querySelector(".review_content");

    reviewElement.addEventListener('click', () => {
        toggleReview(titleElement, contentElement);
    });
});

// 토글 함수
function toggleReview(titleElement, contentElement) {
    if (titleElement.classList.contains("review_title")) {
        titleElement.classList.remove("review_title");
        contentElement.classList.remove("review_content");
        titleElement.classList.add("review_title_tg");
        contentElement.classList.add("review_content_tg");
    } else {
        titleElement.classList.remove("review_title_tg");
        contentElement.classList.remove("review_content_tg");
        titleElement.classList.add("review_title");
        contentElement.classList.add("review_content");
    }
}