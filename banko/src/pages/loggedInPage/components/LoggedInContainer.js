import React from 'react';

import styles from './LoggedInContainer.module.css';
import GroupsFeed from './GroupsFeed';


function LoggedInContainer() {
    return (
        <div className={styles.LoggedInContainer}>

            <div className={styles.Sidebar}>
                <button className={styles.Buttons}>Home</button>
                <button className={styles.Buttons}>Groups</button>
                <button className={styles.Buttons}>Messages</button>
                <button className={styles.Buttons}>Transactions</button>
            </div>

            {/* <GroupsFeed /> if they click Groups */}
            {/* MessagesFeed if they clicked Messagees  */}

        </div>
    )
}

export default LoggedInContainer;