import React, { useContext, useEffect } from 'react';

import $ from 'jquery';

import styles from './MessagesFeed.module.css';
import { Context } from '../../../Store';

function MessagesFeed() {


    const [state, setState] = useContext(Context);

    $("#dropdown").on("submit", function () {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getMessages',
            type: 'get',
            dataType: 'json',
            //Im assuming you wanted the global id variable state.group_id
            //data: { group_id: group_id_in },
            data: { group_id: state.group_id },
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
            //Im assuming you wanted global variable state to get state.user_id
            //data: { user_id: context.user_id },
            data: { user_id: state.id },
            success: function (data) {


                let dropdown = $('#dropdown');
                dropdown.append('<option selected="true" disabled>Choose Group</option>');
                dropdown.prop('selectedIndex', 0);


                dropdown.empty();


                $.each(data, function (key, value) {

                    //ask about this later
                    //dropdown.append($('<option></option>').attr('value', entry.abbreviation).text(entry.name));

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