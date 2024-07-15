$(document).ready(function () {
  let owl = $('#home-blog-owl');
  owl.owlCarousel({

    autoPlay: true,
    autoplayTimeout: 3000,
    autoplayHoverPause: true,

    slideSpeed: 3000,

    loop: true,

    goToFirst: true,

    mouseDrag: true,

    goToFirstSpeed: 3000,

    pagination: true,
    paginationNumbers: true,
    paginationSpeed: 3000,

    animateOut: 'fadeOut',
    animateIn: 'fadeIn',

    nav: false,
    navText: [
      "<i class='fa-solid fa-angle-left fa-2xl' style='color: #7ab629;'></i>",
      "<i class='fa fa-angle-right fa-2xl' style='color: #7AB629;'></i>"
    ],

    items: 2,
    responsive: {
      0: {
        items: 1,
      },
      768: {
        items: 2,
      },
      1000: {
        items: 2,
      }
    },
  });
  $('.owl-next').click(function () {
    owl.trigger('next.owl.carousel');
  });
  $('.owl-prev').click(function () {
    owl.trigger('prev.owl.carousel');
  });
});
