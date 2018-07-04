function calculetePrice() {
    var quantityStudents = document.getElementById('qS').value;
    var price = 5;
    var buyButton = document.getElementById('buy');

    console.log(quantityStudents);
    if (price % quantityStudents != 0 ) {
        buyButton.setAttribute("disabled", "true")
        return price;
    } else
        buyButton.removeAttribute("disabled")
        return (price / quantityStudents);
}

function refresh() {
    document.getElementById('topay').innerHTML = calculetePrice();
}