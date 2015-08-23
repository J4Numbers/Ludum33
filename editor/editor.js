var enemy_count = 0;

function cycle_tile(x, y) {
    var cell = document.getElementById(x+"-"+y);

    if (cell.style.backgroundColor ===  "rgb(0, 0, 255)") {
        cell.style.backgroundColor = "rgb(255, 0, 0)";
    } else {
        cell.style.backgroundColor = "rgb(0, 0, 255)";
    }
}

function addEnemy() {
    document.getElementById("enemy").innerHTML += "<div class=\"enima\" id=\"enemy_"+enemy_count+"\"> \
        <select class=\"e_type\"><option value=\"0\">Tourist</option> \
        <option value=\"1\">Soldier</option> \
        <option value=\"2\">exterminator</option></select> \
        <input tyle=\"text\" class=\"x_coord\" placeholder=\"x-co-ord\" /> \
        <input type=\"text\" class=\"y_coord\" placeholder=\"y-co-ord\" /> \
    </div>";
    ++enemy_count;
}

//function updateTable() {
//    var input = JSON.parse(document.getElementById("output").value);
//    console.log(input);
//    input.foreach(function(e, i, a){});
//}

function download() {
    var table = document.getElementById("level");
    var arr = {
        "textures": {"0": "sprite_1.png", "1": "sprite_2.png"},
        "map": {},
        "enemies": {},
        "char": {
            "x":document.getElementById("char_x").value,
            "y":document.getElementById("char_y").value
        }
    };

    for (var i = 1, row; row = table.rows[i]; i++) {
        arr["map"][i-1] = {};
        //iterate through rows
        //rows would be accessed using the "row" variable assigned in the for loop
        for (var j = 1, col; col = row.cells[j]; j++) {
            //iterate through columns
            //columns would be accessed using the "col" variable assigned in the for loop
            arr["map"][i-1][j-1] = (col.style.backgroundColor === "rgb(0, 0, 255)") ? 1 : 0;
        }  
    }

    var enemies = document.getElementsByClassName("enima");
    var i;
    for (i=0; i<enemies.length; i++) {
        arr["enemies"][i] = {
            "x":enemies[i].getElementsByClassName("x_coord")[0].value, 
            "y":enemies[i].getElementsByClassName("y_coord")[0].value, 
            "type":enemies[i].getElementsByClassName("e_type")[0].value
        };
    }

    //var blob = new Blob([JSON.stringify(arr)], {type: "text/plain;charset=utf-8"});
    //saveAs(blob, document.getElementById("name").value+".json");
    document.getElementById("output").value = JSON.stringify(arr);
}