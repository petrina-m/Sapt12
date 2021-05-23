const url='http://localhost:8080';
let stompClient;
let gameID;
let idClient;
let playerPos = 2;
let gameStarted = false;

function connectToSocket(gameId){
    console.log("connecting to a game");
    var socket = new SockJS(url+"/gameplay");

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function displayResponse(data)
{
    if (!gameStarted) {
        gameStarted = true;
        alert("Game started!");
        if (playerPos === 1) {
            enableTurn();
            names = [ data.player1.name, data.player2.name ];
            $('.playerBoard h3').each(function(index) {
                $(this).text(names[index]);
            });
        }
    } else {
        if (`winner` in data) {
            if (data.winner.id === idClient) {
                alert("You won!")
            } else {
                alert("You lost!")
            }

            disableTurn();
            return;
        }
    }

    if (data.idLastPlayer != null) {
        var messagePlayer = playerPos;
        if (data.idLastPlayer !== idClient) {
            messagePlayer = 3 - messagePlayer;
            enableTurn();
        } else {
            disableTurn();
        }

        if (data.word != null) {
            $("#PLAYER" + messagePlayer + " p").remove();
            $("#PLAYER" + messagePlayer).append("<p>" + data.word + "</p>");
        }
    }
}

function createGame(){
    //get the user login after I click on the button
    let login=document.getElementById("login").value;

    if(login==null || login==='')
    {
        alert("Please enter login");
    }
    else{
        //make a request to the server
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "name": login
            }),
            success: function (data) {
                gameID = data.gameID;
                idClient=data.player1.id;
                reset();

                connectToSocket(gameID);
                alert("You created a game. Game id is: " + data.gameID);
                names = [ data.player1.name, "Player2" ];
                $('.playerBoard h3').each(function(index) {
                    $(this).text(names[index]);
                });
                playerPos = 1;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToRandom(){
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "name": login
            }),
            success: function (data) {
                gameID = data.gameID;
                // displayResponse(data);
                idClient=data.player2.id;
                reset();

                connectToSocket(gameID);
                names = [ data.player1.name, data.player2.name ];
                $('.playerBoard h3').each(function(index) {
                    $(this).text(names[index]);
                });
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToGameWithId(){
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        let inputGameID = document.getElementById("game_id").value;

        if (inputGameID == null || inputGameID === '') {
            alert("Please enter game id");
        }
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "name": login,
                "gameID": inputGameID
            }),
            success: function (data) {
                gameID = data.gameID;
                idClient=data.player2.id;
                reset();
                connectToSocket(gameID);
                alert("Congrats you're playing with: " + data.player1.login);

                names = [ data.player1.name, data.player2.name ];
                $('.playerBoard h3').each(function(index) {
                    $(this).text(names[index]);
                });
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}