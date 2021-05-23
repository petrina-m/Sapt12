var gameOn = false;

$("#word").on('keyup', function (e) {
    if (e.key === 'Enter' || e.keyCode === 13) {
        if (!gameOn) { return; }

        let word = $(this).val();

        $.ajax({
            url: url + "/game/gameplay",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "idLastPlayer": idClient,
                "word": word,
                "gameID": gameID
            }),
            success: function (data) {

                disableTurn();
                gameID = data.gameID;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
});

function reset(){
    $(".playerBoard p").remove();
}

$("#reset").click(function(){
    reset();
});

function enableTurn() {
    gameOn = true;
    $("#word").prop( "disabled", false);
    $("#word").attr('placeholder', "Your turn! Enter word...");
}

function disableTurn() {
    gameOn = false;
    $("#word").prop( "disabled", true);
    $("#word").val('');
    $("#word").attr('placeholder', "Wait your turn...");
}

disableTurn();