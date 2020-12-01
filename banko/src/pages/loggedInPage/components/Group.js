import React, { useEffect } from 'react';

import $ from 'jquery';

import styles from './Group.module.css';
import { useEffect } from 'react';

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

export default Group;