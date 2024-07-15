$(document).ready(function() {
    $(window).on('scroll', function() {
        // Check if footer is in the viewport
        var footerTop = $('#footer').offset().top;
        var windowHeight = $(window).height();
        var scrollTop = $(window).scrollTop();
        var triggerPoint = scrollTop + windowHeight - 100; // Adjust this offset as needed

        if (footerTop < triggerPoint) {
            $('#footer').addClass('animate__slideInLeft');
            // Remove the scroll event listener once animation is triggered
            $(window).off('scroll');
        }
    });
}); 