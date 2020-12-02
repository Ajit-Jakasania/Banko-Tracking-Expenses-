import React, { useContext, useEffect, useState } from 'react';

import $ from 'jquery';

import styles from './MessagesFeed.module.css';
import { Context } from '../../../Store';

function MessagesFeed() {


    const [state, setState] = useContext(Context);
    const [groupNames, setGroupNames] = useState(new Array());
    const [flag, setFlag] = useState(0);
    const [messages, setMessages] = useState(new Array());


    function onSub() {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroupMessages',
            type: 'get',
            dataType: 'json',
            data: { group_id: state.group_id },
            success: function (data) {

                console.log(data);
                // $.each(data, function (key, value) {
                //     //add messages to the html dom
                // });
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
        return false;
    }



    function getGroups() {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroups',
            type: 'get',
            dataType: 'json',
            data: { user_id: state.id },
            success: function (data) {


                let temp = groupNames;
                let i = 0;
                $.each(data, function (key) {

                    let value = data[key].group_name;
                    temp[i] = value;
                    i++;
                });

                setGroupNames(temp);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    }

    if (flag == 0) {
        getGroups();
        setFlag(1);
    }

    return (
        <div className={styles.MessagesFeed}>



            <select id="dropdown" onChange={onSub()} >

                {groupNames.map(name => (
                    <option >{name}</option>
                ))}

                <input type="submit" value="hi"></input>

            </select>

        </div >
    )
}

export default MessagesFeed;