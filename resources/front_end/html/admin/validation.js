function validateform(){
//var email=document.myform.name.value;
var name = document.myform.name.value;
var namePattern = new RegExp("([A-Z][a-z]+)\\s([A-Z][a-z]+)");

var email = document.myform.email.value;
var emailPattern = new RegExp("([a-z]+\\.?[a-z]+\\@[a-z]+\\.[a-z]+)");

if (emailPattern.test(email)) {
	    console.log("Valid email");
    if(namePattern.test(name)) {
        console.log("Valid name")
        return true;
    }
}

return false;


}




//function allowSubmitting() {
//    var nonEmpty = true;  // assume that fields are not empty
//    var textfields = document.querySelectorAll("input, textarea");
//
//    for (var i = 0; i < textfields.length; i++) {
//        if (textfields[i].value === "") {
//            nonEmpty = false;
//        }
//    }
//
//    document.getElementById("form-submit-button").disabled = !nonEmpty;
//}
//
//function validateInputs() {
//    document.getElementById("form-alerts").innerHTML = '';
//
//    var successMsg = document.createElement("section");
//    successMsg.className = "success-msg";
//    successMsg.innerText = "Form was successfully submitted";
//
//    if (validateName() && validateEmail()) {
//        document.getElementById("form-alerts").appendChild(successMsg);
//    }
//}
//
//function validateName() {
//    var fullname = document.getElementById("fullname");
//    var fullnamePattern = new RegExp("([A-Z][a-z]+)\\s([A-Z][a-z]+)");
//    var isValid = fullnamePattern.test(fullname.value);
//
//    var errorMsg = document.createElement("section");
//    errorMsg.className = "alert-msg";
//    errorMsg.innerText = "Name is wrong";
//
//    if (!isValid) {
//        document.getElementById("form-alerts").appendChild(errorMsg);
//    }
//
//    return isValid;
//}
//
//function validateEmail() {
//    var email = document.getElementById("email");
//    var emailPattern = new RegExp("([a-z]+\\.?[a-z]+\\@[a-z]+\\.[a-z]+)");
//    var isValid = emailPattern.test(email.value);
//
//    var errorMsg = document.createElement("section");
//    errorMsg.className = "alert-msg";
//    errorMsg.innerText = "Email is wrong";
//
//    if (!isValid) {
//        document.getElementById("form-alerts").appendChild(errorMsg);
//    }
//
//    return isValid;
//}
//
//
//function validate(){
//  var phoneNumber = document.getElementById('phone-number').value;
//  var postalCode = document.getElementById('postal-code').value;
//  var phoneRGEX = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
//  var postalRGEX = /^[A-Z]{1,2}[0-9]{1,2} ?[0-9][A-Z]{2}$/i;
//  var phoneResult = phoneRGEX.test(phoneNumber);
//  var postalResult = postalRGEX.test(postalCode);
//if(phoneResult == false)
//{
//alert('Please enter a valid phone number');
//return false;
//}
//
//if(postalResult == false)
//{
//alert('Please enter a valid postal number');
//return false;
//}
//
//  return true;
//}
//
