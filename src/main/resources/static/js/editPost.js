$(document).ready(function () {
    function readURL(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInput").change(function() {
        $("#remove-image").hide();
        readURL(this);
    });

    $("#remove-image").click(function () {
        $(".post-image.original").attr("src", null);
        $("#edit-form").append("<input type='hidden' name='removeImage' value='true'>");
        $(this).hide();
    });


});



