import React, { useContext, useEffect, useState } from 'react';

import $ from 'jquery';

import styles from './MessagesFeed.module.css';
import { Context } from '../../../Store';

function MessagesFeed() {


    const [state, setState] = useContext(Context);
    const [groupNames, setGroupNames] = useState(new Array());
    const [flag, setFlag] = useState(0);
    const [messages, setMessages] = useState(new Array());
    const [group, setGroup] = useState(0);





    function getGroups() {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroups',
            type: 'get',
            dataType: 'json',
            data: { user_id: state.id },
            success: function (data) {


                let temp = new Array();
                let i = 0;
                $.each(data, function (key) {

                    temp[i] = data[key].group_name;
                    i++;
                });

                setGroupNames(temp);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    }

    function change(event) {

        if (event.target.value == 0) return false;

        setGroup(event.target.value);


        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroupMessages',
            type: 'get',
            dataType: 'json',
            data: { group_name: event.target.value },
            success: function (data) {
                let temp = data;

                setMessages(temp);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
        return false;
    }


    if (flag == 0) {
        getGroups();
        setFlag(1);
    }

    return (
        <div className={styles.MessagesFeed}>


            <form action="onSub()">

                <select id="dropdown" onChange={change} value={group}>
                    <option value="0"> Choose a Group</option>
                    {groupNames.map(name => (
                        <option value={name}>{name}</option>
                    ))}
                </select>
            </form>

            <div>
                {messages.map(message => (
                    <p> {message.date_sent}  {message.username} : {message.content} </p>
                ))}

            </div>


        </div >
    )
}

export default MessagesFeed;