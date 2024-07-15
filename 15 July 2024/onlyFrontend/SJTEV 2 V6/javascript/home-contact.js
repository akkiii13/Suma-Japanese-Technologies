function sendEmail() {
    Email.send({
        Host: "smtp.elasticemail.com",
        Username: "sumajapanesebavdhan@gmail.com",
        Password: "07C0E298C6CBB12784A9745D1A2D06C3091F",
        To: 'sales@sjtev.com',
        From: "sumajapanesebavdhan@gmail.com",
        Subject: "New Contact Form",
        Body: "Full Name: " + document.getElementById("fullName").value
            + "<br> Mobile No: " + document.getElementById("mobileNumberPrefix").value
            + " " + document.getElementById("mobileNumber").value
            + "<br> Email: " + document.getElementById("email").value
            + "<br> Subject: " + document.getElementById("subject").value
            + "<br> Message: " + document.getElementById("message").value
    }).then(
        message => alert("Message sent succesfully")
    );
}
