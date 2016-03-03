/*
 * Copyright(C) 2014-2016 Yutaka Kato All rights reserved.
 */

$(function () {
    $("#datepicker").datepicker();
    $("input[type=submit], button").button().submit(function (event) {
        event.preventDefault();
    });
});