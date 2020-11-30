import React from 'react'

import styles from './RegisterTitle.module.css'
import FieldList from './FieldList'

function RegisterTitle()
{
    
    return(
        <div className={styles.register_title}>
            <FieldList />
        </div>
    );
}

export default RegisterTitle;