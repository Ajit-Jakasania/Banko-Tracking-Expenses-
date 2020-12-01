import React, { useEffect } from 'react';

import $ from 'jquery';

import styles from './MessagesFeed.module.css';
import { useEffect } from 'react';

function MessagesFeed() {




    $("#dropdown").on("submit", function () {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getMessages',
            type: 'get',
            dataType: 'json',
            data: { group_id: group_id_in },
            success: function (data) {


                $.each(data, function (key, value) {
                    //add messages to the html dom
                });
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });



    })

    //write java for this
    useEffect(() => {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getMessageGroups',
            type: 'get',
            dataType: 'json',
            data: { user_id: context.user_id },
            success: function (data) {


                let dropdown = $('#dropdown');
                dropdown.append('<option selected="true" disabled>Choose Group</option>');
                dropdown.prop('selectedIndex', 0);


                dropdown.empty();


                $.each(data, function (key, value) {

                    dropdown.append($('<option></option>').attr('value', entry.abbreviation).text(entry.name));

                });

            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    })


    return (
        <div className={styles.MessagesFeed}>

            <select id="dropdown" onchange="this.form.submit()"></select>

        </div>
    )
}

export default MessagesFeed;