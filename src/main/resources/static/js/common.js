function alertMessage(type, message, html) {
    if (type != 'primary' && type != 'success' && type != 'warning') {
        type = 'danger';
    }
    $("#alert-message").removeClass();
    $("#alert-message").addClass(`alert alert-fixed alert-${type}`);
    $("#alert-text").text(message);
    if(!!html && html != null){
        $("#alert-text").html(html);
    }
    $("#alert-message").fadeTo(5000, 500).slideUp(500, function() {
        $("#alert-message").slideUp(500);
    });
}