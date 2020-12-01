import React from 'react';
import {BrowserRouter as Router, Route, Link } from "react-router-dom";

import styles from './LoggedInContainer.module.css';
import GroupsFeed from './GroupsFeed';
import WelcomePage from '../../welcomePage/WelcomePage';
import Group from './Group';
import MessagesFeed from './MessagesFeed';
import TransactionsFeed from './TransactionsFeed';


function LoggedInContainer() {
    return (
        <Router>
            <div className={styles.LoggedInContainer}>

                <div className={styles.Sidebar}>
                    <Link to="/">
                        <button className={styles.Buttons}>Home</button>
                    </Link>
                    <Link to='/groups'>
                        <button className={styles.Buttons}>Groups</button>
                    </Link>
                    <Link to='/messages'>
                        <button className={styles.Buttons}>Messages</button>
                    </Link>
                    <Link to='/transactions'>
                        <button className={styles.Buttons}>Transactions</button>
                    </Link>
                </div>

                <Route exact path='/' component={WelcomePage} />
                <Route path='/groups' component={Group} />
                <Route path='/messages' component={MessagesFeed} />
                <Route path='/transactions' component={TransactionsFeed} />

                {/* <GroupsFeed /> if they click Groups */}
                {/* MessagesFeed if they clicked Messagees  */}

            </div>
        </Router>
    )
}

export default LoggedInContainer;