var h = document.getElementById("h");
var u = document.getElementById("u");
var n = document.getElementById("n");
var d1 = document.getElementById("d1");
var r = document.getElementById("r");
var e = document.getElementById("e");
var d2 = document.getElementById("d2");

function colorChange(letter1) {
 	switch(letter1.className) {
  		case "blue noselect":
  			letter1.className = "red noselect";
  			break;
  		case "red noselect":
  			letter1.className = "yellow noselect";
  			break;
  		case "yellow noselect":
  			letter1.className = "green noselect";
  			break;
  		case "green noselect":
  			letter1.className = "blue noselect";
  			break;
  	}
}

function getPair(number) {
	switch(number) {
	case 0: return h;
	case 1: return u;
	case 2: return n;
	case 3: return d1;
	case 4: return r;
	case 5: return e;
	case 6: return d2;
	}
}

var pair = [];

for (index = 0; index < 7; index++) { /* Randomly assign letter connections. */
	pair[index] = Math.floor(Math.random() * 6);
}

for (index = 0; index < 7; index++) { /* Prevent letters from being connecting to themselves. */
	if (pair[index] == index) pair[index]++;
}

h.addEventListener("click", function() {
 	colorChange(h);
 	colorChange(getPair(pair[0]));
})

u.addEventListener("click", function() {
 	colorChange(u);
 	colorChange(getPair(pair[1]));
})

n.addEventListener("click", function() {
 	colorChange(n);
 	colorChange(getPair(pair[2]));
})

d1.addEventListener("click", function() {
 	colorChange(d1);
 	colorChange(getPair(pair[3]));
})

r.addEventListener("click", function() {
 	colorChange(r);
 	colorChange(getPair(pair[4]));
})

e.addEventListener("click", function() {
 	colorChange(e);
 	colorChange(getPair(pair[5]));
})

d2.addEventListener("click", function() {
 	colorChange(d2);
 	colorChange(getPair(pair[6]));
})
