const navbarToggler = document.querySelector('.navbar-toggler');
const navbarTogglerImage = document.getElementById('navbar-toggler-image');
let isImage1Visible = true;

navbarToggler.addEventListener('click', () => {
    if (isImage1Visible) {
        navbarTogglerImage.innerHTML = '<i class="fa-solid fa-x"></i>';
    } else {
        navbarTogglerImage.innerHTML = '<i class="fa-solid fa-bars"></i>';
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
