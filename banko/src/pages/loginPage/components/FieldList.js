import React from 'react'

import styles from './FieldList.module.css'
import InputField from './InputField'
import InputButton from './InputButton'

function FieldList()
{
    
    return(
        <ul className={styles}>
            <li>User Login</li>
            <li><input 
                    type="text" 
                    name="UserName" 
                    placeholder="Username"
                /></li>
            <li><input 
                    type="password" 
                    name="Password" 
                    placeholder="..."
                /></li>
            <li><InputButton text="Submit"/></li>
        </ul> 
    );
}

export default FieldList