import React, { useEffect, useState } from 'react';

import $ from 'jquery';
import styles from './Group.module.css';

/*
    Im going to need some clarification here. You want users from where? Users in the
    same group? If so then we need to make an ajax call in a useEffect to get that
    information. 
*/
function Group(props) {

    //const [users, setUsers] = useState();
    const [users, setUsers] = useState(new Array());
    const [flag, setFlag] = useState(0);

    function getUsers() {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'https://gothic-point-298207.uc.r.appspot.com/allGroupUser',
            type: 'get',
            dataType: 'json',
            data: { group_id: props.group_id },
            success: function (data) {
                let temp = new Array();
                var i = 0;


                $.each(data, function (key) {
                    temp[i] = data[i].username;

                    i++;
                });

                /**
                 * Can only call setState once in a function call
                 * If setState to a value that was initialized using the state, does not rerender component
                 */
                setUsers(temp);

            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    }
    // useEffect(() => {
    //     $.ajax({
    //         contentType: "application/json;charset=utf-8",
    //         url: 'http://localhost:8080/allGroupUser',
    //         type: 'get',
    //         dataType: 'json',
    //         data: { group_id: props.group_id },
    //         success: function (data) {

    //             var i = 0;
    //             console.log(data);


    //             $.each(data, function (key) {

    //                 users[i] = data[key];
    //                 console.log(users[i]);
    //                 i++;

    //             });

    //         },
    //         error: function (request, status, error) {
    //             console.log(request.responseText);
    //         }

    //     });
    // })

    if (flag == 0) {
        getUsers();
        setFlag(1);
    }

    if (flag == 1) {
        return (
            <div className={styles.Group}>

                <h2>{props.group_name}</h2>

                <div>


                    {users.map(user => (
                        <p>User: {user}</p>
                    ))}
                </div>

            </div>
        )
    } else {
        return (<p>Not yet</p>)
    }
}

/* old code
    return (
        <div className={styles.Group}>

            <h2>{props.name}</h2>

            {props.users.map(user => (
                <p>User {user}</p>
            ))}

        </div>
    )
*/

export default Group;