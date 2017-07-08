$(function() {
    
    $('#name-search-link').click(function(e) {
        $(".name-form").delay(100).fadeIn(100);
        $(".map-form").fadeOut(100);
        $('#map-search-link').removeClass('active');
        $('#type').val('place');
        $(this).addClass('active');
        e.preventDefault();
    });
    
    $('#map-search-link').click(function(e) {
        $(".map-form").delay(100).fadeIn(100);
        $(".name-form").fadeOut(100);
        $('#name-search-link').removeClass('active');
        $('#type').val('map');
        $(this).addClass('active');
        e.preventDefault();
    });
    
});
