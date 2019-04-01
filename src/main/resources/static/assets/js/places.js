jQuery(document).ready(function () {
    $(".table.datatable").DataTable({
        "bPaginate": false,
    });
    $('.mdb-select').selectpicker();

    // Get the modal
    var modal = document.getElementById('myModal');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");

    $('.myImg').click(function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    });

    $('.close').click(function () {
        modal.style.display = "none";          
    });

});
$("#places-form").validate({});