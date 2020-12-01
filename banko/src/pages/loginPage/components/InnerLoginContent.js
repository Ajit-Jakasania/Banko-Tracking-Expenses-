import React from 'react'

import styles from './InnerLoginContent.module.css'
import LoginTile from './LoginTile'

function InnerLoginContent()
{
    
    return(
        <div className={styles.inner_login_content}>
            <LoginTile />
        </div>
    );
}

export default InnerLoginContent