const navbarToggler = document.querySelector('.navbar-toggler');
const navbarTogglerImage = document.getElementById('navbar-toggler-image');
let isImage1Visible = true;

navbarToggler.addEventListener('click', () => {
    if (isImage1Visible) {
        navbarTogglerImage.src = '../images/website/sjt_website_010.png';
        navbarTogglerImage.width = '20';
        navbarTogglerImage.height = '20';
    } else {
        navbarTogglerImage.src = '../images/website/sjt_website_009.png';
        navbarTogglerImage.width = '25';
        navbarTogglerImage.height = '25';
    }

    isImage1Visible = !isImage1Visible;
});

document.addEventListener("DOMContentLoaded", function () {
    if (window.innerWidth >= 1200) {
        const dropdownItems = document.querySelectorAll('.nav-item.dropdown');

        dropdownItems.forEach(item => {
            item.addEventListener('mouseenter', () => {
                item.querySelector('.dropdown-menu').classList.add('show');
            });

            item.addEventListener('mouseleave', () => {
                item.querySelector('.dropdown-menu').classList.remove('show');
            });

            item.addEventListener('click', (event) => {
                event.preventDefault();
            });
        });
    }
});
