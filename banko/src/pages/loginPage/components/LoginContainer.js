import React from 'react';

import styles from './LoginContainer.css'

function LoginContainer()
{
    
    return(
        <div className={styles.login_content}>
            <div class={styles.inner_login_content}>
                <div class="login_tile">
                    <div class="login_title">
                       <u1 class="list">
                           <li>User Login</li>
                           <li><input type="text" name="User name" placeholder="User Name"></input></li>
                           <li><input type="password" name="Password" placeholder="...."></input></li>
                           <li><input type="button" name="Submit" value="Submit"></input></li>
                        </u1> 
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginContainer;