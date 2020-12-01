import React from 'react'

import styles from './LoginTile.module.css'
import LoginTitle from './LoginTitle'

function LoginTile()
{
    
    return(
        <div className={styles.login_tile}>
            <LoginTitle />
        </div>
    );
}

export default LoginTile;