import React, { useContext } from 'react';
import { Context } from '../../../Store';


import styles from './Button.module.css';
import styles2 from './LogInRegister.module.css';

function LogoutButton({ text }) {

    const [state, setState] = useContext(Context); // global user variable

    const handleLogout = () => {
        setState({id: 0}) //removes user
    }

    return (
        <div className={styles2.container}>
            <div className={styles.Button}>
                <a className={styles} onClick={handleLogout}>{text}</a>   
            </ div>
        </div>
    );
}



export default LogoutButton;