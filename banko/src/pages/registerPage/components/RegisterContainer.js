import React from 'react';

import styles from './RegisterContainer.css'

function RegisterContainer()
{
    
    return(
        <div className={styles.login_content}>
            <div class={styles.inner_login_content}>
                <div class="login_tile">
                    <div class="login_title">
                        <u1 class="list">
                            <li>Register</li>
                            <li><input type="text" name="User Name" placeholder="User Name"></input></li>
                            <li><input type="password" name="Password" placeholder="Password"></input></li>
                            <li><input type="button" name="Submit" value="Register"></input></li>
                        </u1>
                    </div>
                 </div>
            </div>
        </div>
    );
}

export default RegisterContainer;