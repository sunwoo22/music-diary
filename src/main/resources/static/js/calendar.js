const date = new Date().getDate();
const dates = []

dates.unshift("</g>")
for (var i=0; i<12; i++) {
    x = 200 - 15*i;
    for (var j=0; j<7; j++) {
        thisDate = new Date(new Date().setDate(date-j-7*i));
        y = dayLoc(thisDate.getDay());
        if (y == 0) {
            dates.unshift("<rect y='"+y+"' data-date='"+thisDate.toLocaleDateString()+"'></rect>");
            dates.unshift("<g transform='translate("+String(x)+",0)'>");
            dates.unshift("</g>")
        }
        else if (y == 6) {
            dates.unshift("<rect y='"+y+"' data-date='"+thisDate.toLocaleDateString()+"'></rect>");
        }
        else {
            dates.unshift("<rect y='"+y+"' data-date='"+thisDate.toLocaleDateString()+"'></rect>");
        }
    }
}
dates.unshift("<g transform='translate("+String(x)+",0)'>");

function dayLoc(day) {
    if (day == 0) { return 0; }
    else if (day == 1) { return 15; }
    else if (day == 2) { return 30; }
    else if (day == 3) { return 45; }
    else if (day == 4) { return 60; }
    else if (day == 5) { return 75; }
    else if (day == 6) { return 90; }
}

document.querySelector('.dates').innerHTML = dates.join('');