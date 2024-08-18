$(document).ready(function () {
    // 슬릭
    $(".slick-slider").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        infinite: false,
        centerMode: true,
        variableWidth: true,
        focusOnSelect: true,
    });

    $(".slick-slider").on(
        "afterChange",
        function (event, slick, currentSlide) {
            var currentItem = $(slick.$slides[currentSlide]);
            var title = currentItem.data("title");
            var main = currentItem.data("main");
            var description = currentItem.data("description");

            $("#text-title").text(title);
            $("#text-main").text(main);
            // $("#text-description").html(description.replace(/\n/g, "<br>"));
        }
    );

    // Initial text update
    var initialItem = $(".slick-slider .slick-current");
    $("#text-title").text(initialItem.data("title"));
    $("#text-main").text(initialItem.data("main"));
    /* $("#text-description").html(
        initialItem.data("description").replace(/\n/g, "<br>")
    ); */

    // 공지사항 텍스트를 동적으로 추가
    const announcements = [
        '[공지] 📢 여기를 주목해주세요...111',
        '[공지] 📢 여기를 주목해주세요...222'
    ];

    // 공지사항 컨테이너에 텍스트 추가
    announcements.forEach((text, index) => {
        $('#announcement_container').append(
            `<div id="announcement_item_txt${index + 1}" class="${index === 0 ? 'active' : ''}">${text}</div>`
        );
    });

    let currentIndex = 0;

    setInterval(function () {
        const currentElement = $(`#announcement_item_txt${currentIndex + 1}`);
        currentElement.removeClass('active').addClass('exiting');

        // 다음 공지사항 텍스트 인덱스 계산
        currentIndex = (currentIndex + 1) % announcements.length;
        const nextElement = $(`#announcement_item_txt${currentIndex + 1}`);

        // 다음 공지사항 텍스트에 active 클래스 추가
        nextElement.addClass('active');

        // 이전 공지사항 텍스트의 exiting 클래스 제거 (애니메이션 완료 후)
        setTimeout(() => {
            currentElement.removeClass('exiting');
        }, 1000); // 애니메이션 시간 (1초)과 일치
    }, 5000); // 5000 milliseconds = 5 seconds

    // 전체메뉴 드롭다운
    $('#allMenuToggle').on('click', function (event) {
        event.preventDefault();
        $('#dropdownMenu').slideToggle();
    });
});
