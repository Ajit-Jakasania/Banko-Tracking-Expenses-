import React from 'react';
<<<<<<< HEAD
import { Component } from 'react';

import WelcomePage from './pages/welcomePage/WelcomePage'
import LoginPage from './pages/loginPage/LoginPage'
import RegisterPage from './pages/registerPage/RegisterPage'
import GroupRegisterPage from './pages/groupRegisterPage/GroupRegisterPage'
import JoinGroupPage from './pages/joinGroupPage/JoinGroupPage'
import LoginPage2 from './pages/loginPage/LoginPage2'
=======
import { useState } from 'react';
>>>>>>> 86f3c0b8a23b1ffe650808700285fc749e7ebceb

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

<<<<<<< HEAD
      <RegisterPage />
    </div>
  );
=======
        <NavbarLoggedIn />
        <Footer />
      </div>
  )
>>>>>>> 86f3c0b8a23b1ffe650808700285fc749e7ebceb
}

export default App;

