import React from 'react';
import { useState } from 'react';

import './App.css';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';
import NavbarLoggedIn from './components/Navbar/NavbarLoggedIn';


function App() {

  const [count, setCount] = useState(0);
  const [userID, setUserID] = useState(0);
  const [firstname, setFirstname] = useState(0);
  const [isLoggedIn, setLoggedIn] = useState(false);


  //we want to set a state for user info and set isLoggedIn to true
  const setUser = (jsonData) => {
    setLoggedIn(true);
  }

  if (!isLoggedIn){
    return (
      <div className="App">

        <Navbar />
        <Footer />
      </div>
    );
  }
  return (
    <div className="App">

        <NavbarLoggedIn />
        <Footer />
      </div>
  )
}

export default App;

