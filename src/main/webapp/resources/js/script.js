$(function () {
    $("#code-container").hide();
    $("#code-cb").click(function () {
        if ($(this).is(":checked")) {
            $("#code-container").show();
        } else {
            $("#code-container").hide();
        }
    });

    function addOption(index) {
        $("#options").append(
            '<div class="hor">' +
                '<input type="checkbox" id="opt' + index + '" name="correctAnswers" value="">' +
                '<input id="optVal' + index + '" class="text" name="options" type="text" maxlength="200" required>' +
            '</div>'
        );
    }

    function addOptions() {
        $("#options").empty();
        for (var i = 0; i < $("#num-select").val(); i++) {
            addOption(i);
            $("#optVal" + i).on('input', function () {
                $("#opt" + $(this).parent().index()).val($(this).val());
            });
        }
    }

    addOptions();

    $("#num-select").on('change', function () {
        addOptions();
    })
});