const signupbtn = document.getElementById("signup")
const movebtn = document.querySelector(".move")
var activatemodal = document.querySelector(".modal")
var modalclose = document.querySelector(".modal-close")

signupbtn.addEventListener("click", function(){
    activatemodal.classList.add("modal-active")
});

modalclose.addEventListener("click", function(){
    activatemodal.classList.remove("modal-active")
})
// var activatemovemodal = document.querySelector(".moveoption")
// movebtn.addEventListener("click", function(){
//     activatemovemodal.classList.add("modal-active")
// });

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
// function myFunction() {
//     document.getElementById("myDropdown").classList.toggle("whr-drop-hide");
// }
//
//
// /**
//  * sends a request to the specified url from a form. this will change the window location.
//  * @param {string} path the path to send the post request to
//  * @param {object} params the paramiters to add to the url
//  * @param {string} [method=post] the method to use on the form
//  */
//
// function post(path, params, method='post') {
//
//     // The rest of this code assumes you are not using a library.
//     // It can be made less wordy if you use one.
//     const form = document.createElement('form');
//     form.method = method;
//     form.action = path;
//
//     for (const key in params) {
//         if (params.hasOwnProperty(key)) {
//             const hiddenField = document.createElement('input');
//             hiddenField.type = 'hidden';
//             hiddenField.name = key;
//             hiddenField.value = params[key];
//
//             form.appendChild(hiddenField);
//         }
//     }
//
//     document.body.appendChild(form);
//     form.submit();
// }