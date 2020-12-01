import React, { useContext, useEffect } from 'react';

import $ from 'jquery';
import styles from './GroupsFeed.module.css';
import JoinCreateGroup from './JoinCreateGroup';
import Group from './Group';
import { Context } from '../../../Store';

function GroupsFeed() {

    const groups = new Array();
    const [state, useState] = useContext(Context);

    //write java for this
    useEffect(() => {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroups',
            type: 'get',
            dataType: 'json',
            data: { user_id: state.id },
            success: function (data) {

                console.log(data);
                var i = 0;
                $.each(data, function (key) {
                    
                    groups[i] = data[key];
                    console.log(groups[i]);
                    i++;
                });

            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }
        });

    })
    return (
        <div className={styles.GroupsFeed}>
            <JoinCreateGroup />

            <div className={styles.Groups}>
                {groups.map(group => (
                    <Group name={group[0].group_name} user={group[1]} />
                ))}
            </div>
        </div>
    )
}

export default GroupsFeed;