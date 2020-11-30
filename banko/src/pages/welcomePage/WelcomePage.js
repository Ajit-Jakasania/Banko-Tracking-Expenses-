import React, { useEffect } from 'react';
import { useState } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import Navbar from '../../components/Navbar/Navbar'
import PageContent from './components/PageContent';
import Footer from '../../components/Footer/Footer'
import $ from "jquery"
import RegisterPage from './../registerPage/RegisterPage'




function WelcomePage() {

    //const [count, setCount] = useState(0);
    //const [userID, setUserID] = useState(0);
    //const [firstname, setFirstname] = useState(0);

    return (
        <div>
            <Navbar />
            <PageContent />
            <Footer />
        </div>
    );
}
/*
return (
    <div>
        <Navbar />
        <PageContent />
        <p>Here is the count: {count}!</p>
        <p>Here is the username: {firstname}</p>
        <p>Here is the userID: {userID} </p>
        <button onClick={() => setCount(count + 1)}>
            Click to add one!
        </button>
        <Router>
            <ul>
                <li><Link to="/">Default Link</Link></li>
                <li><Link to="/Test">To second link</Link></li>
                <li><Link to="/RegisterPage">To Register Page</Link></li>
            </ul>
            <Route exact path="/" component={HelloThere} />
            <Route path="/Test" component={Test} />
            <Route path="/RegisterPage" component={RegisterPage} /> 
        </Router>
        <Footer />
    </div>
);
}

const HelloThere = () => (
    <div>
        <p>Link succeeded</p>
    </div>
)

const Test = () => (
    <div>
        <p>Second Link succeeded</p>
    </div>
)
*/
/*
<Link to="/../registerPage/RegisterPage">To the Register Page</Link>
                <Route exact path="/" component={WelcomePage} />
                <Route path="/../registerPage/RegisterPage" component={RegisterPage} /> 
*/

export default WelcomePage;