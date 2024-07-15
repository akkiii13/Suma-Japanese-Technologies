// Function to check if an element is in the viewport
function isElementInViewport(el) {
    const rect = el.getBoundingClientRect();
    return (
        rect.top >= 0 &&
        rect.left >= 0 &&
        rect.right <= (window.innerWidth || document.documentElement.clientWidth)
    );
}

// Add an event listener to play the video when it's in the viewport
window.addEventListener('scroll', playVideoWhenVisible);
window.addEventListener('resize', playVideoWhenVisible); // Handle window resize
window.addEventListener('load', playVideoWhenVisible); // Handle initial load

// Optional: Pause videos when they are out of viewport for better performance
window.addEventListener('blur', function() {
    const videos = document.querySelectorAll('.background-video');
    videos.forEach(function(video) {
        video.pause();
    });
});

window.addEventListener('focus', playVideoWhenVisible);
