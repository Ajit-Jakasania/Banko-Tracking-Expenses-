import React from 'react';

import styles from './GroupsFeed.module.css';
import JoinCreateGroup from './JoinCreateGroup';;

function GroupsFeed() {

    return (
        <div className={styles.GroupsFeed}>
            <JoinCreateGroup />

            {/* for each group ure in, put a <Group name=groupname groupmembers=arr/> */}
        </div>
    )
}

export default GroupsFeed;