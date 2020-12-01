import React from 'react';
import Content from './Content';

import styles from './PageContent.module.css'


function PageContent() {
    return (
        <div className={styles.container}>


            <Content text="Manage Your Expenses Interactively!" />
            <Content text="Banking With Friends!" />
            <Content text="See Your Transaction History!" />
            <Content text="Utilize a Message Board for Your Financial Needs!" />
            <Content text="View Your Spending Patterns!" />

            <Content text="View Your Spending Patterns!" />
            <Content text="View Your Spending Patterns!" />
            <Content text="View Your Spending Patterns!" />
            <Content text="View Your Spending Patterns!" />
            <Content text="View Your Spending Patterns!" />
            <Content text="View Your Spending Patterns!" />
            <Content text="But I don't wanna!" />


        </div>
    );
}

export default PageContent;
