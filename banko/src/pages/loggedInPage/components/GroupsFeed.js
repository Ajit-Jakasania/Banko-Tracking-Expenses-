import React, { useContext, useEffect, useState } from 'react';

import $ from 'jquery';
import styles from './GroupsFeed.module.css';
import JoinCreateGroup from './JoinCreateGroup';
import Group from './Group';
import { Context } from '../../../Store';

function GroupsFeed() {

    const [groups, setGroups] = useState(new Array());
    const [flag, setFlag] = useState(0);
    const [state, setState] = useContext(Context);

    function getGroups() {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userGroups',
            type: 'get',
            dataType: 'json',
            data: { user_id: state.id },
            success: function (data) {

                var i = 0;
                setGroups(data);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }
        });
    }

    //write java for this
    // useEffect(() => {
    //     $.ajax({
    //         contentType: "application/json;charset=utf-8",
    //         url: 'http://localhost:8080/userGroups',
    //         type: 'get',
    //         dataType: 'json',
    //         data: { user_id: state.id },
    //         success: function (data) {

    //             console.log(data);
    //             var i = 0;
    //             $.each(data, function (key) {

    //                 groups[i] = data[key];
    //                 console.log(groups[i]);
    //                 i++;
    //             });
    //             printGroups();
    //         },
    //         error: function (request, status, error) {
    //             console.log(request.responseText);
    //         }
    //     });

    // })

    if (flag == 0) {
        getGroups();
        setFlag(1);
    }

    if (flag == 1) {
        return (
            <div className={styles.GroupsFeed}>
                <div className={styles.Groups}>
                    {groups.map(group => (
                        <Group group_id={group.group_id} group_name={group.group_name} />
                    ))}
                </div>
            </div>
        )
    } else {
        return (<p>No groups</p>)
    }
    // return (
    //     <div className={styles.GroupsFeed}>
    //         <JoinCreateGroup />

    //         <div className={styles.Groups}>
    //             <p>we are still in the groupsfeed</p>
    //             <p>Here is the group: {groups[0].group_name}</p>


    //         </div>
    //     </div>
    // )
}
//<Group group_id={group.group_id} group_name={group.group_name} />
/*
{groups.map((group, key) => 
                    <div>
                        <p>We entered: {group}</p>
                    </div>
                )}
                */
export default GroupsFeed;