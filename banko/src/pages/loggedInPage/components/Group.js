import React, { useEffect } from 'react';

import styles from './Group.module.css';

/*
    Im going to need some clarification here. You want users from where? Users in the
    same group? If so then we need to make an ajax call in a useEffect to get that
    information. 
*/
function Group(props) {
    return (
        <div className={styles.Group}>

            <h2>{props.name}</h2>

            {props.users.map(user => (
                <p>User {user}</p>
            ))}

        </div>
    )
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